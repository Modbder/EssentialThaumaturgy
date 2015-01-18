package essentialThaumaturgy.client.render;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.lwjgl.opengl.GL11;

import essentialThaumaturgy.common.init.AspectsInit;
import essentialThaumaturgy.common.tile.TileRadiatedNode;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;

public class TileRadiatedNodeRenderer extends TileEntitySpecialRenderer
{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x,
			double y, double z, float partialTicks) {
		net.minecraft.entity.EntityLivingBase viewer = Minecraft.getMinecraft().thePlayer;
		try
		{
			Class TileNodeRenderer = Class.forName("thaumcraft.client.renderers.tile.TileNodeRenderer");
			Class UtilsFX = Class.forName("thaumcraft.client.lib.UtilsFX");
			Method renderNode = TileNodeRenderer.getMethod("renderNode", EntityLivingBase.class,double.class,boolean.class,boolean.class,float.class,int.class,int.class,int.class,float.class,AspectList.class,NodeType.class,NodeModifier.class);
			renderNode.invoke(null, viewer,64D,true,false,1.0F,tile.xCoord,tile.yCoord,tile.zCoord,partialTicks,new AspectList().add(AspectsInit.MRU, 80).add(Aspect.MAGIC, 80).add(Aspect.PLANT, 10),((TileRadiatedNode)tile).getNodeType(),((TileRadiatedNode)tile).getNodeModifier());
			GL11.glPushMatrix();
	        GL11.glAlphaFunc(516, 0.003921569F);
	        GL11.glEnable(3042);
	        GL11.glBlendFunc(770, 1);
	        long nt = System.nanoTime();
	        UtilsFX.getMethod("bindTexture", String.class).invoke(null,tx1);
	        int frames = Integer.parseInt(UtilsFX.getMethod("getTextureAnimationSize",String.class).invoke(null,tx1).toString());
	        int i = (int)(((double)(nt / 0x2625a00L) + x) % (double)frames);
	        Method renderFacingQuad = UtilsFX.getMethod("renderFacingQuad", double.class,double.class,double.class,float.class,float.class,float.class,int.class,int.class,float.class,int.class);
	        for(int f = 0; f < 10; ++f)
	        	renderFacingQuad.invoke(null,(double)tile.xCoord + 0.5D, (double)tile.yCoord + 0.5D, (double)tile.zCoord + 0.5D, f*36, 1F, 1F, frames, i, partialTicks, 0xff00ff);
	        GL11.glDisable(3042);
	        GL11.glAlphaFunc(516, 0.1F);
	        GL11.glPopMatrix();
    		Class TileNodeStabilizer = Class.forName("thaumcraft.common.tiles.TileNodeStabilizer");
    		Class TileNodeConverter = Class.forName("thaumcraft.common.tiles.TileNodeConverter");
    		TileEntity te = tile.getWorldObj().getTileEntity(tile.xCoord, tile.yCoord - 1, tile.zCoord);
    		if(te.getClass().isAssignableFrom(TileNodeStabilizer))
    		{
    			Field count = TileNodeStabilizer.getField("count");
    			count.set(te, 37);
    		}
    		te = tile.getWorldObj().getTileEntity(tile.xCoord, tile.yCoord + 1, tile.zCoord);
    		if(te.getClass().isAssignableFrom(TileNodeConverter))
    		{
    			Field count = TileNodeConverter.getField("count");
    			count.set(te, 50);
    		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	static String tx1 = "textures/items/lightningringv.png";
}
