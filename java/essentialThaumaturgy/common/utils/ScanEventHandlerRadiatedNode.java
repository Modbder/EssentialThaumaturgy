package essentialThaumaturgy.common.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ScanResult;

public class ScanEventHandlerRadiatedNode implements IScanEventHandler{

	@Override
	public ScanResult scanPhenomena(ItemStack stack, World world,
			EntityPlayer player) {
		return null;
	}

}
