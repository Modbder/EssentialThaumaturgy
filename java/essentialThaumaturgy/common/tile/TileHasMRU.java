package essentialThaumaturgy.common.tile;

import java.lang.reflect.Method;
import java.util.UUID;

import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import ec3.api.ITEHasMRU;
import ec3.api.ITERequiresMRU;

public class TileHasMRU extends TileEntity implements ITERequiresMRU, IInventory{

	public int syncTick;
	int mru;
	int maxMRU = 100;
	UUID uuid = UUID.randomUUID();
	float balance;
	public int innerRotation;
	private ItemStack[] items = new ItemStack[1];
	
	public void setSlotsNum(int i)
	{
		items = new ItemStack[i];
	}
	
	@Override
    public void readFromNBT(NBTTagCompound i)
    {
		super.readFromNBT(i);
		MiscUtils.loadInventory(this, i);
		try
		{
			Class ecUtils = Class.forName("ec3.utils.common.ECUtils");
			Method loadMRUState = ecUtils.getMethod("loadMRUState", ITEHasMRU.class,NBTTagCompound.class);
			loadMRUState.setAccessible(true);
			loadMRUState.invoke(null, this,i);
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
		
    }
	
	@Override
    public void writeToNBT(NBTTagCompound i)
    {
    	super.writeToNBT(i);
		MiscUtils.saveInventory(this, i);
		try
		{
			Class ecUtils = Class.forName("ec3.utils.common.ECUtils");
			Method saveMRUState = ecUtils.getMethod("saveMRUState", ITEHasMRU.class,NBTTagCompound.class);
			saveMRUState.setAccessible(true);
			saveMRUState.invoke(null, this,i);
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
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
			Class ecUtils = Class.forName("ec3.utils.common.ECUtils");
			Method mruIn = ecUtils.getMethod("mruIn", TileEntity.class,int.class);
			mruIn.setAccessible(true);
			mruIn.invoke(null, this,0);
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
	
    public static int[] getCoords(ItemStack stack)
    {
    	return MiscUtils.getStackTag(stack).getIntArray("pos");
    }
	
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
	
	@Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, -10, nbttagcompound);
    }
	
	@Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
		if(net.getNetHandler() instanceof INetHandlerPlayClient)
			if(pkt.func_148853_f() == -10)
				this.readFromNBT(pkt.func_148857_g());
    }
	
	@Override
	public int getMRU() {
		// TODO Auto-generated method stub
		return mru;
	}

	@Override
	public int getMaxMRU() {
		// TODO Auto-generated method stub
		return maxMRU;
	}

	@Override
	public boolean setMRU(int i) {
		mru = i;
		return true;
	}

	@Override
	public float getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}

	@Override
	public boolean setBalance(float f) {
		balance = f;
		return true;
	}

	@Override
	public boolean setMaxMRU(float f) {
		maxMRU = (int) f;
		return true;
	}

	@Override
	public UUID getUUID() {
		// TODO Auto-generated method stub
		return uuid;
	}
	
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return this.items.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1) {
		// TODO Auto-generated method stub
		return this.items[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
        if (this.items[par1] != null)
        {
            ItemStack itemstack;

            if (this.items[par1].stackSize <= par2)
            {
                itemstack = this.items[par1];
                this.items[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.items[par1].splitStack(par2);

                if (this.items[par1].stackSize == 0)
                {
                    this.items[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.items[par1] != null)
        {
            ItemStack itemstack = this.items[par1];
            this.items[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }


    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.items[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }
    

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return "ec3.container.generic";
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return this.worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.dimension == this.worldObj.provider.dimensionId;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
			return true;
	}
}
