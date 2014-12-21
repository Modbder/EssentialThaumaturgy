package essentialThaumaturgy.common.item;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ec3.api.IMRUPressence;
import essentialThaumaturgy.common.utils.ETUtils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;

public class ItemFocusMRUSanitiser extends ItemFocusBasic{

    public String getSortingHelper(ItemStack itemstack)
    {
        return (new StringBuilder()).append("MRUS").append(super.getSortingHelper(itemstack)).toString();
    }
    
	@Override
	public void addInformation(ItemStack stack,EntityPlayer player, List list, boolean par4) 
	{
		super.addInformation(stack, player, list, par4);
		list.add(StatCollector.translateToLocal(isVisCostPerTick()?"item.mru.cost2":"item.mru.cost1"));
		DecimalFormat myFormatter = new DecimalFormat("#####.##");
		String amount = myFormatter.format(1);
		list.add("\u00A7r x "+ amount);
	}
	
    public int getFocusColor()
    {
        return 0xa5b3cd;
    }
    
    public AspectList getVisCost()
    {
        return cost;
    }
    
    public boolean isVisCostPerTick()
    {
        return true;
    }
    
    public WandFocusAnimation getAnimation()
    {
        return WandFocusAnimation.CHARGE;
    }
    
    public boolean acceptsEnchant(int id)
    {
    	if(id==ThaumcraftApi.enchantPotency) return true;
    	return false;
    }
    
	@Override
	public ItemStack onFocusRightClick(ItemStack itemstack, World world,
			EntityPlayer player, MovingObjectPosition movingobjectposition) {
		player.setItemInUse(itemstack, 0x7fffffff);
		return itemstack;
	}
    
	@Override
	public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player,int count)
	{
		try{
	        Class itemWandCasting = Class.forName("thaumcraft.common.items.wands.ItemWandCasting");
	        Object wand = itemstack.getItem();
	        if(!ETUtils.fociDrainAspectsAndMRU(itemstack, player, getVisCost(), 1, true, false))
	        {
	            player.stopUsingItem();
	            return;
	        }
	        Method getPotency = itemWandCasting.getMethod("getFocusPotency", ItemStack.class);
	        int potencyLevel = 1+Integer.parseInt(getPotency.invoke(wand, itemstack).toString());
	        Class toSearchPotions = Class.forName("thaumcraft.common.config.Config");
	        Field potionPureID = toSearchPotions.getField("potionWarpWardID");
	        potionPureID.setAccessible(true);
	        int id = potionPureID.getInt(null);
	        int duration = 5*potencyLevel;
	        if(player.isPotionActive(id))
	        {
	        	duration += player.getActivePotionEffect(Potion.potionTypes[id]).getDuration();
	        }
	        player.removePotionEffect(id);
	        player.addPotionEffect(new PotionEffect(id,duration,0));
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
        this.itemIcon = p_94581_1_.registerIcon(this.getIconString());
        this.icon = this.itemIcon;
    }
    
    public static final AspectList cost = new AspectList().add(Aspect.ORDER,2).add(Aspect.ENTROPY, 2);

	@Override
	public int getFocusColor(ItemStack focusstack) {
		// TODO Auto-generated method stub
		return 0xa5b3cd;
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
		return new FocusUpgradeType[]{FocusUpgradeType.potency};
	}
}
