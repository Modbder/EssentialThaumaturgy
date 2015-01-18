package essentialThaumaturgy.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderMRUSpreader_Item implements IItemRenderer{

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
			GL11.glTranslated(x+0.5F, y-0.1F, z+0.5F);
			GL11.glScalef(0.5F, 1, 0.5F);
			Minecraft.getMinecraft().renderEngine.bindTexture(textures);
			irradiatorModel.renderAll();
		GL11.glPopMatrix();
		
	}

	public static final ResourceLocation textures = new ResourceLocation("essenthaum","textures/models/mruSpreaderBottom.png");
	public static final IModelCustom irradiatorModel = AdvancedModelLoader.loadModel(new ResourceLocation("essenthaum","models/MRUSpreader.obj"));
}
