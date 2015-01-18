package essentialThaumaturgy.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderNodeIrradiator_Item implements IItemRenderer{

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		int x = 0;
		int y = 0;
		int z = 0;
		float rotationIndex = 0;
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

	public static final ResourceLocation baseTextures = new ResourceLocation("essenthaum","textures/models/nodeIrradiatorBase.png");
	public static final ResourceLocation rodTextures = new ResourceLocation("essenthaum","textures/models/nodeIrradiatorRod.png");
	public static final ResourceLocation rotatorTextures = new ResourceLocation("essenthaum","textures/models/nodeIrradiatorRotator.png");
	public static final IModelCustom irradiatorModel = AdvancedModelLoader.loadModel(new ResourceLocation("essenthaum","models/NodeIrradiator.obj"));
}
