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
import essentialThaumaturgy.common.tile.TileMRUCUAbsorber;
import essentialThaumaturgy.common.tile.TileMRUCUStabilizer;
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;

public class TileMRUCUAbsorberRenderer extends TileEntitySpecialRenderer
{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x,
			double y, double z, float partialTicks) {
		int rotation = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
		TileMRUCUAbsorber absorber = (TileMRUCUAbsorber) tile;
		float rIndex = tile.getWorldObj().getWorldTime()%180F * 2;
		if(!tile.getWorldObj().isBlockIndirectlyGettingPowered(tile.xCoord, tile.yCoord, tile.zCoord))
			rIndex = 0F;
		if(rotation == 1)
		{
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
				GL11.glTranslated(x+0.5F, y, z+0.5F);
				stabilizerModel.renderPart("pCube1");
			GL11.glPopMatrix();
			
			if(absorber.extension < 1.0F)
			{
				GL11.glPushMatrix();
					Minecraft.getMinecraft().renderEngine.bindTexture(texturesCore);
					GL11.glTranslated(x+0.5F, y-(1.0F-absorber.extension)/4, z+0.5F);
					stabilizerModel.renderPart("pCube2");
				GL11.glPopMatrix();
			}else
			{
				GL11.glPushMatrix();
					Minecraft.getMinecraft().renderEngine.bindTexture(texturesCore);
					GL11.glTranslated(x+0.5F, y, z+0.5F);
					GL11.glRotatef(rIndex, 0, 1, 0);
					stabilizerModel.renderPart("pCube2");
				GL11.glPopMatrix();
			}
		}
		
		if(rotation == 0)
		{
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
				GL11.glTranslated(x+0.5F, y+1, z+0.5F);
				GL11.glRotatef(180, 1, 0, 0);
				stabilizerModel.renderPart("pCube1");
			GL11.glPopMatrix();
			
			if(absorber.extension < 1.0F)
			{
				GL11.glPushMatrix();
					Minecraft.getMinecraft().renderEngine.bindTexture(texturesCore);
					GL11.glTranslated(x+0.5F, y+1+(1.0F-absorber.extension)/4, z+0.5F);
					GL11.glRotatef(180, 1, 0, 0);
					stabilizerModel.renderPart("pCube2");
				GL11.glPopMatrix();
			}else
			{
				GL11.glPushMatrix();
					Minecraft.getMinecraft().renderEngine.bindTexture(texturesCore);
					GL11.glTranslated(x+0.5F, y+1, z+0.5F);
					GL11.glRotatef(180, 1, 0, 0);
					GL11.glRotatef(rIndex, 0, 1, 0);
					stabilizerModel.renderPart("pCube2");
				GL11.glPopMatrix();
			}
		}
		
		if(rotation == 2)
		{
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
				GL11.glTranslated(x+0.5F, y+0.5F, z+1F);
				GL11.glRotatef(-90, 1, 0, 0);
				stabilizerModel.renderPart("pCube1");
			GL11.glPopMatrix();
			
			if(absorber.extension < 1.0F)
			{
				GL11.glPushMatrix();
					Minecraft.getMinecraft().renderEngine.bindTexture(texturesCore);
					GL11.glTranslated(x+0.5F, y+0.5F, z+1+(1.0F-absorber.extension)/4);
					GL11.glRotatef(-90, 1, 0, 0);
					stabilizerModel.renderPart("pCube2");
				GL11.glPopMatrix();
			}else
			{
				GL11.glPushMatrix();
					Minecraft.getMinecraft().renderEngine.bindTexture(texturesCore);
					GL11.glTranslated(x+0.5F, y+0.5F, z+1F);
					GL11.glRotatef(-90, 1, 0, 0);
					GL11.glRotatef(rIndex, 0, 1, 0);
					stabilizerModel.renderPart("pCube2");
				GL11.glPopMatrix();
			}
		}
		
		if(rotation == 3)
		{
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
				GL11.glTranslated(x+0.5F, y+0.5F, z);
				GL11.glRotatef(90, 1, 0, 0);
				stabilizerModel.renderPart("pCube1");
			GL11.glPopMatrix();
			
			if(absorber.extension < 1.0F)
			{
				GL11.glPushMatrix();
					Minecraft.getMinecraft().renderEngine.bindTexture(texturesCore);
					GL11.glTranslated(x+0.5F, y+0.5F, z-(1.0F-absorber.extension)/4);
					GL11.glRotatef(90, 1, 0, 0);
					stabilizerModel.renderPart("pCube2");
				GL11.glPopMatrix();
			}else
			{
				GL11.glPushMatrix();
					Minecraft.getMinecraft().renderEngine.bindTexture(texturesCore);
					GL11.glTranslated(x+0.5F, y+0.5F, z);
					GL11.glRotatef(90, 1, 0, 0);
					GL11.glRotatef(rIndex, 0, 1, 0);
					stabilizerModel.renderPart("pCube2");
				GL11.glPopMatrix();
			}
		}
		
		if(rotation == 4)
		{
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
				GL11.glTranslated(x+1F, y+0.5F, z+0.5F);
				GL11.glRotatef(90, 0, 0, 1);
				stabilizerModel.renderPart("pCube1");
			GL11.glPopMatrix();
			
			if(absorber.extension < 1.0F)
			{
				GL11.glPushMatrix();
					Minecraft.getMinecraft().renderEngine.bindTexture(texturesCore);
					GL11.glTranslated(x+1F+(1.0F-absorber.extension)/4, y+0.5F, z+0.5F);
					GL11.glRotatef(90, 0, 0, 1);
					stabilizerModel.renderPart("pCube2");
				GL11.glPopMatrix();
			}else
			{
				GL11.glPushMatrix();
					Minecraft.getMinecraft().renderEngine.bindTexture(texturesCore);
					GL11.glTranslated(x+1F, y+0.5F, z+0.5F);
					GL11.glRotatef(90, 0, 0, 1);
					GL11.glRotatef(rIndex, 0, 1, 0);
					stabilizerModel.renderPart("pCube2");
				GL11.glPopMatrix();
			}
		}
		
		if(rotation == 5)
		{
			GL11.glPushMatrix();
				Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
				GL11.glTranslated(x, y+0.5F, z+0.5F);
				GL11.glRotatef(-90, 0, 0, 1);
				stabilizerModel.renderPart("pCube1");
			GL11.glPopMatrix();
			
			if(absorber.extension < 1.0F)
			{
				GL11.glPushMatrix();
					Minecraft.getMinecraft().renderEngine.bindTexture(texturesCore);
					GL11.glTranslated(x-(1.0F-absorber.extension)/4, y+0.5F, z+0.5F);
					GL11.glRotatef(-90, 0, 0, 1);
					stabilizerModel.renderPart("pCube2");
				GL11.glPopMatrix();
			}else
			{
				GL11.glPushMatrix();
					Minecraft.getMinecraft().renderEngine.bindTexture(texturesCore);
					GL11.glTranslated(x, y+0.5F, z+0.5F);
					GL11.glRotatef(-90, 0, 0, 1);
					GL11.glRotatef(rIndex, 0, 1, 0);
					stabilizerModel.renderPart("pCube2");
				GL11.glPopMatrix();
			}
		}
		if(tile.getWorldObj().isBlockIndirectlyGettingPowered(tile.xCoord, tile.yCoord, tile.zCoord))
		{
			if(absorber.getMRUCU() != null && absorber.getMRUCU().getMRU() > 1000)
			{
				if(absorber.extension >= 1.0F)
				{
					Entity mrucu = (Entity) absorber.getMRUCU();
					renderBeam(partialTicks,x,y,z,0.5F,0.5F,0.5F,mrucu.posX - (tile.xCoord+0.5F),mrucu.posY - (tile.yCoord+0.5F),mrucu.posZ - (tile.zCoord+0.5F),1,0,1,1,1,1,absorber.getMRUCU().getMRU()/180000F);
				}
			}
		}
	}
	
	public static void renderBeam(float partialTicks,double x, double y, double z, double posX, double posY, double posZ, double offsetX, double offsetY, double offsetZ, float colorR, float colorG, float colorB, float colorRB, float colorGB, float colorBB, float size)
	{
		GL11.glPushMatrix(); 
		float f21 = (float)0 + partialTicks;
	        float f31 = MathHelper.sin(f21 * 0.2F) / 2.0F + 0.5F;
	        f31 = (f31 * f31 + f31) * 0.2F;
	        float f4;
	        float f5;
	        float f6;
			f4 = (float) offsetX;
		    f5 = (float) offsetY;
			f6 = (float) offsetZ;
	        GL11.glTranslated(x+posX, y + posY, z+posZ);
	        float f7 = MathHelper.sqrt_float(f4 * f4 + f6 * f6);
	        float f8 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6);
	        GL11.glRotatef((float)(-Math.atan2((double)f6, (double)f4)) * 180.0F / (float)Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
	        GL11.glRotatef((float)(-Math.atan2((double)f7, (double)f5)) * 180.0F / (float)Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
	        Tessellator tessellator = Tessellator.instance;
	        RenderHelper.disableStandardItemLighting();
	        MiscUtils.bindTexture("essentialcraft","textures/special/mru_beam.png");
	        GL11.glShadeModel(GL11.GL_SMOOTH);
	        GL11.glEnable(GL11.GL_BLEND);
	        float f9 = 1;
	        float f10 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6) / 32.0F - (PlayerTickHandler.tickAmount + partialTicks) * 5F;
	        tessellator.startDrawing(5);
	        byte b0 = 8;
	
	        for (int i1 = 0; i1 <= b0; ++i1)
	        {
	            float f11 = MathHelper.sin((float)(i1 % b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F * size;
	            float f12 = MathHelper.cos((float)(i1 % b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F * size;
	            float f13 = (float)(i1 % b0) * 1.0F / (float)b0;
	            tessellator.setColorRGBA_F(colorRB, colorGB, colorBB, 1F);
	            tessellator.addVertexWithUV((double)(f11), (double)(f12), 0.0D, (double)f13, (double)f10);
	            tessellator.setColorRGBA_F(colorR, colorG, colorB, 0F);
	            tessellator.addVertexWithUV((double)f11, (double)f12, (double)f8, (double)f13, (double)f9);
	        }
	
	        tessellator.draw();
	        GL11.glDisable(GL11.GL_BLEND);
	        GL11.glShadeModel(GL11.GL_FLAT);
	        RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
	}
	
	public static final ResourceLocation texturesBase = new ResourceLocation("essenthaum","textures/models/mrucuAbsorberBase.png");
	public static final ResourceLocation texturesCore = new ResourceLocation("essenthaum","textures/models/mrucuAbsorberCore.png");
	public static final IModelCustom stabilizerModel = AdvancedModelLoader.loadModel(new ResourceLocation("essenthaum","models/MRUCUAbsorber.obj"));
}
