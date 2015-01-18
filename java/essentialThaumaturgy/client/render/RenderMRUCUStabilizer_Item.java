package essentialThaumaturgy.client.render;

import org.lwjgl.opengl.GL11;

import DummyCore.Utils.Coord3D;
import ec3.api.ApiCore;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderMRUCUStabilizer_Item implements IItemRenderer{

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
		stabilizerModel.renderPart("pCube1");
	GL11.glPopMatrix();
	
	GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texturesSlide);
		GL11.glTranslated(x+0.5F, y, z+0.5F);
		stabilizerModel.renderPart("pCube2");
	GL11.glPopMatrix();
	
	GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texturesRod);
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		GL11.glTranslated(x+0.5F, y, z+0.5F);
		if(ApiCore.getClosestMRUCU(Minecraft.getMinecraft().theWorld, new Coord3D(p.posX,p.posY,p.posZ), 8) == null)
			GL11.glTranslated(0, -0.4D, 0);
		GL11.glRotatef(Minecraft.getMinecraft().theWorld.getWorldTime()%360, 0, 1, 0);
		stabilizerModel.renderPart("pCube3");
	GL11.glPopMatrix();
	
	GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(texturesSlide);
		GL11.glTranslated(x+0.5F, y, z+0.5F);
		stabilizerModel.renderPart("pCube4");
		stabilizerModel.renderPart("pCube5");
		stabilizerModel.renderPart("pCube6");
		stabilizerModel.renderPart("pCube7");
	GL11.glPopMatrix();
		
	}

	public static final ResourceLocation texturesBase = new ResourceLocation("essenthaum","textures/models/mrucuStabilizerBase.png");
	public static final ResourceLocation texturesRod = new ResourceLocation("essenthaum","textures/models/mrucuStabilizerRod.png");
	public static final ResourceLocation texturesSlide = new ResourceLocation("essenthaum","textures/models/mrucuStabilizerSlide.png");
	public static final IModelCustom stabilizerModel = AdvancedModelLoader.loadModel(new ResourceLocation("essenthaum","models/MRUCUBalancer.obj"));
}
