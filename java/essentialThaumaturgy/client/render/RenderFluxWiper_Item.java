package essentialThaumaturgy.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderFluxWiper_Item implements IItemRenderer{

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
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texturesBase);
		GL11.glTranslated(x+0.5F, y, z+0.5F);
		fluxWiperModel.renderPart("pCube1");
		GL11.glPopMatrix();
	
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texturesRod);
		GL11.glTranslated(x+0.5F, y+0.3F, z+0.5F);
		fluxWiperModel.renderPart("pCube2");
		GL11.glPopMatrix();
		
	}

	public static final ResourceLocation texturesBase = new ResourceLocation("essenthaum","textures/models/fluxWiperBase.png");
	public static final ResourceLocation texturesRod = new ResourceLocation("essenthaum","textures/models/fluxWiperRod.png");
	public static final IModelCustom fluxWiperModel = AdvancedModelLoader.loadModel(new ResourceLocation("essenthaum","models/FluxWiper.obj"));
}
