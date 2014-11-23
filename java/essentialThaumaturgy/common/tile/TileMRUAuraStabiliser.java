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

public class TileMRUAuraStabiliser extends TileHasMRU{
	
	public TileMRUAuraStabiliser()
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
			Aspect randomAspect = null;
			if(worldObj.rand.nextBoolean())
			{
				randomAspect = Aspect.getPrimalAspects().get(worldObj.rand.nextInt(Aspect.getPrimalAspects().size()));
			}else
			{
				randomAspect = Aspect.getCompoundAspects().get(worldObj.rand.nextInt(Aspect.getCompoundAspects().size()));
			}
			try
			{
				Class tcPacketHandler = Class.forName("thaumcraft.common.lib.network.PacketHandler");
				Class tcResearchManager = Class.forName("thaumcraft.common.lib.research.ResearchManager");
				Class thaumcraft = Class.forName("thaumcraft.common.Thaumcraft");
				Class packetAspectPool = Class.forName("thaumcraft.common.lib.network.playerdata.PacketAspectPool");
				Field proxy = thaumcraft.getField("proxy");
				proxy.setAccessible(true);
				Class proxyClass = proxy.get(null).getClass();
				Field playerKnowledge = proxyClass.getField("playerKnowledge");
				playerKnowledge.setAccessible(true);
				Class playerKnowledgeClass = playerKnowledge.get(proxy.get(null)).getClass();
				Object knowledgeLib = playerKnowledge.get(proxy.get(null));
				Method addAspectPool = playerKnowledgeClass.getMethod("addAspectPool", String.class,Aspect.class,short.class);
				Method sheduleSave = tcResearchManager.getMethod("scheduleSave", EntityPlayer.class);
				addAspectPool.setAccessible(true);
				sheduleSave.setAccessible(true);
				Field INSTANCEFld = tcPacketHandler.getField("INSTANCE");
				Object INSTANCE = INSTANCEFld.get(null);
				Method getAspectPoolFor = playerKnowledgeClass.getMethod("getAspectPoolFor", String.class,Aspect.class);
				getAspectPoolFor.setAccessible(true);
				SimpleNetworkWrapper snw = (SimpleNetworkWrapper) INSTANCE;
				if(this.worldObj.rand.nextFloat() < 0.0025F && !this.worldObj.isRemote)
				{
					List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1, yCoord+1, zCoord+1).expand(6D, 3D, 6D));
					for(EntityPlayer p : players)
					{
						addAspectPool.invoke(knowledgeLib, p.getCommandSenderName(),randomAspect,Short.valueOf((short) 1));
						sheduleSave.invoke(null, p);
						short aspectPool = Short.parseShort(getAspectPoolFor.invoke(knowledgeLib, p.getCommandSenderName(),randomAspect).toString());
						Object PacketAspectPool = packetAspectPool.getConstructor(String.class,Short.class,Short.class).newInstance(randomAspect.getTag(),(short)1,aspectPool);
						snw.sendTo((IMessage)PacketAspectPool, (EntityPlayerMP) p);
					}
					this.setMRU(this.getMRU()-500);
				}
				Method sparkle = proxyClass.getMethod("sparkle", float.class,float.class,float.class,float.class,int.class,float.class);
				sparkle.setAccessible(true);
				sparkle.invoke(proxy.get(null),xCoord+0.55F+MathUtils.randomFloat(this.worldObj.rand)/2F,yCoord+1F,zCoord+0.55F+MathUtils.randomFloat(this.worldObj.rand)/2F,1F,0,-0.2F);
				
			}catch(Exception e)
			{
				e.printStackTrace();
				return;
			}
		}
	}
	
}
