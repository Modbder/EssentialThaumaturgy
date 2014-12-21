package essentialThaumaturgy.common.core;

import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import DummyCore.Core.Core;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import essentialThaumaturgy.common.init.AspectsInit;
import essentialThaumaturgy.common.init.BlocksInit;
import essentialThaumaturgy.common.init.EC3Init;
import essentialThaumaturgy.common.init.ItemsInit;
import essentialThaumaturgy.common.init.ThaumonomiconInit;
import essentialThaumaturgy.common.utils.ETUtils;
import essentialThaumaturgy.common.utils.cfg.ETCfg;
import essentialThaumaturgy.common.utils.proxy.CommonProxy;

@Mod(modid = "essenthaum",name = "Essential Thaumaturgy",version = "1.0.1710.0",dependencies = "required-after:Baubles@[1.0.1.10,);after:EssentialCraft@[4.3.1710.112,);after:Thaumcraft@[4.2.2.0,)")
public class EssentialThaumaturgy {
	
	public static EssentialThaumaturgy core = null;

	public static ETCfg cfg = new ETCfg();
	
	@SidedProxy(clientSide = "essentialThaumaturgy.common.utils.proxy.ClientProxy", serverSide = "essentialThaumaturgy.common.utils.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		try
		{
			core = this;
			ForgeChunkManager.setForcedChunkLoadingCallback(core, ETUtils.getTHObj());
			NetworkRegistry.INSTANCE.registerGuiHandler(core, proxy);
			MinecraftForge.EVENT_BUS.register(ETUtils.INSTANCE);
			Core.registerModAbsolute(getClass(), "Essential Thaumaturgy", event.getModConfigurationDirectory().getAbsolutePath(), cfg);
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		if(shouldLoadAspects)
			AspectsInit.initAspects();
		ItemsInit.init();
		BlocksInit.init();
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{
		if(shouldLoadThaumonomicon)
			ThaumonomiconInit.init();
		if(shouldLoadEC3)
			EC3Init.init();
	}
	
	public static boolean shouldLoadAspects;
	public static boolean shouldLoadThaumonomicon;
	public static boolean shouldLoadEC3;
}
