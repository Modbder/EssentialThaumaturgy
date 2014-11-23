package essentialThaumaturgy.common.init;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.List;

import ec3.api.ApiCore;
import ec3.api.EnumStructureType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class EC3Init {
	
	public static void init()
	{
		try
		{
			Class ecUtilsClass = Class.forName("ec3.utils.common.ECUtils");
			Field hashTableFld = ecUtilsClass.getDeclaredField("allowedBlocks");
			hashTableFld.setAccessible(true);
			Hashtable<EnumStructureType,List<Block>> hashMap = (Hashtable<EnumStructureType, List<Block>>) hashTableFld.get(null);
			List<Block> allowedBlocksLst = hashMap.get(EnumStructureType.MRUCUContaigementChamber);
			allowedBlocksLst.add(BlocksInit.glassChaos);
			allowedBlocksLst.add(BlocksInit.glassFrozen);
			allowedBlocksLst.add(BlocksInit.infusedStone);
			hashMap.put(EnumStructureType.MRUCUContaigementChamber, allowedBlocksLst);
			hashTableFld.set(null, hashMap);
			
			Method regBlk = ecUtilsClass.getMethod("registerBlockResistance", Block.class,int.class,float.class);
			regBlk.setAccessible(true);
			regBlk.invoke(null, BlocksInit.glassChaos,0,7F);
			regBlk.invoke(null, BlocksInit.glassFrozen,0,7F);
			regBlk.invoke(null, BlocksInit.infusedStone,0,30F);
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
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
						String classPath = stk.getItem().getClass().toString();
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
