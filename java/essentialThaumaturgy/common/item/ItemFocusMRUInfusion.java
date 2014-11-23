package essentialThaumaturgy.common.item;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ec3.api.IMRUPressence;
import essentialThaumaturgy.common.utils.ETUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;

public class ItemFocusMRUInfusion extends ItemFocusBasic{

    public String getSortingHelper(ItemStack itemstack)
    {
        return (new StringBuilder()).append("MRUI").append(super.getSortingHelper(itemstack)).toString();
    }

    public int getFocusColor()
    {
        return 0x939191;
    }
    
	@Override
	public IIcon getFocusDepthLayerIcon() {
		return depthIcon;
	}
    
	@Override
	public void addInformation(ItemStack stack,EntityPlayer player, List list, boolean par4) 
	{
		super.addInformation(stack, player, list, par4);
		list.add(StatCollector.translateToLocal(isVisCostPerTick()?"item.mru.cost2":"item.mru.cost1"));
		DecimalFormat myFormatter = new DecimalFormat("#####.##");
		String amount = myFormatter.format(300);
		list.add("\u00A7r x "+ amount);
	}
    

    public AspectList getVisCost()
    {
        Random rand = new Random(System.currentTimeMillis() / 400L);
        int fire = rand.nextInt(2);
        int water = rand.nextInt(2);
        int earth = rand.nextInt(2);
        int air = rand.nextInt(2);
        int order = rand.nextInt(2);
        int entropy = rand.nextInt(2);
        AspectList cost = (new AspectList()).add(Aspect.WATER, (50 + rand.nextInt(5) * 250)*water).add(Aspect.AIR, (50 + rand.nextInt(5) * 250)*air).add(Aspect.EARTH, (50 + rand.nextInt(5) * 250)*earth).add(Aspect.FIRE, (50 + rand.nextInt(5) * 250)*fire).add(Aspect.ORDER, (50 + rand.nextInt(5) * 250)*order).add(Aspect.ENTROPY, (50 + rand.nextInt(5) * 250)*entropy);
        return cost;
    }
    
    public boolean isVisCostPerTick()
    {
        return false;
    }
    
    public WandFocusAnimation getAnimation()
    {
        return WandFocusAnimation.WAVE;
    }
    
    public boolean acceptsEnchant(int id)
    {
    	return false;
    }
    
	@Override
	public ItemStack onFocusRightClick(ItemStack itemstack, World world,
			EntityPlayer player, MovingObjectPosition movingobjectposition) {
		if(movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
		{
			int bX = movingobjectposition.blockX;
			int bY = movingobjectposition.blockY;
			int bZ = movingobjectposition.blockZ;
			Block blk = world.getBlock(bX, bY, bZ);
			if(blk == Blocks.cobblestone)
			{
				if(ETUtils.fociDrainAspectsAndMRU(itemstack, player, getVisCost(), 300, true, false))
				{
					player.swingItem();
					world.setBlock(bX, bY, bZ, Block.getBlockFromItem(getISTC("block:blockCustomOre",1+world.rand.nextInt(6)).getItem()), 1+world.rand.nextInt(6), 3);
					world.playSoundAtEntity(player, "thaumcraft:ice", 0.3F, 0.8F + world.rand.nextFloat() * 0.1F);
				}
			}
		}
		return itemstack;
	}
    
	@Override
	public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player,int count)
	{
		
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
        this.itemIcon = p_94581_1_.registerIcon(this.getIconString());
        this.icon = this.itemIcon;
        depthIcon = p_94581_1_.registerIcon("thaumcraft:focus_primal_depth");
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
	
	
	public static ItemStack getISTC(String name, int meta)
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
					searchClass = Class.forName("thaumcraft.common.config.ConfigBlocks");
				}
				if(type.toLowerCase().equals("item"))
				{
					searchClass = Class.forName("thaumcraft.common.config.ConfigItems");
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
	
	public IIcon depthIcon;
    
    public static final AspectList cost = new AspectList().add(Aspect.EARTH,200).add(Aspect.ORDER, 200).add(Aspect.ENTROPY, 50);
}
