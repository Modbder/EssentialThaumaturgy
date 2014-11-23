package essentialThaumaturgy.common.utils.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import essentialThaumaturgy.client.gui.GuiMRUAuraDestructor;
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
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

}
