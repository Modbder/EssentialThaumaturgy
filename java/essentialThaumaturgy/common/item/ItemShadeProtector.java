package essentialThaumaturgy.common.item;

import thaumcraft.api.IRunicArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import baubles.api.BaubleType;
import baubles.api.IBauble;

public class ItemShadeProtector extends Item implements IBauble, IRunicArmor{

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		// TODO Auto-generated method stub
		return BaubleType.AMULET;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRunicCharge(ItemStack itemstack) {
		// TODO Auto-generated method stub
		return 10;
	}

}
