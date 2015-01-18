package essentialThaumaturgy.client.render;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.lwjgl.opengl.GL11;

import DummyCore.Utils.Coord3D;
import DummyCore.Utils.DummyDistance;
import DummyCore.Utils.Lightning;
import DummyCore.Utils.MiscUtils;
import ec3.utils.common.PlayerTickHandler;
import essentialThaumaturgy.common.init.AspectsInit;
import essentialThaumaturgy.common.tile.TileMRUSpreader;
import essentialThaumaturgy.common.tile.TileNodeIrradiator;
import essentialThaumaturgy.common.tile.TileRadiatedNode;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;

public class TileMRUSpreaderRenderer extends TileEntitySpecialRenderer
{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x,
			double y, double z, float partialTicks) {
		int rotation = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
		TileMRUSpreader spreader = (TileMRUSpreader) tile;
		
		if(rotation == 1 || rotation == 0)
		{
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y-0.1F, z+0.5F);
				GL11.glScalef(0.5F, 1, 0.5F);
				Minecraft.getMinecraft().renderEngine.bindTexture(textures);
				irradiatorModel.renderAll();
			GL11.glPopMatrix();
		}
		
		if(rotation == 3 || rotation == 2)
		{
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z-0.1F);
				GL11.glScalef(0.5F, 0.5F, 1F);
				GL11.glRotatef(90, 1, 0, 0);
				Minecraft.getMinecraft().renderEngine.bindTexture(textures);
				irradiatorModel.renderAll();
			GL11.glPopMatrix();
		}
		
		if(rotation == 4 || rotation == 5)
		{
			GL11.glPushMatrix();
				GL11.glTranslated(x+1.1F, y+0.5F, z+0.5F);
				GL11.glScalef(1F, 0.5F, 0.5F);
				GL11.glRotatef(90, 0, 0, 1);
				Minecraft.getMinecraft().renderEngine.bindTexture(textures);
				irradiatorModel.renderAll();
			GL11.glPopMatrix();
		}
		
		GL11.glPushMatrix();
	    	for(int i = 0; i < spreader.lightnings.size(); ++i)
	    	{
	    		Lightning l = spreader.lightnings.get(i);
	    		l.render(x, y, z, partialTicks);
	    	}
	    GL11.glPopMatrix();
	    
		GL11.glPushMatrix();
	    	for(Lightning l : spreader.lights.values())
	    	{
	    		l.render(x, y, z, partialTicks);
	    	}
    	GL11.glPopMatrix();
	}
	
	public static final ResourceLocation textures = new ResourceLocation("essenthaum","textures/models/mruSpreaderBottom.png");
	public static final IModelCustom irradiatorModel = AdvancedModelLoader.loadModel(new ResourceLocation("essenthaum","models/MRUSpreader.obj"));
}
