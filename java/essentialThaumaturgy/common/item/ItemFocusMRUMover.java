package essentialThaumaturgy.common.item;

import java.lang.reflect.Method;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ec3.api.IMRUPressence;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;

public class ItemFocusMRUMover extends ItemFocusBasic{

    public String getSortingHelper(ItemStack itemstack)
    {
        return (new StringBuilder()).append("MRUM").append(super.getSortingHelper(itemstack)).toString();
    }
    
    public int getFocusColor()
    {
        return 0x5f2180;
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
        Method m = itemWandCasting.getMethod("consumeAllVis", ItemStack.class,EntityPlayer.class,AspectList.class,boolean.class,boolean.class);
        m.setAccessible(true);
        if(!Boolean.parseBoolean(m.invoke(wand, itemstack, player, getVisCost(), true, false).toString()))
        {
            player.stopUsingItem();
            return;
        }
		Vec3 mainLookVec = player.getLookVec();
    	int mDistance = 0;
		for(int i = 0; i < 20; ++i)
		{
			Vec3 additionalVec = mainLookVec.addVector(mainLookVec.xCoord*i, mainLookVec.yCoord*i, mainLookVec.zCoord*i);
			List entityList = player.worldObj.getEntitiesWithinAABB(IMRUPressence.class, AxisAlignedBB.getBoundingBox(player.posX+additionalVec.xCoord-1, player.posY+additionalVec.yCoord-2, player.posZ+additionalVec.zCoord-1, player.posX+additionalVec.xCoord+1, player.posY+additionalVec.yCoord+2, player.posZ+additionalVec.zCoord+1));
			if(!entityList.isEmpty())
			{
				Entity presence = (Entity) entityList.get(player.worldObj.rand.nextInt(entityList.size()));
				player.worldObj.spawnParticle("portal", player.posX, player.posY, player.posZ, presence.posX-player.posX, presence.posY-player.posY-1, presence.posZ-player.posZ);
				float moveX = 0;
				float moveY = 0;
				float moveZ = 0;
				moveX = (float) -(mainLookVec.xCoord/10);
				moveY = (float) -(mainLookVec.yCoord/10);
				moveZ = (float) -(mainLookVec.zCoord/10);
				//if(!presence.worldObj.isRemote)
				{
					if(!player.isSneaking())
						presence.setPositionAndRotation(presence.posX+moveX, presence.posY+moveY, presence.posZ+moveZ, 0, 0);
					else
						presence.setPositionAndRotation(presence.posX-moveX, presence.posY-moveY, presence.posZ-moveZ, 0, 0);
				}
					
				break;
			}
		}
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
    
    public static final AspectList cost = new AspectList().add(Aspect.AIR,2);
}
