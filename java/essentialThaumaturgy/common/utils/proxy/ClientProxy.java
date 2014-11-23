package essentialThaumaturgy.common.utils.proxy;

import essentialThaumaturgy.client.gui.GuiMRUAuraDestructor;
import essentialThaumaturgy.client.gui.GuiMRUAuraStabiliser;
import essentialThaumaturgy.client.gui.GuiMRUChunkLoader;
import essentialThaumaturgy.client.gui.GuiMRUCrystalDestructor;
import essentialThaumaturgy.client.gui.GuiMRUDimTranciever;
import essentialThaumaturgy.client.gui.GuiMRUWandCharger;
import essentialThaumaturgy.common.inventory.ContainerMRUAuraDestructor;
import essentialThaumaturgy.common.inventory.ContainerMRUAuraStabiliser;
import essentialThaumaturgy.common.inventory.ContainerMRUChunkLoader;
import essentialThaumaturgy.common.inventory.ContainerMRUCrystalDestructor;
import essentialThaumaturgy.common.inventory.ContainerMRUDimTranciever;
import essentialThaumaturgy.common.inventory.ContainerMRUWandCharger;
import essentialThaumaturgy.common.tile.TileMRUAuraDestructor;
import essentialThaumaturgy.common.tile.TileMRUAuraStabiliser;
import essentialThaumaturgy.common.tile.TileMRUChunkLoader;
import essentialThaumaturgy.common.tile.TileMRUCrystalDestructor;
import essentialThaumaturgy.common.tile.TileMRUDimensionalTranciever;
import essentialThaumaturgy.common.tile.TileMRUWandCharger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
		}
		return null;
	}
	
}
