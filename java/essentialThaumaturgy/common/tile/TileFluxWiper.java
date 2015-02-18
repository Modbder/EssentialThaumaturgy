package essentialThaumaturgy.common.tile;

import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ec3.api.ApiCore;
import essentialThaumaturgy.common.utils.ThaumcraftHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import DummyCore.Utils.Coord3D;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.DummyDistance;
import DummyCore.Utils.MathUtils;

public class TileFluxWiper extends TileHasMRU{
	
	public Coord3D cleared;
	public int clearTime = 0;
	
	public boolean goingUp;
	public float upIndex;
	
	
	public TileFluxWiper()
	{
		this.maxMRU = (int) ApiCore.DEVICE_MAX_MRU_GENERIC;
		this.setSlotsNum(1);
	}
	
	@Override
	public void updateEntity()
	{
		if(this.getMRU() > 0)
		{
			if(!goingUp)
				upIndex += 0.01F;
			else
				upIndex -= 0.03F;
			if(!goingUp && upIndex >= 0.45F)
			{
				goingUp = true;
				this.worldObj.playSound(xCoord+0.5F, yCoord+0.5F, zCoord+0.5F, "random.fizz", 0.1F, 1F, true);
				int rotation = this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
				if(rotation == 1)
				{
					for(int i = 0; i < 5; ++i)
						this.worldObj.spawnParticle("explode", xCoord+0.5F, yCoord+0.1F, zCoord+0.5F, 0.0F, 0, 0);
				}
				if(rotation == 0)
				{
					for(int i = 0; i < 5; ++i)
						this.worldObj.spawnParticle("explode", xCoord+0.5F, yCoord+0.8F, zCoord+0.5F, 0.0F, -0.15F, 0);
				}
				if(rotation == 4)
				{
					for(int i = 0; i < 5; ++i)
						this.worldObj.spawnParticle("explode", xCoord+0.8F, yCoord+0.5F, zCoord+0.5F, -0.150F, 0F, 0);
				}
				if(rotation == 5)
				{
					for(int i = 0; i < 5; ++i)
						this.worldObj.spawnParticle("explode", xCoord+0.2F, yCoord+0.5F, zCoord+0.5F, 0.150F, 0F, 0);
				}
				if(rotation == 2)
				{
					for(int i = 0; i < 5; ++i)
						this.worldObj.spawnParticle("explode", xCoord+0.5F, yCoord+0.5F, zCoord+0.8F, 0F, 0F, -0.15F);
				}
				if(rotation == 3)
				{
					for(int i = 0; i < 5; ++i)
						this.worldObj.spawnParticle("explode", xCoord+0.5F, yCoord+0.5F, zCoord+0.2F, 0F, 0F, 0.15F);
				}
			}
			if(goingUp && upIndex <= 0)
				goingUp = false;
		}
		super.updateEntity();
		if(cleared == null && this.getMRU()-5 >= 0)
		{
			this.setMRU(this.getMRU()-5);
			if(!this.worldObj.isRemote)
			{
				
				int offsetX = (int) (MathUtils.randomDouble(this.worldObj.rand)*16);
				int offsetY = (int) (MathUtils.randomDouble(this.worldObj.rand)*16);
				int offsetZ = (int) (MathUtils.randomDouble(this.worldObj.rand)*16);
				Block b = this.worldObj.getBlock(xCoord+offsetX, yCoord+offsetY, zCoord+offsetZ);
				try
				{
					Block goo = (Block)(ThaumcraftHelper.getField("thaumcraft.common.config.ConfigBlocks", "blockFluxGoo", null));
					Block gas = (Block)(ThaumcraftHelper.getField("thaumcraft.common.config.ConfigBlocks", "blockFluxGas", null));
				if(b == goo || b == gas)
				{
					cleared = new Coord3D(xCoord+offsetX, yCoord+offsetY, zCoord+offsetZ);
					clearTime = 2*10;
				}
				}catch(Exception e)
				{
					e.printStackTrace();
					return;
				}
			}
		}else
		{
			if(!this.worldObj.isRemote && this.cleared != null)
			{
				Block b = this.worldObj.getBlock((int)cleared.x, (int)cleared.y, (int)cleared.z);
				try
				{
					Block goo = (Block)(ThaumcraftHelper.getField("thaumcraft.common.config.ConfigBlocks", "blockFluxGoo", null));
					Block gas = (Block)(ThaumcraftHelper.getField("thaumcraft.common.config.ConfigBlocks", "blockFluxGas", null));
				if(b != goo && b != gas)
				{
					cleared = null;
					clearTime = 0;
					return;
				}
				if(this.getMRU() - 20 > 0)
				{
					--clearTime;
					this.setMRU(this.getMRU()-20);
					if(clearTime <= 0)
					{

						this.worldObj.setBlockToAir((int)cleared.x, (int)cleared.y, (int)cleared.z);
						this.cleared = null;
					}
				}
				}catch(Exception e)
				{
					e.printStackTrace();return;
				}
			}
		}
	}
    
	@Override
    public void readFromNBT(NBTTagCompound i)
    {
		if(i.hasKey("coord"))
		{
			String str = i.getString("coord");
			if(!str.equals("null"))
			{
				DummyData[] coordData = DataStorage.parseData(i.getString("coord"));
				cleared = new Coord3D(Double.parseDouble(coordData[0].fieldValue),Double.parseDouble(coordData[1].fieldValue),Double.parseDouble(coordData[2].fieldValue));
			}else
			{
				cleared = null;
			}
		}
		clearTime = i.getInteger("clear");
		super.readFromNBT(i);
    }
	
	@Override
    public void writeToNBT(NBTTagCompound i)
    {
		if(cleared != null)
		{
			i.setString("coord", cleared.toString());
		}else
		{
			i.setString("coord", "null");
		}
		i.setInteger("clear", clearTime);
    	super.writeToNBT(i);
    }
}
