package essentialThaumaturgy.common.tile;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import essentialThaumaturgy.common.init.AspectsInit;
import essentialThaumaturgy.common.utils.ETUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;

public class TileMRUGenerator extends TileHasMRUReqAspects{
	
	public int charge;
	
	public TileMRUGenerator()
	{
		this.setMaxMRU(5000F);
		this.setSlotsNum(0);
	}
	
	@Override
    public void readFromNBT(NBTTagCompound i)
    {
		super.readFromNBT(i);
		charge = i.getInteger("charge");
    }
	
	@Override
    public void writeToNBT(NBTTagCompound i)
    {
    	super.writeToNBT(i);
		i.setInteger("charge", charge);
    }
	
	@Override
	public void setSuction(Aspect aspect, int amount) {
		
	}
	
	@Override
	public Aspect getSuctionType(ForgeDirection face) {
		return AspectsInit.MRU;
	}

	@Override
	public int getSuctionAmount(ForgeDirection face) {
		return 64;
	}

	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		for(int i  = 0; i < 6; ++i)
		{
			ForgeDirection facing = ForgeDirection.getOrientation(i);
			TileEntity te = ThaumcraftApiHelper.getConnectableTile(getWorldObj(), xCoord, yCoord, zCoord, facing);
			if(te != null)
			{
				IEssentiaTransport ic = (IEssentiaTransport) te;
	            if(!ic.canOutputTo(facing.getOpposite()))
	            {
	            	
	            }else
		            if(this.getAspects().getAmount(AspectsInit.MRU)+1 < this.maxAspects && ic.getSuctionAmount(facing.getOpposite()) < getSuctionAmount(facing) && ic.takeEssentia(AspectsInit.MRU, 1, facing.getOpposite()) == 1)
		            {
		            	this.addEssentia(AspectsInit.MRU, 1, facing);
		            }
			}
		}
		if(this.getAspects().getAmount(AspectsInit.MRU) > 0 && charge <= 0)
		{
			this.takeFromContainer(AspectsInit.MRU, 1);
			charge = 20*60;
		}
		try
		{
			if(charge > 0)
			{
				if(this.getMRU()+30 <= this.getMaxMRU())
				{
					this.setMRU(this.getMRU()+30);
					Class thaumcraft = Class.forName("thaumcraft.common.Thaumcraft");
					Field proxy = thaumcraft.getField("proxy");
					proxy.setAccessible(true);
					Class proxyClass = proxy.get(null).getClass();
					Method sparkle = proxyClass.getMethod("sparkle", float.class,float.class,float.class,float.class,int.class,float.class);
					sparkle.setAccessible(true);
					for(int i = 2; i < 6; ++i)
					{
						ForgeDirection dir = ForgeDirection.getOrientation(i);
						if(dir.offsetZ == 0)
						{
							sparkle.invoke(proxy.get(null),xCoord+0.5F+(float)dir.offsetX/1.90F,yCoord+1F,zCoord+0.5F+MathUtils.randomFloat(this.worldObj.rand)/2F,1F,0,0.2F);
							sparkle.invoke(proxy.get(null),xCoord+0.5F+(float)dir.offsetX/1.90F,yCoord,zCoord+0.5F+MathUtils.randomFloat(this.worldObj.rand)/2F,1F,0,-0.2F);
						}else
						{
							sparkle.invoke(proxy.get(null),xCoord+0.5F+MathUtils.randomFloat(this.worldObj.rand)/2F,yCoord+1F,zCoord+0.5F+(float)dir.offsetZ/1.90F,1F,0,0.2F);
							sparkle.invoke(proxy.get(null),xCoord+0.5F+MathUtils.randomFloat(this.worldObj.rand)/2F,yCoord,zCoord+0.5F+(float)dir.offsetZ/1.90F,1F,0,-0.2F);
						}
						
					}
					--this.charge;
				}

			}
		}catch(Exception e)
		{
			e.printStackTrace();return;
		}
	}

	@Override
	public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
		System.out.println(aspect);
		if(this.getSuctionType(face) == aspect)
			return this.addEssentia(aspect, amount, face);
		return 0;
	}
	
}
