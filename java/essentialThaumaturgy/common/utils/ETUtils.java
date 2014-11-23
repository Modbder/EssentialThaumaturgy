package essentialThaumaturgy.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.List;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import essentialThaumaturgy.common.core.EssentialThaumaturgy;
import essentialThaumaturgy.common.init.ItemsInit;
import DummyCore.Utils.DummyDataUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import thaumcraft.api.aspects.AspectList;

public class ETUtils {
	
	public static final Hashtable<Integer,Ticket> etTickets = new Hashtable<Integer,Ticket>();
	
	public static final ETUtils INSTANCE = new ETUtils();
	
	public static TicketHandler handler;
	
	public static TicketHandler getTHObj()
	{
		if(handler == null)
			handler = INSTANCE.new TicketHandler();
		return handler;
	}
	
	public ETUtils()
	{
		handler = new TicketHandler();
	}
	
	public class TicketHandler implements LoadingCallback
	{

		@Override
		public void ticketsLoaded(List<Ticket> tickets, World world) {
			
		}
		
	}
	
	@SubscribeEvent
	public void onWorldLoaded(WorldEvent.Load event)
	{
		World w = event.world;
		if(w != null && !w.isRemote)
		{
			if(!etTickets.containsKey(w.provider.dimensionId))
			{
				etTickets.put(w.provider.dimensionId, ForgeChunkManager.requestTicket(EssentialThaumaturgy.core, w, Type.NORMAL));
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerDeath(LivingHurtEvent event)
	{
		EntityLivingBase base = event.entityLiving;
		if(base instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) base;
			if(event.ammount >= player.getHealth())
			{
				IInventory inv = BaublesApi.getBaubles(player);
				for(int i = 0; i < inv.getSizeInventory(); ++i)
				{
					ItemStack stk = inv.getStackInSlot(i);
					if(stk != null && stk.getItem() == ItemsInit.shade_protector)
					{
						inv.setInventorySlotContents(i, null);
						player.heal(player.getMaxHealth());
						event.setCanceled(true);
						return;
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onWorldUnloaded(WorldEvent.Unload event)
	{
		try
		{
		for(Ticket t : etTickets.values())
		{	
			if(t != null)
				ForgeChunkManager.releaseTicket(t);
		}
		etTickets.clear();
		}catch(Exception e)
		{
			e.printStackTrace();return;
		}
	}
	
	public static Ticket getTicketForDimension(int dimID)
	{
		return etTickets.get(dimID);
	}
	
	public static boolean requestChunkLoading(int chunkX, int chunkZ, int dimID)
	{
		Ticket t = getTicketForDimension(dimID);
		if(t != null)
		{
			//And yes, I know, that you can force chunkloading as many times as you want - it does not matter, but, guess what? A safe check is never a bad thing!
			for(ChunkCoordIntPair pair : t.getChunkList())
			{
				if(pair.chunkXPos == chunkX && pair.chunkZPos == chunkZ)
				{
					return false;
				}
			}
			ForgeChunkManager.forceChunk(t, new ChunkCoordIntPair(chunkX,chunkZ));
		}else
		{
			return false;
		}
		return true;
	}
	
	public static void requestChunkUnloading(int chunkX, int chunkZ, int dimID)
	{
		Ticket t = getTicketForDimension(dimID);
		if(t != null)
			ForgeChunkManager.unforceChunk(t, new ChunkCoordIntPair(chunkX,chunkZ));
	}
	
	public static boolean fociDrainAspectsAndMRU(ItemStack item,EntityPlayer p,AspectList aspects,int mruDrained,boolean doDrain,boolean isRemote)
	{
		try
		{
	        Class itemWandCasting = Class.forName("thaumcraft.common.items.wands.ItemWandCasting");
	        Object wand = item.getItem();
	        Method m = itemWandCasting.getMethod("consumeAllVis", ItemStack.class,EntityPlayer.class,AspectList.class,boolean.class,boolean.class);
	        m.setAccessible(true);
	        boolean retBool_0 = Boolean.parseBoolean(m.invoke(wand, item, p, aspects, true, false).toString());
	        int ubMRU = Integer.parseInt(DummyDataUtils.getDataForPlayer(p.getDisplayName(), "essentialcraft", "ubmruEnergy"));
	        if(ubMRU >= mruDrained)
	        {
	        	ubMRU -= mruDrained;
	        	DummyDataUtils.setDataForPlayer(p.getDisplayName(), "essentialcraft", "ubmruEnergy", Integer.toString(ubMRU));
	        	float flt = (float)mruDrained/1000F;
	        	if(p.worldObj.rand.nextFloat() < flt && !p.worldObj.isRemote)
	        	{
	        		int effectToAdd = p.worldObj.rand.nextInt(3);
	        		switch(effectToAdd)
	        		{
	        			case 0:
	        			{
	        				Class potionsEC3 = Class.forName("ec3.common.registry.PotionRegistry");
	        				Field corruptionPotion = potionsEC3.getField("mruCorruptionPotion");
	        				corruptionPotion.setAccessible(true);
	        				Object potion = corruptionPotion.get(null);
	        				int currentDuration = 20*30;
	        				if(p.getActivePotionEffect((Potion) potion) != null)
	        				{
	        					currentDuration += p.getActivePotionEffect((Potion) potion).getDuration();
	        				}
	        				int amplifier = currentDuration / 1200;
	        				p.removePotionEffect(p.getActivePotionEffect((Potion) potion).getPotionID());
	        				p.addPotionEffect(new PotionEffect(((Potion)potion).id,currentDuration,amplifier));
	        				return retBool_0;
	        			}
	        			case 1:
	        			{
	        				Class thaumcraftMainCls = Class.forName("thaumcraft.common.Thaumcraft");
	        				Method addWarp = thaumcraftMainCls.getMethod("addWarpToPlayer", EntityPlayer.class,int.class,boolean.class);
	        				addWarp.setAccessible(true);
	        				addWarp.invoke(null, p,3+p.worldObj.rand.nextInt(5),true);
	        				return retBool_0;
	        			}
	        			case 2:
	        			{
	        				p.addPotionEffect(new PotionEffect(Potion.hunger.id,100,3));
	        				p.addPotionEffect(new PotionEffect(Potion.wither.id,100,3));
	        				p.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC+""+EnumChatFormatting.DARK_PURPLE+StatCollector.translateToLocal("warp.text.15")));
	        				return retBool_0;
	        			}
	        		}
	        	}
	        	return retBool_0;
	        }else
	        	return false;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}	

}
