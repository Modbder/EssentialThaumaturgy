package essentialThaumaturgy.common.tile;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import DummyCore.Utils.MathUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;

public class TileMRUAuraDestructor extends TileHasMRU{
	
	public TileMRUAuraDestructor()
	{
		this.setMaxMRU(5000F);
		this.setSlotsNum(2);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if(this.getMRU() - 500 >= 0)
		{
			try
			{
				Class thaumcraft = Class.forName("thaumcraft.common.Thaumcraft");
				Field proxy = thaumcraft.getField("proxy");
				proxy.setAccessible(true);
				Class proxyClass = proxy.get(null).getClass();
				
				Method addWarp = thaumcraft.getMethod("addWarpToPlayer", EntityPlayer.class,int.class,boolean.class);
				addWarp.setAccessible(true);
				if(this.worldObj.rand.nextFloat() < 0.0025F && !this.worldObj.isRemote)
				{
					List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1, yCoord+1, zCoord+1).expand(6D, 3D, 6D));
					for(EntityPlayer p : players)
					{
						addWarp.invoke(null, p,3+p.worldObj.rand.nextInt(5),true);
					}
					this.setMRU(this.getMRU()-500);
				}
				
				Method sparkle = proxyClass.getMethod("sparkle", float.class,float.class,float.class,float.class,int.class,float.class);
				sparkle.setAccessible(true);
				sparkle.invoke(proxy.get(null),xCoord+0.55F+MathUtils.randomFloat(this.worldObj.rand)/2F,yCoord+1F,zCoord+0.55F+MathUtils.randomFloat(this.worldObj.rand)/2F,1F,5,-0.2F);
				
			}catch(Exception e)
			{
				e.printStackTrace();
				return;
			}
		}
	}
	
}
