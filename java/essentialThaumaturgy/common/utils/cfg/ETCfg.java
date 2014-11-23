package essentialThaumaturgy.common.utils.cfg;

import net.minecraftforge.common.config.Configuration;
import DummyCore.Utils.DummyConfig;
import DummyCore.Utils.IDummyConfig;
import static essentialThaumaturgy.common.core.EssentialThaumaturgy.*;

public class ETCfg implements IDummyConfig{

	@Override
	public void load(Configuration config) {
		// TODO Auto-generated method stub
		shouldLoadAspects = config.getBoolean("shouldInitialiseAspects", "INTEGRATIONS", true, "Should EssentialThaumaturgy assign aspect to all blocks and items in EssentialCraft?");
		shouldLoadThaumonomicon = config.getBoolean("shouldInitialiseThaumonomicon", "INTEGRATIONS", true, "Should EssentialThaumaturgy register a new page in Thaumonomion and add all recipes?");
		shouldLoadEC3 = config.getBoolean("shouldInitialiseEC3Integrations", "INTEGRATIONS", true, "Should EssentialThaumaturgy register all EssentialCraft integrations, like adding blocks to structures?");
	}

}
