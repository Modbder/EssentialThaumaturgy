package essentialThaumaturgy.common.item;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.IScribeTools;
import ec3.api.IItemRequiresMRU;

public class ItemMagicScribingTools extends Item implements IItemRequiresMRU, IScribeTools{
	
	public ItemMagicScribingTools()
	{
		this.setMaxDamage(5001);
		this.setNoRepair();
		this.setMaxStackSize(1);
		
	}
	
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int indexInInventory, boolean isCurrentItem)
	{
		try
		{
			if(entity instanceof EntityPlayer)
			{
				Class ECUtils = Class.forName("ec3.utils.common.ECUtils");
				Method tryToDecreaseMRUInStorage = ECUtils.getMethod("tryToDecreaseMRUInStorage", EntityPlayer.class,int.class);
				if(this.getMRU(itemStack)+10 <= this.getMaxMRU(itemStack) && Boolean.parseBoolean(tryToDecreaseMRUInStorage.invoke(null, entity,-10).toString()))
				{
					this.setMRU(itemStack, 10);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	@Override
    public void setDamage(ItemStack stack, int damage)
    {
		int damageDifference = stack.getItemDamage() - damage;
		damageDifference *= 50;
        super.setDamage(stack, stack.getItemDamage() - damageDifference);
    }
	
	public void setDamageIgnore(ItemStack stack, int damage)
	{
		try
		{
			Field itemDamage = ItemStack.class.getDeclaredFields()[5];
			itemDamage.setAccessible(true);
			if(damage < 0)damage = 0;
			itemDamage.set(stack, damage);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
		
	}
	
	@Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
    {
		par3List.add(this.getMRU(par1ItemStack) + "/" + this.getMaxMRU(par1ItemStack) + " MRU");
    }
	
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs tab, List l)
    {
    	l.add(new ItemStack(i,1,4999));
    	l.add(new ItemStack(i,1,0));
    }

	@Override
	public boolean setMRU(ItemStack stack, int amount) {
		if(stack.getItemDamage()-amount < stack.getMaxDamage())
		{
			this.setDamageIgnore(stack, stack.getItemDamage()-amount);
			return true;
		}
		return false;
	}

	@Override
	public int getMRU(ItemStack stack) {
		// TODO Auto-generated method stub
		return 5000-stack.getItemDamage();
	}

	@Override
	public int getMaxMRU(ItemStack stack) {
		// TODO Auto-generated method stub
		return stack.getMaxDamage()-1;
	}

}
