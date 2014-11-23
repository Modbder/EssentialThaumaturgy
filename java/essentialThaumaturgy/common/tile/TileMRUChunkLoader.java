package essentialThaumaturgy.common.tile;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import DummyCore.Utils.MathUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import essentialThaumaturgy.common.utils.ETUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;

public class TileMRUChunkLoader extends TileHasMRU{
	
	public TileMRUChunkLoader()
	{
		this.setMaxMRU(5000F);
		this.setSlotsNum(1);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if(this.getMRU() - 5 >= 0 && !this.worldObj.isRemote)
		{
			ETUtils.requestChunkLoading(xCoord/16, zCoord/16, this.worldObj.provider.dimensionId);
			this.setMRU(this.getMRU()-5);
		}else if(!this.worldObj.isRemote)
		{
			ETUtils.requestChunkUnloading(xCoord/16, zCoord/16, this.worldObj.provider.dimensionId);
		}
		if(this.getMRU() - 5 >= 0)
		{
			try
			{
				Class thaumcraft = Class.forName("thaumcraft.common.Thaumcraft");
				Field proxy = thaumcraft.getField("proxy");
				proxy.setAccessible(true);
				Class proxyClass = proxy.get(null).getClass();
				Method sparkle = proxyClass.getMethod("sparkle", float.class,float.class,float.class,float.class,int.class,float.class);
				sparkle.setAccessible(true);
				sparkle.invoke(proxy.get(null),xCoord+0.5F,yCoord+1.1F,zCoord+0.5F,1F,1,-0.2F);
			}catch(Exception e)
			{
				return;
			}
		}
	}
	
}
