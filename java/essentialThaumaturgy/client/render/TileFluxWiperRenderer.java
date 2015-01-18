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
import essentialThaumaturgy.common.tile.TileFluxWiper;
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

public class TileFluxWiperRenderer extends TileEntitySpecialRenderer
{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x,
			double y, double z, float partialTicks) {
		int rotation = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
		TileFluxWiper wiper = (TileFluxWiper) tile;
		if(rotation == 1)
		{
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
				GL11.glTranslated(x+0.5F, y, z+0.5F);
				fluxWiperModel.renderPart("pCube1");
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesRod);
				GL11.glTranslated(x+0.5F, y+wiper.upIndex, z+0.5F);
				fluxWiperModel.renderPart("pCube2");
			GL11.glPopMatrix();
		}
		if(rotation == 0)
		{
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
				GL11.glTranslated(x+0.5F, y+1, z+0.5F);
				GL11.glRotatef(180, 1, 0, 0);
				fluxWiperModel.renderPart("pCube1");
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesRod);
				GL11.glTranslated(x+0.5F, y-wiper.upIndex+1F, z+0.5F);
				GL11.glRotatef(180, 1, 0, 0);
				fluxWiperModel.renderPart("pCube2");
			GL11.glPopMatrix();
		}
		if(rotation == 4)
		{
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
				GL11.glTranslated(x+1, y+0.5F, z+0.5F);
				GL11.glRotatef(90, 0, 0, 1);
				fluxWiperModel.renderPart("pCube1");
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesRod);
				GL11.glTranslated(x-wiper.upIndex+1F, y+0.5F, z+0.5F);
				GL11.glRotatef(90, 0, 0, 1);
				fluxWiperModel.renderPart("pCube2");
			GL11.glPopMatrix();
		}
		if(rotation == 5)
		{
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
				GL11.glTranslated(x, y+0.5F, z+0.5F);
				GL11.glRotatef(-90, 0, 0, 1);
				fluxWiperModel.renderPart("pCube1");
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesRod);
				GL11.glTranslated(x+wiper.upIndex, y+0.5F, z+0.5F);
				GL11.glRotatef(-90, 0, 0, 1);
				fluxWiperModel.renderPart("pCube2");
			GL11.glPopMatrix();
		}
		
		if(rotation == 2)
		{
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
				GL11.glTranslated(x+0.5D, y+0.5F, z+1);
				GL11.glRotatef(-90, 1, 0, 0);
				fluxWiperModel.renderPart("pCube1");
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesRod);
				GL11.glTranslated(x+0.5F, y+0.5F, z-wiper.upIndex+1F);
				GL11.glRotatef(-90, 1, 0, 0);
				fluxWiperModel.renderPart("pCube2");
			GL11.glPopMatrix();
		}
		if(rotation == 3)
		{
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
				GL11.glTranslated(x+0.5D, y+0.5F, z);
				GL11.glRotatef(90, 1, 0, 0);
				fluxWiperModel.renderPart("pCube1");
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesRod);
				GL11.glTranslated(x+0.5F, y+0.5F, z+wiper.upIndex);
				GL11.glRotatef(90, 1, 0, 0);
				fluxWiperModel.renderPart("pCube2");
			GL11.glPopMatrix();
		}
	}
	
	public static final ResourceLocation texturesBase = new ResourceLocation("essenthaum","textures/models/fluxWiperBase.png");
	public static final ResourceLocation texturesRod = new ResourceLocation("essenthaum","textures/models/fluxWiperRod.png");
	public static final IModelCustom fluxWiperModel = AdvancedModelLoader.loadModel(new ResourceLocation("essenthaum","models/FluxWiper.obj"));
}
