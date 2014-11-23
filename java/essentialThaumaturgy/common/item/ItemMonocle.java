package essentialThaumaturgy.common.item;

import java.util.List;

import ec3.api.IItemAllowsSeeingMRUCU;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemMonocle extends ItemArmor
implements IRepairable, IVisDiscountGear, IRevealer, IGoggles, IRunicArmor, IItemAllowsSeeingMRUCU{

	public ItemMonocle(ArmorMaterial p_i45325_1_, int p_i45325_2_,int p_i45325_3_)
	{
		super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
		this.setMaxDamage(320);
	}
	
    public int getRunicCharge(ItemStack itemstack)
    {
        return 0;
    }
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        super.addInformation(stack, player, list, par4);
        list.add((new StringBuilder()).append(EnumChatFormatting.DARK_PURPLE).append(StatCollector.translateToLocal("tc.visdiscount")).append(": ").append(getVisDiscount(stack, player, null)).append("%").toString());
    }
    
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return "essenthaum:textures/armor/monocle.png";
    }
    
    public EnumRarity getRarity(ItemStack itemstack)
    {
        return EnumRarity.rare;
    }
    
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
    {
        return 7;
    }

    public boolean showNodes(ItemStack itemstack, EntityLivingBase player)
    {
        return true;
    }
    
    public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player)
    {
        return true;
    }

}
