package essentialThaumaturgy.common.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemPearl extends Item{
	
	public String[] names = new String[]{"overchargedPearl","mruLeftovers","primodialLeftovers","pureParticle","shadeParticle"};
	public IIcon[] icons = new IIcon[5];
	
	public ItemPearl()
	{
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int i)
    {
        return icons[i];
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stk, World w, EntityPlayer player)
    {

    	if(stk.getItemDamage() == 0)
    	{
	    	player.inventory.decrStackSize(player.inventory.currentItem, 1);
	    	if(stk.stackSize <= 0)stk = null;
	    	ItemStack primodLefts = new ItemStack(this,3+w.rand.nextInt(3),2);
	    	ItemStack mruLefts = new ItemStack(this,2+w.rand.nextInt(4),1);
	    	ItemStack pureLefts = new ItemStack(this,1,3);
	    	ItemStack shadeLefts = new ItemStack(this,1,4);
	    	if(!player.inventory.addItemStackToInventory(primodLefts))player.dropPlayerItemWithRandomChoice(primodLefts, false);
	    	if(!player.inventory.addItemStackToInventory(mruLefts))player.dropPlayerItemWithRandomChoice(mruLefts, false);
	    	if(!player.inventory.addItemStackToInventory(pureLefts))player.dropPlayerItemWithRandomChoice(pureLefts, false);
	    	if(!player.inventory.addItemStackToInventory(shadeLefts))player.dropPlayerItemWithRandomChoice(shadeLefts, false);
    	}
        return stk;
    }

    @Override
    public String getUnlocalizedName(ItemStack stk)
    {
        return "item.essenthaum." + this.names[stk.getItemDamage()];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs tab, List l)
    {
    	for(int i1 = 0; i1 < this.names.length; ++i1)
    		l.add(new ItemStack(i, 1, i1));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg)
    {
    	this.itemIcon = reg.registerIcon("essenthaum:"+this.names[0]);
    	for(int i = 0; i < 5; ++i)
    		this.icons[i] = reg.registerIcon("essenthaum:"+names[i]);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stk)
    {
    	return stk.getItemDamage() == 0 ? EnumRarity.epic : EnumRarity.rare;
    }

}
