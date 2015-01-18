package essentialThaumaturgy.common.init;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectSourceHelper;
import static thaumcraft.api.aspects.Aspect.*;

public class AspectsInit {
	
	public static void initAspects()
	{
		MRU = new Aspect("mru", 0x470035, new Aspect[]{MAGIC,ENERGY}, new ResourceLocation("essenthaum","textures/aspects/mru.png"), 1);
		RADIATION = new Aspect("radiation", 0x470035, new Aspect[]{MRU,MOTION}, new ResourceLocation("essenthaum","textures/aspects/radiation.png"), 1);
		MATRIX = new Aspect("matrix", 0x470035, new Aspect[]{MRU,MAN}, new ResourceLocation("essenthaum","textures/aspects/matrix.png"), 1);
		
		//=======================================================BLOCKS===========================================================//
		ThaumcraftApi.registerObjectTag(getIS("block:magicPlating",0), new AspectList().add(MRU, 1).add(METAL, 2).add(ARMOR, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:fortifiedGlass",0), new AspectList().add(MRU, 1).add(METAL, 1).add(CRYSTAL, 2).add(ARMOR, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:ecController",0), new AspectList().add(MRU, 5).add(METAL, 4).add(MECHANISM, 8).add(MATRIX, 2).add(ORDER, 4).add(CRAFT, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:ecAcceptor",0), new AspectList().add(MRU, 3).add(RADIATION, 2).add(METAL, 4).add(MECHANISM, 8).add(MATRIX, 1).add(EXCHANGE, 4).add(CRAFT, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:ecEjector",0), new AspectList().add(MRU, 2).add(RADIATION, 3).add(METAL, 4).add(MECHANISM, 8).add(MATRIX, 1).add(EXCHANGE, 4).add(CRAFT, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:ecHoldingChamber",0), new AspectList().add(MRU, 6).add(VOID, 4).add(METAL, 4).add(MECHANISM, 4).add(MATRIX, 1).add(AURA, 1).add(CRAFT, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:ecStateChecker",0), new AspectList().add(MRU, 3).add(SENSES, 4).add(METAL, 4).add(MECHANISM, 4).add(MATRIX, 1).add(CRYSTAL, 3).add(CRAFT, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:rayTower",-1), new AspectList().add(RADIATION, 2).add(EXCHANGE, 2).add(MECHANISM, 2).add(CRYSTAL, 2));
		ThaumcraftApi.registerObjectTag(getIS("block:moonWell",0), new AspectList().add(MRU, 8).add(MATRIX, 2).add(WEATHER, 2).add(MECHANISM, 6).add(LIGHT, 2));
		ThaumcraftApi.registerObjectTag(getIS("block:solarPrism",0), new AspectList().add(CRYSTAL, 8).add(LIGHT, 8).add(AURA, 2).add(MRU, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:sunRayAbsorber",0), new AspectList().add(MRU, 8).add(MATRIX, 2).add(WEATHER, 2).add(MECHANISM, 8).add(LIGHT, 8));
		ThaumcraftApi.registerObjectTag(getIS("block:coldStone",0), new AspectList().add(COLD, 3).add(EARTH, 3));
		ThaumcraftApi.registerObjectTag(getIS("block:coldDistillator",0), new AspectList().add(MRU, 8).add(MATRIX, 2).add(COLD, 8).add(MECHANISM, 8).add(RADIATION, 2).add(AURA, 1));
		ThaumcraftApi.registerObjectTag(getIS("block:naturalFurnace",0), new AspectList().add(MRU, 4).add(MECHANISM, 4).add(FIRE, 4).add(RADIATION, 2).add(AURA, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:heatGenerator",0), new AspectList().add(FIRE, 6).add(MRU, 4).add(MECHANISM, 4));
		ThaumcraftApi.registerObjectTag(getIS("block:enderGenerator",0), new AspectList().add(MRU, 4).add(MECHANISM, 4).add(ELDRITCH, 4).add(ENTROPY, 4).add(RADIATION, 2).add(WEAPON, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:magicianTable",0), new AspectList().add(RADIATION, 6).add(MRU, 6).add(MECHANISM, 2).add(CRAFT, 2).add(ENERGY, 2).add(ELDRITCH, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:fortifiedStone",0), new AspectList().add(MRU, 1).add(METAL, 1).add(EARTH, 2).add(ARMOR, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:magicalQuarry",0), new AspectList().add(MINE, 8).add(MECHANISM, 8).add(MRU, 6).add(ENTROPY, 4).add(RADIATION, 2).add(MATRIX, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:monsterClinger",0), new AspectList().add(TRAP, 8).add(MECHANISM, 8).add(MRU, 6).add(MOTION, 4).add(RADIATION, 2).add(MATRIX, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:potionSpreader",0), new AspectList().add(AIR, 8).add(MECHANISM, 8).add(MRU, 6).add(HEAL, 4).add(VOID, 3).add(RADIATION, 2).add(MATRIX, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:magicalEnchanter",0), new AspectList().add(MAGIC, 8).add(MECHANISM, 8).add(MRU, 6).add(MIND, 4).add(RADIATION, 2).add(MATRIX, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:monsterHarvester",0), new AspectList().add(WEAPON, 8).add(MECHANISM, 8).add(MRU, 6).add(TRAP, 4).add(RADIATION, 2).add(MATRIX, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:magicalRepairer",0), new AspectList().add(TOOL, 8).add(MECHANISM, 8).add(MRU, 6).add(CRAFT, 4).add(RADIATION, 2).add(MATRIX, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:matrixAbsorber",0), new AspectList().add(MATRIX, 4).add(ENTROPY, 4).add(EXCHANGE, 2));
		ThaumcraftApi.registerObjectTag(getIS("block:radiatingChamber",0), new AspectList().add(RADIATION, 8).add(MECHANISM, 6).add(MRU, 4).add(CRAFT, 4).add(METAL, 2).add(GREED, 2).add(AURA, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:magmaticSmeltery",0), new AspectList().add(FIRE, 16).add(MINE, 6).add(MRU, 4).add(MECHANISM, 4).add(AURA, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:magicalJukebox",0), new AspectList().add(MATRIX, 6).add(AIR, 6).add(AURA, 6));
		ThaumcraftApi.registerObjectTag(getIS("block:elementalCrystal",-1), new AspectList().add(CRYSTAL, 16).add(FIRE, 6).add(WATER, 6).add(EARTH, 6).add(AIR, 6).add(ENTROPY, 6).add(ORDER, 6).add(MATRIX, 6).add(MRU, 6).add(RADIATION, 6).add(AURA, 6).add(ARMOR, 6).add(ELDRITCH, 2).add(DARKNESS, 2).add(LIGHT, 2).add(VOID, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:crystalFormer",0), new AspectList().add(MRU, 8).add(CRYSTAL, 8).add(MECHANISM, 8).add(CRAFT, 4).add(RADIATION, 2).add(MATRIX, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:crystalController",0), new AspectList().add(MRU, 8).add(CRYSTAL, 8).add(MECHANISM, 8).add(ORDER, 4).add(RADIATION, 2).add(MATRIX, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:crystalExtractor",0), new AspectList().add(MRU, 8).add(CRYSTAL, 8).add(MECHANISM, 8).add(ENTROPY,4).add(RADIATION, 2).add(MATRIX, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:chargingChamber",0), new AspectList().add(MRU, 8).add(CRYSTAL, 8).add(MECHANISM, 8).add(EXCHANGE,4).add(RADIATION, 4).add(MATRIX, 2));
		ThaumcraftApi.registerObjectTag(getIS("block:voidStone",0), new AspectList().add(VOID, 8).add(ELDRITCH, 8).add(ORDER, 6).add(MRU, 4).add(MATRIX, 2).add(EARTH, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:voidGlass",0), new AspectList().add(VOID, 8).add(ELDRITCH, 8).add(ORDER, 6).add(MRU, 4).add(MATRIX, 2).add(CRYSTAL, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:concrete",0), new AspectList().add(EARTH, 2).add(ORDER, 1).add(FIRE, 1).add(RADIATION, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:cacti",0), new AspectList().add(EARTH, 2).add(CROP, 1).add(POISON, 1).add(RADIATION, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:dreadDirt",0), new AspectList().add(RADIATION, 3).add(EARTH, 2));
		ThaumcraftApi.registerObjectTag(getIS("block:root",0), new AspectList().add(EARTH, 2).add(TREE, 1));
		ThaumcraftApi.registerObjectTag(getIS("block:magicalTeleporter",0), new AspectList().add(MRU, 8).add(MATRIX, 6).add(TRAVEL, 6).add(ELDRITCH, 6).add(VOID, 6).add(MECHANISM, 6));
		ThaumcraftApi.registerObjectTag(getIS("block:magicalFurnace",0), new AspectList().add(MRU, 8).add(MATRIX, 6).add(FIRE, 6).add(ELDRITCH, 6).add(VOID, 6).add(MECHANISM, 6));
		ThaumcraftApi.registerObjectTag(getIS("block:emberForge",0), new AspectList().add(MRU, 8).add(MATRIX, 6).add(DARKNESS, 6).add(ELDRITCH, 6).add(VOID, 6).add(MECHANISM, 6).add(FIRE, 3));
		ThaumcraftApi.registerObjectTag(getIS("block:levitator",0), new AspectList().add(AIR, 12).add(FLIGHT, 8).add(RADIATION, 6).add(MECHANISM, 6).add(ELDRITCH, 6).add(VOID, 4).add(MRU, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:spreader",0), new AspectList().add(AIR, 12).add(FLIGHT, 8).add(RADIATION, 6).add(MECHANISM, 6).add(ELDRITCH, 6).add(VOID, 4).add(MRU, 0));
		ThaumcraftApi.registerObjectTag(getIS("block:torch",0), new AspectList().add(LIGHT, 6).add(RADIATION, 1).add(MRU, 0));
		
		ThaumcraftApi.registerObjectTag(new ItemStack(BlocksInit.radiatedNode,1,0), new AspectList().add(MRU, 8).add(RADIATION, 8).add(MATRIX, 8).add(MAGIC, 8).add(TAINT, 8));

		//=======================================================ITEMS============================================================//
		ThaumcraftApi.registerObjectTag(getIS("item:mruMover1",0), new AspectList().add(MRU, 1).add(MOTION, 1).add(CRYSTAL, 1));
		ThaumcraftApi.registerObjectTag(getIS("item:magicMonocle",0), new AspectList().add(MRU, 5).add(SENSES, 4).add(CRYSTAL,3).add(AURA, 2).add(GREED, 1));
		ThaumcraftApi.registerObjectTag(getIS("item:soulStone",0), new AspectList().add(MATRIX, 5).add(SENSES, 5).add(EARTH, 1));
		ThaumcraftApi.registerObjectTag(getIS("item:matrixProj",0), new AspectList().add(MATRIX, 8).add(SENSES, 8));
		ThaumcraftApi.registerObjectTag(getIS("item:matrixProj",1), new AspectList().add(MATRIX, 8).add(SENSES, 8).add(FIRE, 8).add(MOTION, 8).add(RADIATION, 3));
		ThaumcraftApi.registerObjectTag(getIS("item:matrixProj",2), new AspectList().add(MATRIX, 8).add(SENSES, 8).add(COLD, 8).add(TRAP, 8).add(RADIATION, 3));
		ThaumcraftApi.registerObjectTag(getIS("item:matrixProj",3), new AspectList().add(MATRIX, 8).add(SENSES, 8).add(MAGIC, 8).add(MRU, 8).add(RADIATION, 3));
		ThaumcraftApi.registerObjectTag(getIS("item:titanite",0), new AspectList().add(VOID, 4).add(ELDRITCH, 4).add(EARTH, 4));
		ThaumcraftApi.registerObjectTag(getIS("item:twinkling_titanite",0), new AspectList().add(VOID, 4).add(ELDRITCH, 4).add(LIGHT, 4).add(EARTH, 4));
		ThaumcraftApi.registerObjectTag(getIS("item:magicalSlag",0), new AspectList().add(MRU, 2).add(MAGIC, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",0), new AspectList().add(ORDER, 8).add(MRU, 6));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",3), new AspectList().add(MRU, 3).add(AURA, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",4), new AspectList().add(GREED, 8).add(CRYSTAL, 2).add(MRU, 6));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",5), new AspectList().add(MRU, 3).add(METAL, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",6), new AspectList().add(VOID, 2).add(WATER, 2).add(MRU, 2).add(RADIATION, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",7), new AspectList().add(ORDER, 2).add(MRU, 2).add(EARTH, 2).add(ARMOR, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",8), new AspectList().add(ORDER, 4).add(ELDRITCH, 4).add(MRU, 2).add(ARMOR, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",9), new AspectList().add(ORDER, 2).add(CRYSTAL, 2).add(MRU, 2).add(ARMOR, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",10), new AspectList().add(ORDER, 2).add(GREED, 2).add(MRU, 2).add(ARMOR, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",11), new AspectList().add(ORDER, 4).add(MECHANISM, 2).add(MRU, 2).add(ARMOR, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",12), new AspectList().add(MRU, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",13), new AspectList().add(MRU, 4));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",14), new AspectList().add(MRU, 8));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",15), new AspectList().add(MRU, 12));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",16), new AspectList().add(MRU, 16));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",20), new AspectList().add(MRU, 6).add(RADIATION, 3).add(ORDER, 2).add(AURA, 3));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",32), new AspectList().add(CRYSTAL, 16).add(LIGHT, 6).add(RADIATION, 2).add(AURA, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",34), new AspectList().add(MRU, 2).add(METAL, 2).add(ARMOR, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",35), new AspectList().add(VOID, 6).add(ELDRITCH, 6).add(RADIATION, 6));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",36), new AspectList().add(VOID, 6).add(ELDRITCH, 6).add(RADIATION, 6));
		ThaumcraftApi.registerObjectTag(getIS("item:genericItem",37), new AspectList().add(VOID, 6).add(ELDRITCH, 6).add(RADIATION, 6));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",0), new AspectList().add(MRU, 1).add(FIRE, 1).add(AURA, 1));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",4), new AspectList().add(MRU, 2).add(FIRE, 2).add(AURA, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",8), new AspectList().add(MRU, 4).add(FIRE, 4).add(AURA, 4));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",12), new AspectList().add(MRU, 8).add(FIRE, 8).add(AURA, 8));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",1), new AspectList().add(MRU, 1).add(WATER, 1).add(AURA, 1));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",5), new AspectList().add(MRU, 2).add(WATER, 2).add(AURA, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",9), new AspectList().add(MRU, 4).add(WATER, 4).add(AURA, 4));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",13), new AspectList().add(MRU, 8).add(WATER, 8).add(AURA, 8));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",2), new AspectList().add(MRU, 1).add(EARTH, 1).add(AURA, 1));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",6), new AspectList().add(MRU, 2).add(EARTH, 2).add(AURA, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",10), new AspectList().add(MRU, 4).add(EARTH, 4).add(AURA, 4));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",14), new AspectList().add(MRU, 8).add(EARTH, 8).add(AURA, 8));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",3), new AspectList().add(MRU, 1).add(AIR, 1).add(AURA, 1));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",7), new AspectList().add(MRU, 2).add(AIR, 2).add(AURA, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",11), new AspectList().add(MRU, 4).add(AIR, 4).add(AURA, 4));
		ThaumcraftApi.registerObjectTag(getIS("item:essence",15), new AspectList().add(MRU, 8).add(AIR, 8).add(AURA, 8));
		ThaumcraftApi.registerObjectTag(getIS("item:storage",0), new AspectList().add(MRU, 1).add(VOID, 1));
		ThaumcraftApi.registerObjectTag(getIS("item:storage",1), new AspectList().add(MRU, 2).add(VOID, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:storage",2), new AspectList().add(MRU, 4).add(VOID, 4));
		ThaumcraftApi.registerObjectTag(getIS("item:storage",3), new AspectList().add(MRU, 8).add(VOID, 8));
		ThaumcraftApi.registerObjectTag(getIS("item:storage",4), new AspectList().add(MRU, 16).add(VOID, 16));
		ThaumcraftApi.registerObjectTag(getIS("item:magicalDigger",0), new AspectList().add(MRU, 8).add(MINE, 8).add(ENTROPY, 6).add(SENSES, 4).add(RADIATION, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:spawnerCollector",0), new AspectList().add(MRU, 8).add(EXCHANGE, 8).add(ORDER, 6).add(RADIATION, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:staffOfLife",0), new AspectList().add(MRU, 8).add(LIFE, 8).add(PLANT, 6).add(RADIATION, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:biomeWand",0), new AspectList().add(MRU, 8).add(EXCHANGE, 8).add(WEATHER, 6).add(TRAVEL, 4).add(RADIATION, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:emeraldHeart",0), new AspectList().add(MRU, 8).add(HEAL, 8).add(RADIATION, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:magicalShield",0), new AspectList().add(MRU, 8).add(ARMOR, 8).add(RADIATION, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:spikyShield",0), new AspectList().add(MRU, 8).add(ARMOR, 8).add(WEAPON, 4).add(RADIATION, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:magicWaterBottle",0), new AspectList().add(MRU, 8).add(LIFE, 8).add(ORDER, 8).add(RADIATION, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:magicalPorkchop",0), new AspectList().add(MRU, 8).add(HUNGER, 8).add(FLESH, 8).add(RADIATION, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:magicalWings",0), new AspectList().add(MRU, 8).add(FLIGHT, 8).add(AIR, 8).add(RADIATION, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:magmaticStaff",0), new AspectList().add(MRU, 8).add(FIRE, 8).add(EXCHANGE, 8).add(RADIATION, 4));
		ThaumcraftApi.registerObjectTag(getIS("item:magicalLantern",0), new AspectList().add(MRU, 8).add(LIGHT, 8).add(RADIATION, 2));
		ThaumcraftApi.registerObjectTag(getIS("item:magnetizingStaff",0), new AspectList().add(MRU, 8).add(MOTION, 8).add(RADIATION, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:bottledWind",0), new AspectList().add(MRU, 2).add(AIR, 2).add(RADIATION, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:imprisonedWind",0), new AspectList().add(MRU, 2).add(AIR, 4).add(RADIATION, 0));
		ThaumcraftApi.registerObjectTag(getIS("item:windKeeper",0), new AspectList().add(MRU, 2).add(AIR, 8).add(RADIATION, 0));
		
		ThaumcraftApi.registerObjectTag(new ItemStack(ItemsInit.pearl,1,1), new AspectList().add(MRU,8));
		ThaumcraftApi.registerObjectTag(new ItemStack(ItemsInit.pearl,1,2), new AspectList().add(AIR,8).add(WATER, 8).add(EARTH, 8).add(FIRE, 8).add(ORDER, 8).add(ENTROPY,8));
		ThaumcraftApi.registerObjectTag(new ItemStack(ItemsInit.pearl,1,3), new AspectList().add(ORDER,8).add(MAGIC, 8).add(TAINT, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(ItemsInit.pearl,1,4), new AspectList().add(MRU,8).add(VOID, 8).add(DARKNESS, 8));
	}
	
	public static Aspect
	MRU,
	RADIATION,
	MATRIX;
	
	
	public static ItemStack getIS(String name, int meta)
	{
		if(meta == -1)
			meta = OreDictionary.WILDCARD_VALUE;
		int index = name.indexOf(":");
		if(index == -1)
		{
			List<ItemStack> retLst = OreDictionary.getOres(name);
			if(retLst != null && !retLst.isEmpty())
			{
				for(ItemStack stk : retLst)
				{
					if(stk != null)
					{
						String classPath = stk.getItem().getClass().getCanonicalName();
						if(classPath.toLowerCase().contains("ec3"))
						{
							return stk;
						}
					}
				}
			}
		}else
		{
			try
			{
				String type = name.substring(0, index);
				Class searchClass = null;
				if(type.toLowerCase().equals("block"))
				{
					searchClass = Class.forName("ec3.common.block.BlocksCore");
				}
				if(type.toLowerCase().equals("item"))
				{
					searchClass = Class.forName("ec3.common.item.ItemsCore");
				}
				if(searchClass != null)
				{
					String fieldToSearch = name.substring(index+1, name.length());
					Field fld = searchClass.getDeclaredField(fieldToSearch);
					if(fld != null)
					{
						fld.setAccessible(true);
						Object obj = fld.get(null);
						if(obj != null)
						{
							if(obj instanceof Block)
								return new ItemStack((Block)obj,1,meta);
							if(obj instanceof Item)
								return new ItemStack((Item)obj,1,meta);
						}
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
