package essentialThaumaturgy.common.item;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ec3.api.IMRUPressence;
import essentialThaumaturgy.common.utils.ETUtils;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;

public class ItemFocusMRULifedrain extends ItemFocusBasic{

    public String getSortingHelper(ItemStack itemstack)
    {
        return (new StringBuilder()).append("MRULD").append(super.getSortingHelper(itemstack)).toString();
    }

    public int getFocusColor()
    {
        return 0xc48e8a;
    }
    
	@Override
	public void addInformation(ItemStack stack,EntityPlayer player, List list, boolean par4) 
	{
		super.addInformation(stack, player, list, par4);
		list.add(StatCollector.translateToLocal(isVisCostPerTick()?"item.mru.cost2":"item.mru.cost1"));
		DecimalFormat myFormatter = new DecimalFormat("#####.##");
		String amount = myFormatter.format(50);
		list.add("\u00A7r x "+ amount);
	}
    
    public AspectList getVisCost()
    {
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
		if(movingobjectposition == null)
		{
			movingobjectposition = new MovingObjectPosition(player);
			movingobjectposition.entityHit = null;
		}
		List<EntityLivingBase> mobs = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(player.posX+player.getLookVec().xCoord-1D, player.posY+player.getLookVec().yCoord-1D, player.posZ+player.getLookVec().zCoord-1D, player.posX+player.getLookVec().xCoord+1D, player.posY+player.getLookVec().yCoord+1D, player.posZ+player.getLookVec().zCoord+1D));
		if(!mobs.isEmpty() && mobs.size() > 1)
		{
			for(int i = 0; i < mobs.size(); ++i)
			{
				EntityLivingBase mob = mobs.get(i);
				if(mob != null && !(mob instanceof EntityPlayer))
				{
					movingobjectposition.entityHit = mob;
					movingobjectposition.typeOfHit = MovingObjectType.ENTITY;
					break;
				}
			}
		}
		if(movingobjectposition != null && movingobjectposition.typeOfHit == MovingObjectType.ENTITY)
		{
			try
			{
				if(player.getFoodStats().needFood())
			        if (ETUtils.fociDrainAspectsAndMRU(itemstack, player, getVisCost(), 100, true, false))
			        {
			        	movingobjectposition.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(player), 1);
			        	if(((EntityLivingBase) movingobjectposition.entityHit).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD)
			        		player.getFoodStats().addStats(1, 1F);
			        	else
			        		player.attackEntityFrom(DamageSource.starve, 5);
			        	player.swingItem();
			        }
			}catch(Exception e)
			{
				return itemstack;
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
    
    public static final AspectList cost = new AspectList().add(Aspect.WATER,800).add(Aspect.EARTH, 800).add(Aspect.ENTROPY, 800);

	@Override
	public int getFocusColor(ItemStack focusstack) {
		// TODO Auto-generated method stub
		return 0xc48e8a;
	}

	@Override
	public AspectList getVisCost(ItemStack focusstack) {
		// TODO Auto-generated method stub
		return cost;
	}

	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack,
			int rank) {
		// TODO Auto-generated method stub
		return null;
	}
}
