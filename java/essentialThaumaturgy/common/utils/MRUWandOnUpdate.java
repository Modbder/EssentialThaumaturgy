package essentialThaumaturgy.common.utils;

import java.lang.reflect.Method;

import DummyCore.Utils.MiscUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.wands.IWandRodOnUpdate;

public class MRUWandOnUpdate implements IWandRodOnUpdate{

	@Override
	public void onUpdate(ItemStack wand, EntityPlayer player) {
		try
		{
			Class wandCls = Class.forName("thaumcraft.common.items.wands.ItemWandCasting");
			NBTTagCompound tg = MiscUtils.getStackTag(wand);
			Method getMaxVis = wandCls.getMethod("getMaxVis", ItemStack.class);
			int aspectCap = Integer.parseInt(getMaxVis.invoke(wand.getItem(), wand).toString());
			int fire = tg.getInteger("ignis");
			int water = tg.getInteger("aqua");
			int earth = tg.getInteger("terra");
			int air = tg.getInteger("aer");
			int entropy = tg.getInteger("perditio");
			int order = tg.getInteger("ordo");
			if(fire < aspectCap && decreaseMRU(player,10))
			{
				++fire;
				tg.setInteger("ignis", fire);
			}
			
			if(water < aspectCap && decreaseMRU(player,10))
			{
				++water;
				tg.setInteger("aqua", water);
			}
			
			if(earth < aspectCap && decreaseMRU(player,10))
			{
				++earth;
				tg.setInteger("terra", earth);
			}
			
			if(air < aspectCap && decreaseMRU(player,10))
			{
				++air;
				tg.setInteger("aer", air);
			}
			
			if(order < aspectCap && decreaseMRU(player,10))
			{
				++order;
				tg.setInteger("ordo", order);
			}
			
			if(entropy < aspectCap && decreaseMRU(player,10))
			{
				++entropy;
				tg.setInteger("perditio", entropy);
			}
		}catch(Exception e)
		{
			e.printStackTrace();return;
		}
	}
	
	public boolean decreaseMRU(EntityPlayer p, int amount)
	{
		try
		{
			Class ECUtils = Class.forName("ec3.utils.common.ECUtils");
			Method tryToDecreaseMRUInStorage = ECUtils.getMethod("tryToDecreaseMRUInStorage", EntityPlayer.class,int.class);
			return Boolean.parseBoolean(tryToDecreaseMRUInStorage.invoke(null, p,-amount).toString());
		}catch(Exception e)
		{
			e.printStackTrace();return false;
		}
	}

}
