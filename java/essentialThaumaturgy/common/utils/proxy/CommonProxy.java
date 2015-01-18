package essentialThaumaturgy.common.utils.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import essentialThaumaturgy.client.gui.GuiFluxWiper;
import essentialThaumaturgy.client.gui.GuiMRUAuraDestructor;
import essentialThaumaturgy.client.gui.GuiMRUCUAbsorber;
import essentialThaumaturgy.client.gui.GuiMRUCUStabilizer;
import essentialThaumaturgy.client.gui.GuiMRUCrystalDestructor;
import essentialThaumaturgy.client.gui.GuiMRUDimTranciever;
import essentialThaumaturgy.client.gui.GuiMRUSpreader;
import essentialThaumaturgy.client.gui.GuiMRUWandCharger;
import essentialThaumaturgy.client.gui.GuiNodeIrradiator;
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

public class CommonProxy implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if(ID == 7322)
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile instanceof TileMRUAuraStabiliser)
			{
				return new ContainerMRUAuraStabiliser(player.inventory, tile);
			}
			if(tile instanceof TileMRUChunkLoader)
			{
				return new ContainerMRUChunkLoader(player.inventory, tile);
			}
			if(tile instanceof TileMRUAuraDestructor)
			{
				return new ContainerMRUAuraDestructor(player.inventory, tile);
			}
			if(tile instanceof TileMRUDimensionalTranciever)
			{
				return new ContainerMRUDimTranciever(player.inventory, tile);
			}
			if(tile instanceof TileMRUCrystalDestructor)
			{
				return new ContainerMRUCrystalDestructor(player.inventory, tile);
			}
			if(tile instanceof TileMRUWandCharger)
			{
				return new ContainerMRUWandCharger(player.inventory, tile);
			}
			if(tile instanceof TileNodeIrradiator)
			{
				return new ContainerNodeIrradiator(player.inventory, tile);
			}
			if(tile instanceof TileMRUSpreader)
			{
				return new ContainerMRUSpreader(player.inventory, tile);
			}
			if(tile instanceof TileFluxWiper)
			{
				return new ContainerFluxWiper(player.inventory, tile);
			}
			if(tile instanceof TileMRUCUStabilizer)
			{
				return new ContainerMRUCUStabilizer(player.inventory, tile);
			}
			if(tile instanceof TileMRUCUAbsorber)
			{
				return new ContainerMRUCUAbsorber(player.inventory, tile);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setup(){};
}
