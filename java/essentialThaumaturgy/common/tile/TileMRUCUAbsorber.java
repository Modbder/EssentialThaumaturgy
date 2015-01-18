package essentialThaumaturgy.common.tile;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import DummyCore.Utils.Coord3D;
import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import ec3.api.ApiCore;
import ec3.api.IMRUPressence;
import essentialThaumaturgy.common.init.AspectsInit;
import essentialThaumaturgy.common.utils.ETUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;

public class TileMRUCUAbsorber extends TileHasMRUAndAspects{
	
	public float extension;
	
	public IMRUPressence mrucu;
	
	public int getRotation()
	{
		return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
	}
	
	public IMRUPressence getMRUCU()
	{
		if(mrucu == null) mrucu = ApiCore.getClosestMRUCU(worldObj, new Coord3D(xCoord,yCoord,zCoord), 8);
		return mrucu;
	}
	
	public TileMRUCUAbsorber()
	{
		this.setMaxMRU(5000F);
		this.setSlotsNum(1);
	}
	
	@Override
	public boolean isConnectable(ForgeDirection face) {
		return face.ordinal() != this.getRotation();
	}
	
	
	@Override
	public void updateEntity()
	{
		if(this.getAspects().getAmount(AspectsInit.MRU) == 0)
		{
			this.aspects.remove(AspectsInit.MRU);
		}
		if(getMRUCU() != null && this.extension < 1.0F && this.getMRU() > 0)
			this.extension += 0.03F;
		else if(this.getMRUCU() == null && this.extension > 0F)
			this.extension -= 0.03F;
		super.updateEntity();
		
		if(this.getMRUCU() != null)
		{
			IMRUPressence mrucu = this.getMRUCU();
			this.setBalance(mrucu.getBalance());
			if(mrucu.getMRU() > 1000 && this.worldObj.getWorldTime()%20 == 0)
			{
				int amount = this.getAspects().getAmount(AspectsInit.MRU);
				if(amount <= 63)
				{
					if(this.getMRU() >= 30 && this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
					{
						this.setMRU(this.getMRU()-30*20);
						if(this.worldObj.getWorldTime()%20 == 0)
							mrucu.setMRU(mrucu.getMRU()-100);
						if(this.worldObj.getWorldTime()%200 == 0)
							this.addToContainer(AspectsInit.MRU, 1);
					}
				}
			}
		}
		
	}

	
	
}
