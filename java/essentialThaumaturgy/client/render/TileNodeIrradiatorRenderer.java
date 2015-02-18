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

public class TileNodeIrradiatorRenderer extends TileEntitySpecialRenderer
{

	@Override
	public void renderTileEntityAt(TileEntity tile, double x,
			double y, double z, float partialTicks) {
		int rotation = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
		TileNodeIrradiator irrad = (TileNodeIrradiator) tile;
		float rotationIndex = irrad.rotation;
		if(rotation == 2)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(baseTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z);
				float scaleIndex = 1.33F;
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("base1");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z);
				scaleIndex = 1.33F;
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rotatorTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z);
				scaleIndex = 1.0F;
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("rotator");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z+0.25F);
				scaleIndex = 0.8F;
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(0.3F, 0, 0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z+0.25F);
				scaleIndex = 0.8F;
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(-0.3F, 0, 0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z+0.25F);
				scaleIndex = 0.8F;
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(0.3F, 0, -0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z+0.25F);
				scaleIndex = 0.8F;
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(-0.3F, 0, -0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			if(irrad.canWork())
			{
				renderBeam(partialTicks, x, y, z, 0.5F, 0.5F, 0.5F, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0.03F);
				for(int i = 0; i < 4; ++i)
					renderBeam(partialTicks, x, y, z, (Math.cos(Math.toRadians(rotationIndex+45+90*i)))/2.35F+0.5F, Math.sin(Math.toRadians(rotationIndex+45+90*i))/2.35F+0.5F, 0.6F, -Math.cos(Math.toRadians(rotationIndex+45+90*i))/2.35F, -Math.sin(Math.toRadians(rotationIndex+45+90*i))/2.35F, 0.9F, 1, 0, 1, 1, 0, 1, 0.02F);
			}
		}
		if(rotation == 3)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(baseTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z+1);
				float scaleIndex = 1.33F;
				GL11.glRotatef(-90, 1, 0, 0);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("base1");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z+1);
				scaleIndex = 1.33F;
				GL11.glRotatef(-90, 1, 0, 0);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();

			Minecraft.getMinecraft().renderEngine.bindTexture(rotatorTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z+1);
				scaleIndex = 1.0F;
				GL11.glRotatef(-90, 1, 0, 0);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("rotator");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z+0.75F);
				scaleIndex = 0.8F;
				GL11.glRotatef(-90, 1, 0, 0);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(0.3F, 0, 0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z+0.75F);
				scaleIndex = 0.8F;
				GL11.glRotatef(-90, 1, 0, 0);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(-0.3F, 0, 0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z+0.75F);
				scaleIndex = 0.8F;
				GL11.glRotatef(-90, 1, 0, 0);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(0.3F, 0, -0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.5F, z+0.75F);
				scaleIndex = 0.8F;
				GL11.glRotatef(-90, 1, 0, 0);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(-0.3F, 0, -0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			if(irrad.canWork())
			{
				renderBeam(partialTicks, x, y, z, 0.5F, 0.5F, 0.5F-1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0.03F);
				for(int i = 0; i < 4; ++i)
					renderBeam(partialTicks, x, y, z, (Math.cos(Math.toRadians(-rotationIndex-45-90*i)))/2.35F+0.5F, Math.sin(Math.toRadians(-rotationIndex-45-90*i))/2.35F+0.5F, 0.4F, -Math.cos(Math.toRadians(-rotationIndex-45-90*i))/2.35F, -Math.sin(Math.toRadians(-rotationIndex-45-90*i))/2.35F, -1, 1, 0, 1, 1, 0, 1, 0.02F);
			}
		}
		if(rotation == 4)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(baseTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x, y+0.5F, z+0.5F);
				float scaleIndex = 1.33F;
				GL11.glRotatef(-90, 0, 0, 1);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("base1");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x, y+0.5F, z+0.5F);
				scaleIndex = 1.33F;
				GL11.glRotatef(-90, 0, 0, 1);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rotatorTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x, y+0.5F, z+0.5F);
				scaleIndex = 1.0F;
				GL11.glRotatef(-90, 0, 0, 1);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("rotator");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.25F, y+0.5F, z+0.5F);
				scaleIndex = 0.8F;
				GL11.glRotatef(-90, 0, 0, 1);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(0.3F, 0, 0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.25F, y+0.5F, z+0.5F);
				scaleIndex = 0.8F;
				GL11.glRotatef(-90, 0, 0, 1);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(-0.3F, 0, 0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.25F, y+0.5F, z+0.5F);
				scaleIndex = 0.8F;
				GL11.glRotatef(-90, 0, 0, 1);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(0.3F, 0, -0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.25F, y+0.5F, z+0.5F);
				scaleIndex = 0.8F;
				GL11.glRotatef(-90, 0, 0, 1);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(-0.3F, 0, -0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			if(irrad.canWork())
			{
				renderBeam(partialTicks, x, y, z, 0.5F, 0.5F, 0.5F, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0.03F);
				for(int i = 0; i < 4; ++i)
					renderBeam(partialTicks, x, y, z, 0.6F, Math.sin(Math.toRadians(-rotationIndex+45+90*i))/2.35F+0.5F, (Math.cos(Math.toRadians(-rotationIndex+45+90*i)))/2.35F+0.5F, 0.9F, -Math.sin(Math.toRadians(-rotationIndex+45+90*i))/2.35F, -Math.cos(Math.toRadians(-rotationIndex+45+90*i))/2.35F, 1, 0, 1, 1, 0, 1, 0.02F);
			}
		}
		if(rotation == 5)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(baseTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+1, y+0.5F, z+0.5F);
				float scaleIndex = 1.33F;
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("base1");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+1, y+0.5F, z+0.5F);
				scaleIndex = 1.33F;
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rotatorTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+1, y+0.5F, z+0.5F);
				scaleIndex = 1.0F;
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("rotator");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.75F, y+0.5F, z+0.5F);
				scaleIndex = 0.8F;
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(0.3F, 0, 0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.75F, y+0.5F, z+0.5F);
				scaleIndex = 0.8F;
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(-0.3F, 0, 0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.75F, y+0.5F, z+0.5F);
				scaleIndex = 0.8F;
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(0.3F, 0, -0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.75F, y+0.5F, z+0.5F);
				scaleIndex = 0.8F;
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(-0.3F, 0, -0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			if(irrad.canWork())
			{
				renderBeam(partialTicks, x, y, z, 0.5F, 0.5F, 0.5F, -1, 0, 0, 1, 0, 1, 1, 0, 1, 0.03F);
				for(int i = 0; i < 4; ++i)
					renderBeam(partialTicks, x, y, z, 0.4F, Math.sin(Math.toRadians(rotationIndex+45+90*i))/2.35F+0.5F, (Math.cos(Math.toRadians(rotationIndex+45+90*i)))/2.35F+0.5F, -0.9F, -Math.sin(Math.toRadians(rotationIndex+45+90*i))/2.35F, -Math.cos(Math.toRadians(rotationIndex+45+90*i))/2.35F, 1, 0, 1, 1, 0, 1, 0.02F);
			}
		}
		if(rotation == 0 || rotation == 1)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(baseTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y, z+0.5F);
				float scaleIndex = 1.33F;
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("base1");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y, z+0.5F);
				scaleIndex = 1.33F;
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rotatorTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y, z+0.5F);
				scaleIndex = 1.0F;
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glScalef(scaleIndex, 1, scaleIndex);
				irradiatorModel.renderPart("rotator");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.25F, z+0.5F);
				scaleIndex = 0.8F;
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(0.3F, 0, 0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.25F, z+0.5F);
				scaleIndex = 0.8F;
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(-0.3F, 0, 0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.25F, z+0.5F);
				scaleIndex = 0.8F;
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(0.3F, 0, -0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
			
			Minecraft.getMinecraft().renderEngine.bindTexture(rodTextures);
			GL11.glPushMatrix();
				GL11.glTranslated(x+0.5F, y+0.25F, z+0.5F);
				scaleIndex = 0.8F;
				GL11.glRotatef(rotationIndex, 0, 1, 0);
				GL11.glTranslatef(-0.3F, 0, -0.3F);
				GL11.glScalef(scaleIndex, 0.5F, scaleIndex);
				irradiatorModel.renderPart("rod");
			GL11.glPopMatrix();
		}
		
        GL11.glPushMatrix();
        if(irrad.getNode() != null)
        {
	    	for(int i = 0; i < irrad.lightnings.size(); ++i)
	    	{
	    		Lightning l = irrad.lightnings.get(i);
	    		ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[ForgeDirection.OPPOSITES[rotation]];
	    		l.render(x+d.offsetX, y, z+d.offsetZ, partialTicks);
	    	}
        }
    	GL11.glPopMatrix();
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
	        MiscUtils.bindTexture("essentialcraft","textures/special/whitebox.png");
	        GL11.glShadeModel(GL11.GL_SMOOTH);
	        GL11.glEnable(GL11.GL_BLEND);
	        float f9 = 1;
	        float f10 = MathHelper.sqrt_float(f4 * f4 + f5 * f5 + f6 * f6) / 32.0F - (PlayerTickHandler.tickAmount + partialTicks) * 0.1F;
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
	
	public static final ResourceLocation baseTextures = new ResourceLocation("essenthaum","textures/models/nodeIrradiatorBase.png");
	public static final ResourceLocation rodTextures = new ResourceLocation("essenthaum","textures/models/nodeIrradiatorRod.png");
	public static final ResourceLocation rotatorTextures = new ResourceLocation("essenthaum","textures/models/nodeIrradiatorRotator.png");
	public static final IModelCustom irradiatorModel = AdvancedModelLoader.loadModel(new ResourceLocation("essenthaum","models/NodeIrradiator.obj"));
}
