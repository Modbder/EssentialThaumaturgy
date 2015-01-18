package essentialThaumaturgy.common.tile;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import DummyCore.Utils.Coord3D;
import DummyCore.Utils.Lightning;
import DummyCore.Utils.MathUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import essentialThaumaturgy.common.block.BlockRadiatedAuraNode;
import essentialThaumaturgy.common.init.BlocksInit;
import essentialThaumaturgy.common.utils.ETUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.visnet.TileVisNode;

public class TileNodeIrradiator extends TileHasMRU{
	
	public float progress, rotation, rotationSpeed;
	
	public List<Lightning> lightnings = new ArrayList();
	
	public boolean canWork()
	{
		return hasNode() && this.getMRU() > 0;
	}
	
	
	public boolean hasNode()
	{
		ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[ForgeDirection.OPPOSITES[worldObj.getBlockMetadata(xCoord, yCoord, zCoord)]];
			TileEntity t = worldObj.getTileEntity(xCoord+d.offsetX, yCoord, zCoord+d.offsetZ);
			if(t != null && (t instanceof TileRadiatedNode || (t instanceof TileVisNode && worldObj.getBlockMetadata(xCoord+d.offsetX, yCoord, zCoord+d.offsetZ) == 5)))
				return true;
		return false;
	}
	
	public TileEntity getNode()
	{
			ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[ForgeDirection.OPPOSITES[worldObj.getBlockMetadata(xCoord, yCoord, zCoord)]];
			TileEntity t = worldObj.getTileEntity(xCoord+d.offsetX, yCoord, zCoord+d.offsetZ);
			if(t != null && (t instanceof TileRadiatedNode || (t instanceof TileVisNode && worldObj.getBlockMetadata(xCoord+d.offsetX, yCoord, zCoord+d.offsetZ) == 5)))
				return t;
		return null;
	}
	
	public TileNodeIrradiator()
	{
		this.setMaxMRU(5000F);
		this.setSlotsNum(1);
	}
	
	@Override
    public void writeToNBT(NBTTagCompound i)
    {
		i.setFloat("rotation", this.rotation);
		i.setFloat("speed", this.rotationSpeed);
		i.setFloat("progress", this.progress);
		super.writeToNBT(i);
    }
	
