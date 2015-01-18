package essentialThaumaturgy.common.utils.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import essentialThaumaturgy.client.gui.GuiFluxWiper;
import essentialThaumaturgy.client.gui.GuiMRUAuraDestructor;
import essentialThaumaturgy.client.gui.GuiMRUAuraStabiliser;
import essentialThaumaturgy.client.gui.GuiMRUCUAbsorber;
import essentialThaumaturgy.client.gui.GuiMRUCUStabilizer;
import essentialThaumaturgy.client.gui.GuiMRUChunkLoader;
import essentialThaumaturgy.client.gui.GuiMRUCrystalDestructor;
import essentialThaumaturgy.client.gui.GuiMRUDimTranciever;
import essentialThaumaturgy.client.gui.GuiMRUSpreader;
import essentialThaumaturgy.client.gui.GuiMRUWandCharger;
import essentialThaumaturgy.client.gui.GuiNodeIrradiator;
import essentialThaumaturgy.client.render.RenderFluxWiper_Item;
import essentialThaumaturgy.client.render.RenderMRUCUAbsorber_Item;
import essentialThaumaturgy.client.render.RenderMRUCUStabilizer_Item;
import essentialThaumaturgy.client.render.RenderMRUSpreader_Item;
import essentialThaumaturgy.client.render.RenderNodeIrradiator_Item;
import essentialThaumaturgy.client.render.TileFluxWiperRenderer;
import essentialThaumaturgy.client.render.TileMRUCUAbsorberRenderer;
import essentialThaumaturgy.client.render.TileMRUCUStabilizerRenderer;
import essentialThaumaturgy.client.render.TileMRUSpreaderRenderer;
import essentialThaumaturgy.client.render.TileNodeIrradiatorRenderer;
import essentialThaumaturgy.client.render.TileRadiatedNodeRenderer;
import essentialThaumaturgy.common.init.BlocksInit;
import essentialThaumaturgy.common.inventory.ContainerFluxWiper;
import essentialThaumaturgy.common.inventory.ContainerMRUAuraDestructor;
import essentialThaumaturgy.common.inventory.ContainerMRUAuraStabiliser;
import essentialThaumaturgy.common.inventory.ContainerMRUCUAbsorber;
import essentialThaumaturgy.common.inventory.ContainerMRUCUStabilizer;
import essentialThaumaturgy.common.inventory.ContainerMRUChunkLoader;
import essentialThaumaturgy.common.inventory.ContainerMRUCrystalDestructor;
import essentialThaumaturgy.common.inventory.ContainerMRUDimTranciever;
import essentialThaumaturgy.common.inventory.ContainerMRUSpreader;
import essentialThaumaturgy.common.inventory.ContainerMRUWandCharger;
import essentialThaumaturgy.common.inventory.ContainerNodeIrradiator;
import essentialThaumaturgy.common.tile.TileFluxWiper;
import essentialThaumaturgy.common.tile.TileMRUAuraDestructor;
import essentialThaumaturgy.common.tile.TileMRUAuraStabiliser;
import essentialThaumaturgy.common.tile.TileMRUCUAbsorber;
import essentialThaumaturgy.common.tile.TileMRUCUStabilizer;
import essentialThaumaturgy.common.tile.TileMRUChunkLoader;
import essentialThaumaturgy.common.tile.TileMRUCrystalDestructor;
import essentialThaumaturgy.common.tile.TileMRUDimensionalTranciever;
import essentialThaumaturgy.common.tile.TileMRUSpreader;
import essentialThaumaturgy.common.tile.TileMRUWandCharger;
import essentialThaumaturgy.common.tile.TileNodeIrradiator;
import essentialThaumaturgy.common.tile.TileRadiatedNode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == 7322)
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile instanceof TileMRUAuraStabiliser)
			{
				return new GuiMRUAuraStabiliser(new ContainerMRUAuraStabiliser(player.inventory, tile), tile);
			}
			if(tile instanceof TileMRUChunkLoader)
			{
				return new GuiMRUChunkLoader(new ContainerMRUChunkLoader(player.inventory, tile), tile);
			}
			if(tile instanceof TileMRUAuraDestructor)
			{
				return new GuiMRUAuraDestructor(new ContainerMRUAuraDestructor(player.inventory, tile), tile);
			}
			if(tile instanceof TileMRUDimensionalTranciever)
			{
				return new GuiMRUDimTranciever(new ContainerMRUDimTranciever(player.inventory, tile), tile);
			}
			if(tile instanceof TileMRUCrystalDestructor)
			{
				return new GuiMRUCrystalDestructor(new ContainerMRUCrystalDestructor(player.inventory, tile), tile);
			}
			if(tile instanceof TileMRUWandCharger)
			{
				return new GuiMRUWandCharger(new ContainerMRUWandCharger(player.inventory, tile), tile);
			}
			if(tile instanceof TileNodeIrradiator)
			{
				return new GuiNodeIrradiator(new ContainerNodeIrradiator(player.inventory, tile), tile);
			}
			if(tile instanceof TileMRUSpreader)
			{
				return new GuiMRUSpreader(new ContainerMRUSpreader(player.inventory, tile), tile);
			}
			if(tile instanceof TileFluxWiper)
			{
				return new GuiFluxWiper(new ContainerFluxWiper(player.inventory, tile), tile);
			}
			if(tile instanceof TileMRUCUStabilizer)
			{
				return new GuiMRUCUStabilizer(new ContainerMRUCUStabilizer(player.inventory, tile), tile);
			}
			if(tile instanceof TileMRUCUAbsorber)
			{
				return new GuiMRUCUAbsorber(new ContainerMRUCUAbsorber(player.inventory, tile), tile);
			}
		}
		return null;
	}
	
	@Override
	public void setup()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileRadiatedNode.class, new TileRadiatedNodeRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileNodeIrradiator.class, new TileNodeIrradiatorRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileMRUSpreader.class, new TileMRUSpreaderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileFluxWiper.class, new TileFluxWiperRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileMRUCUStabilizer.class, new TileMRUCUStabilizerRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileMRUCUAbsorber.class, new TileMRUCUAbsorberRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlocksInit.nodeIrradiator), new RenderNodeIrradiator_Item());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlocksInit.mruSpreader), new RenderMRUSpreader_Item());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlocksInit.fluxWiper), new RenderFluxWiper_Item());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlocksInit.mrucuStabilizer), new RenderMRUCUStabilizer_Item());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlocksInit.mrucuAbsorber), new RenderMRUCUAbsorber_Item());
	}
	
}
