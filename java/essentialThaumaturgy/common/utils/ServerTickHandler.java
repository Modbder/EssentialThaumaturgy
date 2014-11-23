package essentialThaumaturgy.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.relauncher.Side;

public class ServerTickHandler {
	
	@SubscribeEvent
    public void serverWorldTick(WorldTickEvent event)
    {
        if(event.side == Side.CLIENT)
            return;
        if(event.phase != Phase.START)
        {
        	
        }
    }

}