	@Override
    public void readFromNBT(NBTTagCompound i)
    {
		this.rotation = i.getFloat("rotation");
		this.rotationSpeed = i.getFloat("speed");
		this.progress = i.getFloat("progress");
		super.readFromNBT(i);
    }
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		TileEntity tile = this.getNode();
		if(tile != null)
		{
			if(!(tile instanceof TileRadiatedNode))
			{
				if(this.getMRU()-100 >= 0)
				{
					this.setMRU(this.getMRU()-100);
					if(this.rotationSpeed == 0)
						this.rotationSpeed = 0.125F;
					else
						if(this.rotationSpeed< 45)
							this.rotationSpeed *= 1.001F;
					++this.progress;
					if(this.worldObj.isRemote && this.worldObj.getWorldTime()%50==0)
						this.worldObj.playSound(xCoord, yCoord, zCoord, "minecart.inside", rotationSpeed/22.5F, rotationSpeed/22.5F, false);
					if(this.rotation%90 <= this.rotationSpeed)
					{
						this.worldObj.playSound(xCoord, yCoord, zCoord, "mob.irongolem.hit", 1, rotationSpeed/22.5F, true);
					}
					if(this.progress > 6200 && this.progress < 7000)
					{
						for(int i = 0; i < 10; ++i)
						{
							this.worldObj.spawnParticle("smoke", xCoord+0.5F+MathUtils.randomFloat(this.worldObj.rand)/3D, yCoord+0.5F+MathUtils.randomFloat(this.worldObj.rand)/3D, zCoord+0.5F+MathUtils.randomFloat(this.worldObj.rand)/3D, 0, 0, 0);
						}
					}
					if(this.progress > 6400 && this.progress < 7000)
					{
						this.worldObj.spawnParticle("flame", xCoord+0.5F+MathUtils.randomFloat(this.worldObj.rand)/3D, yCoord+0.5F+MathUtils.randomFloat(this.worldObj.rand)/3D, zCoord+0.5F+MathUtils.randomFloat(this.worldObj.rand)/3D, 0, 0, 0);
					}
					if(this.progress > 6500 && this.progress < 7000)
					{
						if(this.worldObj.isRemote && this.worldObj.getWorldTime()%5==0)
							this.worldObj.playSound(xCoord, yCoord, zCoord, "essentialcraft:sound.lightning_hit", 0.5F, rotationSpeed/22.5F, false);
						if(this.worldObj.isRemote && this.lightnings.size() <= 20)
						{
							Lightning l = new Lightning(this.worldObj.rand, new Coord3D(0.5F, 0.5F, 0.5F), new Coord3D(0.5F+MathUtils.randomFloat(this.worldObj.rand), 0.5F+MathUtils.randomFloat(this.worldObj.rand), 0.5F+MathUtils.randomFloat(this.worldObj.rand)), 0.1F, 0.7F, 0.0F, 1.0F);
							this.lightnings.add(l);
						}
					}
					if(this.progress >= 7000)
					{
						ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[ForgeDirection.OPPOSITES[worldObj.getBlockMetadata(xCoord, yCoord, zCoord)]];
						TileVisNode node = (TileVisNode) tile;
						IAspectContainer modeCon = (IAspectContainer) tile;
						AspectList aspects = modeCon.getAspects().copy();
						for(int i = 0; i < aspects.size(); ++i)
						{
							Aspect asp = aspects.getAspects()[i];
							int amount = aspects.getAmount(asp);
							aspects.merge(asp, amount*2);
						}
						this.worldObj.setBlockToAir(xCoord+d.offsetX, yCoord, zCoord+d.offsetZ);
						this.worldObj.setBlock(xCoord+d.offsetX, yCoord, zCoord+d.offsetZ, BlocksInit.radiatedNode, 0, 3);
						TileRadiatedNode rNode = (TileRadiatedNode) this.getNode();
						rNode.visBase = aspects;
						
					}
				}else
				{
					this.progress = 0F;
					if(this.rotationSpeed > 0.1F)
						this.rotationSpeed /= 1.05F;
					else
						this.rotationSpeed = 0F;
				}
			}else
			{
				if(this.getMRU()-5 >= 0)
				{
					this.setMRU(this.getMRU()-5);
					if(this.rotationSpeed > 1F)
						this.rotationSpeed /= 1.005F;
					if(this.worldObj.isRemote && this.worldObj.getWorldTime()%50==0)
						this.worldObj.playSound(xCoord, yCoord, zCoord, "minecart.inside", rotationSpeed/45F, rotationSpeed/45F, false);
					if(this.rotation%90 <= this.rotationSpeed)
					{
						this.worldObj.playSound(xCoord, yCoord, zCoord, "mob.irongolem.hit", 0.5F, rotationSpeed/45F, true);
					}
				}else
				{
					if(this.rotationSpeed > 1F)
						this.rotationSpeed /= 1.005F;
					if(this.rotationSpeed <= 0.2F)
					{
						((BlockRadiatedAuraNode)BlocksInit.radiatedNode).explode(worldObj,tile.xCoord,tile.yCoord,tile.zCoord);
					}
				}
			}
		}else
		{
			this.progress = 0F;
			if(this.rotationSpeed > 0.1F)
				this.rotationSpeed /= 1.05F;
			else
				this.rotationSpeed = 0F;
		}
		this.rotation+=this.rotationSpeed;
		if(this.worldObj.isRemote)
			for(int i = 0; i < lightnings.size(); ++i)
			{
				Lightning lt = lightnings.get(i);
				if(lt.renderTicksExisted >= 21)
					this.lightnings.remove(i);
			}
	}
	
}
