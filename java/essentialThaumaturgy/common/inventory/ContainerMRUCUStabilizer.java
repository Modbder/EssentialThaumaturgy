package essentialThaumaturgy.common.inventory;

import essentialThaumaturgy.common.tile.TileFluxWiper;
import essentialThaumaturgy.common.tile.TileMRUCUStabilizer;
import essentialThaumaturgy.common.tile.TileMRUSpreader;
import essentialThaumaturgy.common.tile.TileNodeIrradiator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerMRUCUStabilizer extends Container{
	
    private TileMRUCUStabilizer in;

    public ContainerMRUCUStabilizer(InventoryPlayer par1InventoryPlayer, TileEntity par2)
    {
        this.in = (TileMRUCUStabilizer) par2;
        this.addSlotToContainer(new Slot(in, 0, 29, 32));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18,84+ i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		// TODO Auto-generated method stub
		return in.isUseableByPlayer(p_75145_1_);
	}

	@Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (par2 != 1 && par2 != 0)
            {
                if (!this.mergeItemStack(itemstack1, 0, 1, false))
				{
				    return null;
				}
            }
            else if (!this.mergeItemStack(itemstack1, 1, 37, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}
