package essentialThaumaturgy.common.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectSourceHelper;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.visnet.VisNetHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ec3.api.ApiCore;
import ec3.api.IMRUPressence;
import essentialThaumaturgy.common.init.AspectsInit;
import essentialThaumaturgy.common.utils.ThaumcraftHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;
import DummyCore.Utils.Coord3D;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.DummyDistance;
import DummyCore.Utils.Lightning;
import DummyCore.Utils.MathUtils;

public class TileMRUCUStabilizer extends TileHasMRUReqAspects{
	
	public List<Lightning> lightnings = new ArrayList<Lightning>();
	
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
	
	public TileMRUCUStabilizer()
	{
		this.maxMRU = (int) ApiCore.DEVICE_MAX_MRU_GENERIC;
		this.setSlotsNum(1);
	}
	
	@Override
	public void updateEntity()
	{
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
		            if(this.getAspects().getAmount(Aspect.ORDER)+1 < this.maxAspects && ic.getSuctionAmount(facing.getOpposite()) < getSuctionAmount(facing) && ic.takeEssentia(Aspect.ORDER, 1, facing.getOpposite()) == 1)
		            {
		            	this.addEssentia(Aspect.ORDER, 1, facing);
		            }
			}
		}
		super.updateEntity();
		
		if(this.getMRUCU() != null)
		{
			if(this.getMRU() - 10 >= 0 && this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
			{
				this.setMRU(this.getMRU()-10);
				IMRUPressence mrucu = this.getMRUCU();
				Entity mrucu_e = (Entity) mrucu;
				float balanceDifference = 1.0F-mrucu.getBalance();

				this.setBalance(mrucu.getBalance());
				if(this.worldObj.getWorldTime() % 20 == 0)
				{
					if(this.getAspects().getAmount(Aspect.ORDER) <= 0)
						this.getAspects().remove(Aspect.ORDER);
					float balanceChange = balanceDifference/100;
					if(this.getAspects().getAmount(Aspect.ORDER) >= 1)
					{
						this.takeFromContainer(Aspect.ORDER, 1);
						mrucu.setBalance(mrucu.getBalance()+balanceChange);
						this.worldObj.playSound(xCoord+0.5F, yCoord+0.5F, zCoord+0.5F, "essentialcraft:sound.lightning_hit", 0.3F, 0.01F, true);
						if(this.worldObj.isRemote)
						{
							float colorR = 0.0F;
							if(balanceDifference < 0)
								colorR = -balanceDifference;
							float colorB = 0.0F;
							if(balanceDifference > 0)
								colorB = balanceDifference;
							float colorG = 1.0F;
							if(balanceDifference < 0)
								colorG = 1-colorR;
							if(balanceDifference > 0)
								colorG = 1-colorB;
							Lightning l = new Lightning(this.worldObj.rand,new Coord3D(mrucu_e.posX-(xCoord+0.5F),mrucu_e.posY-(yCoord+0.5F),mrucu_e.posZ-(zCoord+0.5F)),new Coord3D(0.5F,0.5F,0.5F),0.03F,colorR,colorG,1-colorR);
							this.lightnings.add(l);
							Lightning l1 = new Lightning(this.worldObj.rand,new Coord3D(0.5F,0.8F,0.5F),new Coord3D(mrucu_e.posX-(xCoord+0.5F),mrucu_e.posY-(yCoord+0.5F),mrucu_e.posZ-(zCoord+0.5F)),0.3F,1,1,1);
							this.lightnings.add(l1);
						}
					}else
					{
						int drain = VisNetHandler.drainVis(worldObj, xCoord, yCoord, zCoord, Aspect.ORDER, 5);
						if(drain >= 5)
						{
							mrucu.setBalance(mrucu.getBalance()+balanceChange);
							this.worldObj.playSound(xCoord+0.5F, yCoord+0.5F, zCoord+0.5F, "essentialcraft:sound.lightning_hit", 0.3F, 0.01F, true);
							if(this.worldObj.isRemote)
							{
								float colorR = 0.0F;
								if(balanceDifference < 0)
									colorR = -balanceDifference;
								float colorB = 0.0F;
								if(balanceDifference > 0)
									colorB = balanceDifference;
								float colorG = 1.0F;
								if(balanceDifference < 0)
									colorG = 1-colorR;
								if(balanceDifference > 0)
									colorG = 1-colorB;
								Lightning l = new Lightning(this.worldObj.rand,new Coord3D(mrucu_e.posX-(xCoord+0.5F),mrucu_e.posY-(yCoord+0.5F),mrucu_e.posZ-(zCoord+0.5F)),new Coord3D(0.5F,0.5F,0.5F),0.03F,colorR,colorG,1-colorR);
								this.lightnings.add(l);
								Lightning l1 = new Lightning(this.worldObj.rand,new Coord3D(0.5F,0.8F,0.5F),new Coord3D(mrucu_e.posX-(xCoord+0.5F),mrucu_e.posY-(yCoord+0.5F),mrucu_e.posZ-(zCoord+0.5F)),0.3F,1,1,1);
								this.lightnings.add(l1);
							}
						}else
						{
							if(AspectSourceHelper.drainEssentia(this, Aspect.ORDER, ForgeDirection.UNKNOWN, 6))
							{
								mrucu.setBalance(mrucu.getBalance()+balanceChange);
								this.worldObj.playSound(xCoord+0.5F, yCoord+0.5F, zCoord+0.5F, "essentialcraft:sound.lightning_hit", 0.3F, 0.01F, true);
								if(this.worldObj.isRemote)
								{
									float colorR = 0.0F;
									if(balanceDifference < 0)
										colorR = -balanceDifference;
									float colorB = 0.0F;
									if(balanceDifference > 0)
										colorB = balanceDifference;
									float colorG = 1.0F;
									if(balanceDifference < 0)
										colorG = 1-colorR;
									if(balanceDifference > 0)
										colorG = 1-colorB;
									Lightning l = new Lightning(this.worldObj.rand,new Coord3D(mrucu_e.posX-(xCoord+0.5F),mrucu_e.posY-(yCoord+0.5F),mrucu_e.posZ-(zCoord+0.5F)),new Coord3D(0.5F,0.5F,0.5F),0.03F,colorR,colorG,1-colorR);
									this.lightnings.add(l);
									Lightning l1 = new Lightning(this.worldObj.rand,new Coord3D(0.5F,0.8F,0.5F),new Coord3D(mrucu_e.posX-(xCoord+0.5F),mrucu_e.posY-(yCoord+0.5F),mrucu_e.posZ-(zCoord+0.5F)),0.3F,1,1,1);
									this.lightnings.add(l1);
								}
							}
						}
					}
				}
			}
			
		}
		
		if(this.worldObj.isRemote)
			for(int i = 0; i < lightnings.size(); ++i)
			{
				Lightning lt = lightnings.get(i);
				if(lt.renderTicksExisted >= 50)
					this.lightnings.remove(i);
				if(i >= 21)
					this.lightnings.remove(i);
			}
	
	}
    
	@Override
    public void readFromNBT(NBTTagCompound i)
    {
		super.readFromNBT(i);
    }
	
	@Override
    public void writeToNBT(NBTTagCompound i)
    {
    	super.writeToNBT(i);
    }
	
	@Override
	public Aspect getSuctionType(ForgeDirection face) {
		return face.ordinal() != this.getRotation() ? Aspect.ORDER : null;
	}
	
	@Override
	public int getSuctionAmount(ForgeDirection face) {
		return face.ordinal() != this.getRotation() ? 64 : 0;
	}
	
	@Override
	public boolean isConnectable(ForgeDirection face) {
		return face.ordinal() != this.getRotation();
	}
	
	@Override
	public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
		if(this.getSuctionType(face) == aspect)
			return this.addEssentia(aspect, amount, face);
		return 0;
	}
	
	@Override
	public void setSuction(Aspect aspect, int amount) {
		
	}
}
