package essentialThaumaturgy.common.tile;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import ec3.api.ITEHasMRU;
import essentialThaumaturgy.common.utils.ETUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;

public class TileMRUDimensionalTranciever extends TileHasMRU{
	
	public TileMRUDimensionalTranciever()
	{
		this.setMaxMRU(5000F);
		this.setSlotsNum(1);
	}
	
	@Override
	public void updateEntity()
	{
		++this.innerRotation;
		if(syncTick == 0)
		{
			if(!this.worldObj.isRemote)
			{
				MiscUtils.sendPacketToAllAround(worldObj, getDescriptionPacket(), xCoord, yCoord, zCoord, this.worldObj.provider.dimensionId, 128);
			}
			syncTick = 10;
		}else
			--this.syncTick;
		
		mruIn();
	}
	
	public void mruIn()
	{
		try
		{
		IInventory inv = this;
		int slotNum = 0;
		TileEntity tile = this;
		Class boungGemClass = Class.forName("ec3.common.item.ItemBoundGem");
		if(inv.getSizeInventory() > 0 && inv.getStackInSlot(slotNum) != null && inv.getStackInSlot(slotNum).getItem().getClass() == boungGemClass && inv.getStackInSlot(slotNum).getTagCompound() != null)
		{
			ItemStack s = inv.getStackInSlot(slotNum);
			int[] o = getCoords(s);
			if(MathUtils.getDifference(tile.xCoord, o[0]) <= 16 && MathUtils.getDifference(tile.yCoord, o[1]) <= 16 && MathUtils.getDifference(tile.zCoord, o[2]) <= 16)
			{
    			if(tile.getWorldObj().getTileEntity(o[0], o[1], o[2]) != null && tile.getWorldObj().getTileEntity(o[0], o[1], o[2]) instanceof ITEHasMRU)
    			{
    				this.setBalance(((ITEHasMRU) tile.getWorldObj().getTileEntity(o[0], o[1], o[2])).getBalance());
    			}
    		}
		}
		if(inv.getSizeInventory() > 0)
		{
			mruIn(this,0);
			Class ecUtils = Class.forName("ec3.utils.common.ECUtils");
			Method spawnMRUParticles = ecUtils.getMethod("spawnMRUParticles", TileEntity.class,int.class);
			spawnMRUParticles.setAccessible(true);
			spawnMRUParticles.invoke(null, this,0);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	public static void mruIn(TileEntity tile, int slotNum)
	{
		try
		{
		if(tile instanceof IInventory && tile instanceof ITEHasMRU)
		{
			IInventory inv = (IInventory) tile;
			ITEHasMRU mrut = (ITEHasMRU) tile;
			Class boungGemClass = Class.forName("ec3.common.item.ItemBoundGem");
			if(inv.getStackInSlot(slotNum) != null && inv.getStackInSlot(slotNum).getItem().getClass() == boungGemClass && inv.getStackInSlot(slotNum).getTagCompound() != null)
			{
				ItemStack s = inv.getStackInSlot(slotNum);
				
				Method getCoords = boungGemClass.getMethod("getCoords", ItemStack.class);
				int[] o = (int[]) getCoords.invoke(null, s);
				if(true)
				{
					World wrldObj = 
							MinecraftServer.getServer().worldServerForDimension(getDimension(s));
	    			if(wrldObj != null && wrldObj.getTileEntity(o[0], o[1], o[2]) != null && wrldObj.getTileEntity(o[0], o[1], o[2]) instanceof ITEHasMRU)
	    			{
		    			ITEHasMRU t = (ITEHasMRU) wrldObj.getTileEntity(o[0], o[1], o[2]);
		    			if(t != tile && t != null && !tile.getWorldObj().isRemote)
		    			{
		    				if(mrut.getMRU() < mrut.getMaxMRU())
		    				{
		    					int mru = t.getMRU();
		    					if(mru > mrut.getMaxMRU() - mrut.getMRU())
		    					{
		    						t.setMRU(mru-(mrut.getMaxMRU() - mrut.getMRU()));
		    						mrut.setMRU(mrut.getMaxMRU());
		    					}else
		    					{
		    						t.setMRU(0);
		    						mrut.setMRU(mrut.getMRU()+mru);
		    					}
		    					mrut.setBalance((mrut.getBalance()+t.getBalance())/2);
		    				}
		    			}
	    			}
				}
			}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	public static int getDimension(ItemStack stack)
	{
		return MiscUtils.getStackTag(stack).getInteger("dim");
	}
	
}
