package essentialThaumaturgy.common.init;

import java.lang.reflect.Field;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import essentialThaumaturgy.common.utils.ScanEventHandlerRadiatedNode;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.WandRod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ThaumonomiconInit {
	
	public static void init()
	{
		EnumHelper.addEnum(new Class[][]{{NodeModifier.class}},NodeModifier.class, "RADIATED", new Object[]{});
		ItemStack matrixInteractor = new ItemStack(ItemsInit.matrixInteractor,1,0);
		ItemStack infusedStone = new ItemStack(BlocksInit.infusedStone,1,0);
		ItemStack pearl = new ItemStack(ItemsInit.pearl,1,0);
		ItemStack pearlPrimodial = new ItemStack(ItemsInit.pearl,1,2);
		ItemStack pearlMRU = new ItemStack(ItemsInit.pearl,1,1);
		ItemStack corePrimordial = new ItemStack(ItemsInit.primordialCore,1,0);
		ItemStack pearlPure = new ItemStack(ItemsInit.pearl,1,3);
		ItemStack pearlShade = new ItemStack(ItemsInit.pearl,1,4);
		ItemStack irradiator = new ItemStack(BlocksInit.nodeIrradiator,1,0);
		ItemStack spreader = new ItemStack(BlocksInit.mruSpreader,1,0);
		ItemStack wiper = new ItemStack(BlocksInit.fluxWiper,1,0);
		ItemStack stabilizer = new ItemStack(BlocksInit.mrucuStabilizer,1,0);
		ItemStack absorber = new ItemStack(BlocksInit.mrucuAbsorber,1,0);
		ItemStack tools = new ItemStack(ItemsInit.mruScribingTools,1,0);
		
		OreDictionary.registerOre("gemChaos", new ItemStack(ItemsInit.chaosGem,1,0));
		OreDictionary.registerOre("gemFrozen", new ItemStack(ItemsInit.frozenGem,1,0));
		
		ShapedOreRecipe glassChaosRecipe = new ShapedOreRecipe(new ItemStack(BlocksInit.glassChaos,8,0), new Object[]{
			"GGG",
			"GCG",
			"GGG",
			'G',"blockGlass",
			'C',"gemChaos"
		});
		
		ShapedOreRecipe glassFrozenRecipe = new ShapedOreRecipe(new ItemStack(BlocksInit.glassFrozen,8,0), new Object[]{
			"GGG",
			"GCG",
			"GGG",
			'G',"blockGlass",
			'C',"gemFrozen"
		});
		
		GameRegistry.addRecipe(glassChaosRecipe);
		GameRegistry.addRecipe(glassFrozenRecipe);
		
		ResearchCategories.registerCategory("et.category", new ResourceLocation("essenthaum","textures/misc/thaumonomiconIcon.png"), new ResourceLocation("thaumcraft","textures/gui/gui_researchback.png"));
		
		ThaumcraftApi.addCrucibleRecipe("et.research.mruVol1", OreDictionary.getOres("gemChaos").get(0), getIS("item:drops",4), new AspectList().add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 2));
		ThaumcraftApi.addCrucibleRecipe("et.research.mruVol1", OreDictionary.getOres("gemFrozen").get(0),getIS("item:drops",4), new AspectList().add(Aspect.COLD, 3).add(Aspect.ORDER, 2));
		ThaumcraftApi.addCrucibleRecipe("et.research.matrix", matrixInteractor,new ItemStack(ItemsInit.inertInteractor,1,0), new AspectList().add(AspectsInit.MRU, 10).add(AspectsInit.MATRIX, 10));
		ThaumcraftApi.addCrucibleRecipe("et.research.infusedStone", infusedStone,getIS("block:fortifiedStone",0), new AspectList().add(AspectsInit.MRU, 1).add(Aspect.MAGIC,1));
		ThaumcraftApi.addCrucibleRecipe("et.research.pearlDivsion", pearl,getISTC("item:itemEldritchObject",3), new AspectList().add(AspectsInit.MRU, 32));
		
		ShapedArcaneRecipe rec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.mruVol2", new ItemStack(ItemsInit.monocleOfRevealing,1,0), new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25).add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.AIR, 10), new Object[]{
			"GM ",
			"   ",
			"   ",
			'G',getISTC("item:itemGoggles",OreDictionary.WILDCARD_VALUE),
			'M',getIS("item:magicMonocle",OreDictionary.WILDCARD_VALUE)
		});
		
		ShapedArcaneRecipe scribingToolsRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.inkwellmru", new ItemStack(ItemsInit.mruScribingTools,1,5000), new AspectList().add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10).add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.AIR, 10), new Object[]{
			"TSI",
			"   ",
			"   ",
			'T',getISTC("item:itemInkwell",OreDictionary.WILDCARD_VALUE),
			'S',getIS("item:storage",1),
			'I',getIS("item:genericItem",1)
		});
		
		ShapedArcaneRecipe fociRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.mruVol2", new ItemStack(ItemsInit.foci_MRUMovement,1,0), new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25).add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.AIR, 10), new Object[]{
			"GOG",
			"OFO",
			"GOG",
			'G',getIS("item:drops",4),
			'O',Items.emerald,
			'F',getIS("item:genericItem", 4)
		});
		
		ShapedArcaneRecipe inertInteractorRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.matrix", new ItemStack(ItemsInit.inertInteractor,1,0), new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25).add(Aspect.FIRE, 25).add(Aspect.WATER, 25).add(Aspect.EARTH, 25).add(Aspect.AIR, 25), new Object[]{
			"GOG",
			"OFO",
			"GOG",
			'G',Items.diamond,
			'O',getIS("item:genericItem", 30),
			'F',getIS("item:genericItem", 4)
		});
		
		ShapedArcaneRecipe foci_fortRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.foci_fortification", new ItemStack(ItemsInit.foci_fortification,1,0), new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 10).add(Aspect.EARTH, 25), new Object[]{
			"GOG",
			"OFO",
			"GOG",
			'G',getISTC("item:itemShard",6),
			'O',Blocks.iron_block,
			'F',ItemsInit.matrixInteractor
		});
		
		ShapedArcaneRecipe foci_lightRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.foci_light", new ItemStack(ItemsInit.foci_light,1,0), new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 10).add(Aspect.FIRE, 25), new Object[]{
			"GOG",
			"OFO",
			"GOG",
			'G',getIS("item:magicalSlag",0),
			'O',Blocks.glowstone,
			'F',ItemsInit.matrixInteractor
		});
		
		ShapedArcaneRecipe foci_infRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.foci_infusion", new ItemStack(ItemsInit.foci_mruInfusion,1,0), new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25).add(Aspect.FIRE, 25).add(Aspect.WATER, 25).add(Aspect.EARTH, 25).add(Aspect.AIR, 25), new Object[]{
			"GOG",
			"OFO",
			"GOG",
			'G',getISTC("item:itemShard",6),
			'O',getIS("item:genericItem",23),
			'F',ItemsInit.matrixInteractor
		});
		
		ShapedArcaneRecipe foci_sanRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.foci_sanity", new ItemStack(ItemsInit.foci_sanity,1,0), new AspectList().add(Aspect.ORDER, 50), new Object[]{
			"GOG",
			"OFO",
			"GOG",
			'G',getISTC("item:itemBathSalts",0),
			'O',getISTC("item:itemShard",6),
			'F',ItemsInit.matrixInteractor
		});
		
		ShapedArcaneRecipe foci_endRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.foci_ender", new ItemStack(ItemsInit.foci_ender,1,0), new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25).add(Aspect.AIR, 25), new Object[]{
			"GOG",
			"OFO",
			"GOG",
			'G',Items.ender_pearl,
			'O',getISTC("item:itemShard",0),
			'F',ItemsInit.matrixInteractor
		});
		
		ShapedArcaneRecipe foci_drainRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.foci_lifedrain", new ItemStack(ItemsInit.foci_lifedrain,1,0), new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25).add(Aspect.EARTH, 50), new Object[]{
			"GOG",
			"OFO",
			"GOG",
			'G',Items.porkchop,
			'O',Items.beef,
			'F',ItemsInit.matrixInteractor
		});
		
		ShapedArcaneRecipe chunkLoaderRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.chunkLoader", new ItemStack(BlocksInit.chunkLoader,1,0), new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 50), new Object[]{
			"EAE",
			"MDM",
			"GOG",
			'G',BlocksInit.infusedStone,
			'O',getIS("item:storage",3),
			'E',"gemAmber",
			'M',getIS("item:genericItem", 30),
			'D',getIS("item:genericItem", 29),
			'A',getIS("item:genericItem", 27)
		});
		
		
		ShapedArcaneRecipe auraStabiliserRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.auraStabiliser", new ItemStack(BlocksInit.auraStabiliser,1,0), new AspectList().add(Aspect.ORDER, 50).add(Aspect.ENTROPY, 25), new Object[]{
			"QAE",
			"MDM",
			"GOG",
			'G',BlocksInit.infusedStone,
			'O',getIS("item:storage",3),
			'Q',ItemsInit.chaosGem,
			'E',ItemsInit.frozenGem,
			'M',getIS("item:genericItem", 30),
			'D',getIS("item:genericItem", 29),
			'A',getIS("item:genericItem", 27)
		});
		
		ShapedArcaneRecipe dimTrancieverRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.dimTranciever", new ItemStack(BlocksInit.dimTranciever,1,0), new AspectList().add(Aspect.ORDER, 50).add(Aspect.ENTROPY, 50), new Object[]{
			"EAE",
			"MDM",
			"GOG",
			'G',BlocksInit.infusedStone,
			'O',getIS("item:storage",3),
			'E',getIS("item:genericItem", 33),
			'M',getIS("item:genericItem", 30),
			'D',getIS("item:genericItem", 29),
			'A',getIS("item:genericItem", 27)
		});
		
		ShapedArcaneRecipe auraDestrRec = ThaumcraftApi.addArcaneCraftingRecipe("et.research.auraSpoiler", new ItemStack(BlocksInit.auraDestructor,1,0), new AspectList().add(Aspect.ENTROPY, 50), new Object[]{
			" M ",
			"MDM",
			" M ",
			'M',"shardEntropy",
			'D',BlocksInit.auraStabiliser
		});
		
		InfusionRecipe iRec_awake_magcalPlate = ThaumcraftApi.addInfusionCraftingRecipe("et.research.mruVol3", new ItemStack(ItemsInit.amp,1,0), 2, new AspectList().add(AspectsInit.MRU, 8).add(Aspect.ENERGY, 8), getIS("item:genericItem", 34), new ItemStack[]{new ItemStack(ItemsInit.chaosGem),new ItemStack(ItemsInit.frozenGem),new ItemStack(ItemsInit.chaosGem),new ItemStack(ItemsInit.frozenGem)});
		InfusionRecipe iRec_awake_magicalAlloys = ThaumcraftApi.addInfusionCraftingRecipe("et.research.mruVol3", new ItemStack(ItemsInit.apa,1,0), 2, new AspectList().add(AspectsInit.MRU, 8).add(Aspect.ENERGY, 8), getIS("item:genericItem", 0), new ItemStack[]{new ItemStack(ItemsInit.chaosGem),new ItemStack(ItemsInit.frozenGem),new ItemStack(ItemsInit.chaosGem),new ItemStack(ItemsInit.frozenGem)});
		InfusionRecipe iRec_awake_elementalCore = ThaumcraftApi.addInfusionCraftingRecipe("et.research.mruVol3", new ItemStack(ItemsInit.aec,1,0), 2, new AspectList().add(AspectsInit.MRU, 8).add(Aspect.ENERGY, 8), getIS("item:genericItem", 1), new ItemStack[]{new ItemStack(ItemsInit.chaosGem),new ItemStack(ItemsInit.frozenGem),new ItemStack(ItemsInit.chaosGem),new ItemStack(ItemsInit.frozenGem)});
		InfusionRecipe iRec_aspectProcessor = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.aspectProcessor", new ItemStack(ItemsInit.aspectProcessor), 3, new AspectList().add(AspectsInit.MRU, 16).add(AspectsInit.MATRIX, 8).add(Aspect.AIR, 16).add(Aspect.WATER, 16).add(Aspect.EARTH, 16).add(Aspect.FIRE, 16).add(Aspect.ENTROPY, 16).add(Aspect.ORDER, 16), new ItemStack(ItemsInit.aec), new ItemStack[]{new ItemStack(Items.diamond),new ItemStack(Items.diamond),new ItemStack(Items.emerald),new ItemStack(Items.emerald),new ItemStack(ItemsInit.amp),new ItemStack(ItemsInit.amp)});
		InfusionRecipe iRec_crystalDestructor = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.crystalDestructor", new ItemStack(BlocksInit.crystalDestructor), 5, new AspectList().add(AspectsInit.MRU, 32).add(AspectsInit.RADIATION, 32).add(Aspect.ENTROPY, 16).add(Aspect.MECHANISM, 16).add(Aspect.EXCHANGE, 32), new ItemStack(ItemsInit.aspectProcessor), new ItemStack[]{new ItemStack(BlocksInit.infusedStone),new ItemStack(BlocksInit.infusedStone),getIS("item:storage",3),getIS("block:crystalExtractor",0),new ItemStack(BlocksInit.infusedStone),new ItemStack(BlocksInit.infusedStone)});
		InfusionRecipe iRec_wandCharger = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.wandCharger", new ItemStack(BlocksInit.wandCharger), 5, new AspectList().add(AspectsInit.MRU, 32).add(AspectsInit.RADIATION, 32).add(Aspect.MAGIC, 16).add(Aspect.MECHANISM, 16).add(Aspect.EXCHANGE, 32), new ItemStack(ItemsInit.aspectProcessor), new ItemStack[]{new ItemStack(BlocksInit.infusedStone),new ItemStack(BlocksInit.infusedStone),getIS("item:storage",3),getIS("block:radiatingChamber",0),new ItemStack(BlocksInit.infusedStone),new ItemStack(BlocksInit.infusedStone)});
		InfusionRecipe iRec_generator = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.generator", new ItemStack(BlocksInit.generator), 5, new AspectList().add(AspectsInit.MRU, 32).add(AspectsInit.RADIATION, 32).add(Aspect.MAGIC, 16).add(Aspect.MECHANISM, 16).add(Aspect.MOTION, 32), new ItemStack(ItemsInit.aspectProcessor), new ItemStack[]{new ItemStack(BlocksInit.infusedStone),new ItemStack(BlocksInit.infusedStone),getIS("item:storage",3),getIS("block:enderGenerator",0),new ItemStack(BlocksInit.infusedStone),new ItemStack(BlocksInit.infusedStone)});
		InfusionRecipe iRec_aspectTransvector = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.aspectTransvector", new ItemStack(ItemsInit.aspectTransvector), 3, new AspectList().add(AspectsInit.MRU, 16).add(AspectsInit.MATRIX, 8).add(Aspect.AIR, 16).add(Aspect.WATER, 16).add(Aspect.EARTH, 16).add(Aspect.FIRE, 16).add(Aspect.ENTROPY, 16).add(Aspect.ORDER, 16), new ItemStack(ItemsInit.aec), new ItemStack[]{new ItemStack(Items.diamond),new ItemStack(Items.diamond),new ItemStack(Items.emerald),new ItemStack(Items.emerald),new ItemStack(ItemsInit.apa),new ItemStack(ItemsInit.apa)});
		InfusionRecipe iRec_rodMRU = 
				ThaumcraftApi.addInfusionCraftingRecipe("ROD_mru", new ItemStack(ItemsInit.wandRod), 5, new AspectList().add(AspectsInit.MRU, 16).add(AspectsInit.MATRIX, 8).add(Aspect.AIR, 16).add(Aspect.WATER, 16).add(Aspect.EARTH, 16).add(Aspect.FIRE, 16).add(Aspect.ENTROPY, 16).add(Aspect.ORDER, 16).add(Aspect.METAL, 16).add(Aspect.ELDRITCH, 16), getISTC("item:itemWandRod",2), new ItemStack[]{new ItemStack(ItemsInit.aspectTransvector),new ItemStack(ItemsInit.aspectTransvector),new ItemStack(ItemsInit.chaosGem),new ItemStack(ItemsInit.frozenGem),new ItemStack(ItemsInit.chaosGem),new ItemStack(ItemsInit.frozenGem)});
		InfusionRecipe iRec_staffMRU = 
				ThaumcraftApi.addInfusionCraftingRecipe("ROD_mruWand", new ItemStack(ItemsInit.staffRod), 5, new AspectList().add(AspectsInit.MRU, 16).add(AspectsInit.MATRIX, 8).add(Aspect.AIR, 16).add(Aspect.WATER, 16).add(Aspect.EARTH, 16).add(Aspect.FIRE, 16).add(Aspect.ENTROPY, 16).add(Aspect.ORDER, 16).add(Aspect.METAL, 16).add(Aspect.ELDRITCH, 16), getISTC("item:itemWandRod",50), new ItemStack[]{new ItemStack(ItemsInit.aspectTransvector),new ItemStack(ItemsInit.aspectTransvector),new ItemStack(ItemsInit.chaosGem),new ItemStack(ItemsInit.frozenGem),new ItemStack(ItemsInit.chaosGem),new ItemStack(ItemsInit.frozenGem)});
		InfusionRecipe iRec_capsMRUI = 
				ThaumcraftApi.addInfusionCraftingRecipe("CAP_mru", new ItemStack(ItemsInit.cap_inert), 8, new AspectList().add(AspectsInit.MRU, 16).add(AspectsInit.MATRIX, 8).add(AspectsInit.RADIATION, 16).add(Aspect.METAL, 16).add(Aspect.TRAVEL, 32),new ItemStack(ItemsInit.aspectTransvector) , new ItemStack[]{new ItemStack(ItemsInit.matrixInteractor),new ItemStack(ItemsInit.chaosGem),new ItemStack(ItemsInit.frozenGem),new ItemStack(ItemsInit.chaosGem),new ItemStack(ItemsInit.frozenGem),getISTC("item:itemWandCap",2)});
		InfusionRecipe iRec_capsMRU = 
				ThaumcraftApi.addInfusionCraftingRecipe("CAP_mru", new ItemStack(ItemsInit.mruCap), 8, new AspectList().add(AspectsInit.MRU, 64).add(AspectsInit.MATRIX, 32),new ItemStack(ItemsInit.cap_inert) , new ItemStack[]{getISTC("item:itemResource",14),getISTC("item:itemResource",14),getISTC("item:itemResource",14),getISTC("item:itemResource",14),getISTC("item:itemResource",14),getISTC("item:itemResource",14),getISTC("item:itemResource",14)});
		InfusionRecipe iRec_shadeProtector = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.mruVol5", new ItemStack(ItemsInit.shade_protector), 8, new AspectList().add(AspectsInit.MATRIX, 64).add(Aspect.HEAL, 32).add(Aspect.LIFE, 32),getISTC("item:itemAmuletRunic",OreDictionary.WILDCARD_VALUE) , new ItemStack[]{getISTC("item:itemResource",14),getISTC("item:itemResource",14),getISTC("item:itemResource",14),getISTC("item:itemResource",14),getISTC("item:itemResource",14),getISTC("item:itemResource",14),getISTC("item:itemResource",14)});
		InfusionRecipe iRec_primodialPearl = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.primordial", getISTC("item:itemEldritchObject",3), 2, new AspectList().add(Aspect.ELDRITCH, 16).add(AspectsInit.MRU,16),getIS("item:genericItem",38), new ItemStack[]{pearlPrimodial,pearlPrimodial,pearlPrimodial,pearlPrimodial,pearlPrimodial});
		InfusionRecipe iRec_primordialCore = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.mruLeft", corePrimordial, 6, new AspectList().add(Aspect.VOID, 32).add(AspectsInit.MRU,32).add(Aspect.ARMOR,16),pearlMRU, new ItemStack[]{getIS("item:genericItem",35),getIS("item:genericItem",36),getIS("item:genericItem",35),getIS("item:genericItem",37),getIS("item:genericItem",35),getIS("item:genericItem",36),getIS("item:genericItem",35),getIS("item:genericItem",37)});
		InfusionRecipe iRec_irradiator = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.nodeIrradiation", irradiator, 5, new AspectList().add(Aspect.ARMOR,64).add(Aspect.AURA,64).add(Aspect.MAGIC,64).add(Aspect.MOTION,64).add(Aspect.MECHANISM,64),getISTC("block:blockStoneDevice",10), new ItemStack[]{corePrimordial,getIS("block:rayTower",0),getISTC("item:itemThaumometer",0),getIS("block:rayTower",0),corePrimordial,getIS("block:rayTower",0),getIS("item:genericItem",44),getIS("block:rayTower",0)});
		InfusionRecipe iRec_spreader = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.mruSpreader", spreader, 2, new AspectList().add(Aspect.AURA,16).add(Aspect.MAGIC,32).add(AspectsInit.RADIATION,64).add(AspectsInit.MATRIX,8),getIS("block:rayTower",0), new ItemStack[]{getIS("item:bound_gem",0),corePrimordial,getISTC("item:itemResource",15)});
		InfusionRecipe iRec_wiper = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.fluxWiper", wiper, 1, new AspectList().add(Aspect.ORDER,16).add(Aspect.MAGIC,8).add(AspectsInit.MRU,8).add(AspectsInit.RADIATION,8),getISTC("block:blockStoneDevice",14), new ItemStack[]{getIS("item:genericItem",36),corePrimordial,getIS("item:genericItem",37)});
		InfusionRecipe iRec_stabilizer = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.mrucuStabilizer", stabilizer, 6, new AspectList().add(Aspect.ORDER,64).add(Aspect.AURA,64).add(AspectsInit.MRU,64).add(AspectsInit.RADIATION,32).add(Aspect.MECHANISM,32),getISTC("block:blockStoneDevice",10), new ItemStack[]{pearlPrimodial,pearlPrimodial,pearlPrimodial,getIS("item:genericItem",37),getIS("item:genericItem",36),getIS("item:genericItem",35),getIS("item:genericItem",35)});
		InfusionRecipe iRec_absorber = 
				ThaumcraftApi.addInfusionCraftingRecipe("et.research.mrucuAbsorber", absorber, 6, new AspectList().add(Aspect.EXCHANGE,64).add(Aspect.AURA,64).add(AspectsInit.MRU,64).add(AspectsInit.RADIATION,32).add(Aspect.MECHANISM,16),getIS("block:enderGenerator",0), new ItemStack[]{pearlPrimodial,pearlPrimodial,getIS("item:genericItem",37),getIS("item:genericItem",36),getIS("item:genericItem",35),getIS("item:genericItem",35),getIS("item:bound_gem",0)});
		
		
		
		
		//TODO Infusion
		new ResearchItem("et.research.mruVol1", "et.category", new AspectList(), 0, 0, 0, new ResourceLocation("essenthaum","textures/misc/thaumonomiconIcon.png"))
			.setAutoUnlock()
			.setPages(
				new ResearchPage(StatCollector.translateToLocal("et.mruVol1.txt.0")),
				new ResearchPage(StatCollector.translateToLocal("et.mruVol1.txt.1")),
				new ResearchPage(new ShapelessOreRecipe(getIS("item:mruMover1",0), new Object[]{
						"shardElemental","stickWood"
					})),
				new ResearchPage(ThaumcraftApi.getCrucibleRecipe(OreDictionary.getOres("gemChaos").get(0))),
				new ResearchPage(ThaumcraftApi.getCrucibleRecipe(OreDictionary.getOres("gemFrozen").get(0))),
				new ResearchPage(glassChaosRecipe),
				new ResearchPage(glassFrozenRecipe)
			)
			.setRound()
			.setStub()
			.registerResearchItem();
		
		new ResearchItem("et.research.mruVol2", "et.category", new AspectList().add(AspectsInit.MRU, 3).add(AspectsInit.RADIATION, 3).add(AspectsInit.MATRIX, 3).add(Aspect.MOTION, 3), -2, -1, 2, new ResourceLocation("essenthaum","textures/misc/thaumonomiconIcon.png"))
			.setAspectTriggers(AspectsInit.MATRIX,AspectsInit.MRU,AspectsInit.RADIATION)
			.setParents("et.research.mruVol1")
			.setPages(
				new ResearchPage(StatCollector.translateToLocal("et.mruVol2.txt.0")),
				new ResearchPage(rec),
				new ResearchPage(fociRec)
			)
			.registerResearchItem();
		
		new ResearchItem("et.research.inkwellmru", "et.category", new AspectList().add(AspectsInit.MRU, 3).add(Aspect.FLIGHT, 3).add(Aspect.MIND, 3), 1, -3, 1, tools)
		.setParents("et.research.mruVol1")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.inkwellmru.txt.0")),
			new ResearchPage(scribingToolsRec)
		)
		.registerResearchItem();
		
		new ResearchItem("et.research.matrix", "et.category", new AspectList().add(AspectsInit.MATRIX, 8).add(Aspect.MIND, 8), -2, -3, 3, new ResourceLocation("essenthaum","textures/aspects/matrix.png"))
		.setAspectTriggers(AspectsInit.MATRIX)
		.setParents("et.research.mruVol2")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.matrix.txt.0")),
			new ResearchPage(StatCollector.translateToLocal("et.matrix.txt.1")),
			new ResearchPage(inertInteractorRec),
			new ResearchPage(ThaumcraftApi.getCrucibleRecipe(matrixInteractor))
			)
		.setConcealed()
		.registerResearchItem();	
		
		new ResearchItem("et.research.foci_fortification", "et.category", new AspectList().add(AspectsInit.MATRIX, 8).add(Aspect.EXCHANGE, 8).add(Aspect.METAL, 8), 0, -5, 3, new ResourceLocation("essenthaum","textures/items/focus_fortification.png"))
		.setParents("et.research.matrix")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.foci_fortification.txt.0")),
			new ResearchPage(foci_fortRec)
		)
		.setConcealed()
		.registerResearchItem();	
		
		new ResearchItem("et.research.foci_light", "et.category", new AspectList().add(AspectsInit.MATRIX, 8).add(Aspect.LIGHT, 8).add(Aspect.FIRE, 8), -4, -5, 3, new ResourceLocation("essenthaum","textures/items/focus_light.png"))
		.setParents("et.research.matrix")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.foci_light.txt.0")),
			new ResearchPage(foci_lightRec)
		)
		.setConcealed()
		.registerResearchItem();	
		
		new ResearchItem("et.research.foci_infusion", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.ELDRITCH, 8).add(Aspect.EXCHANGE, 8), -3, -6, 3, new ItemStack(ItemsInit.foci_mruInfusion,1,0))
		.setParents("et.research.matrix")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.foci_infusion.txt.0")),
			new ResearchPage(foci_infRec)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.foci_sanity", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.HEAL, 8).add(Aspect.MIND, 8), -2, -6, 3, new ItemStack(ItemsInit.foci_sanity,1,0))
		.setParents("et.research.matrix")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.foci_sanity.txt.0")),
			new ResearchPage(foci_sanRec)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.foci_ender", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.TRAVEL, 8).add(Aspect.ELDRITCH, 8), -1, -6, 3, new ItemStack(ItemsInit.foci_ender,1,0))
		.setParents("et.research.matrix")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.foci_ender.txt.0")),
			new ResearchPage(foci_endRec)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.foci_lifedrain", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.FLESH, 8).add(Aspect.EXCHANGE, 8), -2, -4, 3, new ItemStack(ItemsInit.foci_lifedrain,1,0))
		.setParents("et.research.matrix")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.foci_lifedrain.txt.0")),
			new ResearchPage(foci_drainRec)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.infusedStone", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.EARTH, 8).add(Aspect.METAL, 8), -2, 1, 3, new ResourceLocation("essenthaum","textures/aspects/radiation.png"))
		.setParents("et.research.matrix")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.infusedStone.txt.0")),
			new ResearchPage(ThaumcraftApi.getCrucibleRecipe(infusedStone))
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.auraStabiliser", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.AURA, 8).add(Aspect.MIND, 8), -2, 4, 3, new ItemStack(BlocksInit.auraStabiliser,1,0))
		.setParents("et.research.infusedStone")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.auraStabiliser.txt.0")),
			new ResearchPage(auraStabiliserRec)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.chunkLoader", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.AURA, 8).add(Aspect.TRAP, 8), -3, 4, 3, new ItemStack(BlocksInit.chunkLoader,1,0))
		.setParents("et.research.infusedStone")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.chunkLoader.txt.0")),
			new ResearchPage(chunkLoaderRec)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.auraSpoiler", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.AURA, 8).add(Aspect.ENTROPY, 8), -2, 5, 3, new ItemStack(BlocksInit.auraDestructor,1,0))
		.setParents("et.research.auraStabiliser")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.auraSpoiler.txt.0")),
			new ResearchPage(auraDestrRec)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.dimTranciever", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.AURA, 8).add(Aspect.TRAVEL, 8), -1, 4, 3, new ItemStack(BlocksInit.dimTranciever,1,0))
		.setParents("et.research.infusedStone")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.dimTranciever.txt.0")),
			new ResearchPage(dimTrancieverRec)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.mruVol3", "et.category", new AspectList().add(AspectsInit.MRU, 5).add(AspectsInit.RADIATION, 5).add(AspectsInit.MATRIX, 5).add(Aspect.MAGIC, 5), -4, -2,3, new ResourceLocation("essenthaum","textures/misc/thaumonomiconIcon.png"))
		.setAspectTriggers(AspectsInit.MATRIX,AspectsInit.MRU,AspectsInit.RADIATION)
		.setParents("et.research.mruVol2","INFUSION")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.mruVol3.txt.0")),
			new ResearchPage(iRec_awake_magcalPlate),
			new ResearchPage(iRec_awake_magicalAlloys),
			new ResearchPage(iRec_awake_elementalCore)
		)
		.registerResearchItem();
		
		new ResearchItem("et.research.aspectProcessor", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.MIND, 8).add(AspectsInit.MATRIX, 8).add(Aspect.EXCHANGE, 8), -7, -4, 3, new ItemStack(ItemsInit.aspectProcessor,1,0))
		.setParents("et.research.mruVol3")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.aspectProcessor.txt.0")),
			new ResearchPage(iRec_aspectProcessor)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.crystalDestructor", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.MECHANISM, 8).add(Aspect.CRYSTAL, 8), -7, -7, 3, new ItemStack(BlocksInit.crystalDestructor,1,0))
		.setParents("et.research.aspectProcessor")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.crystalDestructor.txt.0")),
			new ResearchPage(StatCollector.translateToLocal("et.crystalDestructor.txt.1")),
			new ResearchPage(iRec_crystalDestructor)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.wandCharger", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.MECHANISM, 8).add(Aspect.MAGIC, 8), -6, -7, 3, new ItemStack(BlocksInit.wandCharger,1,0))
		.setParents("et.research.aspectProcessor")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.wandCharger.txt.0")),
			new ResearchPage(iRec_wandCharger)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.generator", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.MECHANISM, 8).add(Aspect.MOTION, 8), -8, -7, 3, new ItemStack(BlocksInit.generator,1,0))
		.setParents("et.research.aspectProcessor")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.generator.txt.0")),
			new ResearchPage(iRec_generator)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.aspectTransvector", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.MIND, 8).add(AspectsInit.MATRIX, 8).add(Aspect.EXCHANGE, 8), -7, 0, 3, new ItemStack(ItemsInit.aspectTransvector,1,0))
		.setParents("et.research.mruVol3")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.aspectTransvector.txt.0")),
			new ResearchPage(iRec_aspectTransvector)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("ROD_mru", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.EARTH, 8).add(AspectsInit.MATRIX, 8).add(Aspect.ELDRITCH, 8), -8, 3, 3, new ItemStack(ItemsInit.wandRod,1,0))
		.setParents("et.research.aspectTransvector")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.ROD_mru.txt.0")),
			new ResearchPage(iRec_rodMRU)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("ROD_mruStaff_staff", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(Aspect.AIR, 8).add(AspectsInit.MATRIX, 8).add(Aspect.ELDRITCH, 8), -6, 3, 3, new ItemStack(ItemsInit.staffRod,1,0))
		.setParents("et.research.aspectTransvector")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.ROD_mruStaff.txt.0")),
			new ResearchPage(iRec_staffMRU)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("CAP_mru", "et.category", new AspectList().add(AspectsInit.RADIATION, 8).add(AspectsInit.MATRIX, 8).add(Aspect.TRAVEL, 8), -7, 3, 3, new ItemStack(ItemsInit.mruCap,1,0))
		.setParents("et.research.aspectTransvector")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.CAP_mru.txt.0")),
			new ResearchPage(iRec_capsMRUI),
			new ResearchPage(iRec_capsMRU)
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.mruVol4", "et.category", new AspectList().add(AspectsInit.MRU, 5).add(Aspect.DARKNESS, 5).add(AspectsInit.RADIATION, 5).add(Aspect.TRAVEL, 5), -10, -2,3, new ResourceLocation("essenthaum","textures/misc/thaumonomiconIcon.png"))
		.setAspectTriggers(AspectsInit.MATRIX,AspectsInit.MRU,AspectsInit.RADIATION)
		.setParents("et.research.mruVol3","ELDRITCHMINOR")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.mruVol4.txt.0")),
			new ResearchPage(StatCollector.translateToLocal("et.mruVol4.txt.1"))
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.mruVol5", "et.category", new AspectList().add(AspectsInit.MRU, 5).add(Aspect.DARKNESS, 5).add(AspectsInit.RADIATION, 5).add(Aspect.TRAVEL, 5), -13, -2,3, new ResourceLocation("essenthaum","textures/misc/thaumonomiconIcon.png"))
		.setAspectTriggers(AspectsInit.MATRIX,AspectsInit.MRU,AspectsInit.RADIATION)
		.setParents("et.research.mruVol4")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.mruVol5.txt.0")),
			new ResearchPage(iRec_shadeProtector)
			
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.pearlDivsion", "et.category", new AspectList().add(AspectsInit.MRU, 5).add(Aspect.ELDRITCH, 5).add(Aspect.TAINT, 5).add(Aspect.MAGIC, 5), -17, -2,3, pearl)
		.setParents("et.research.mruVol5","PRIMPEARL")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.pearlDivsion.txt.0")),
			new ResearchPage(ThaumcraftApi.getCrucibleRecipe(pearl))
			
		)
		.setSecondary()
		.setSpecial()
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.primordial", "et.category", new AspectList().add(AspectsInit.MRU, 5).add(Aspect.ELDRITCH, 5).add(Aspect.TAINT, 5).add(Aspect.MAGIC, 5).add(Aspect.EXCHANGE, 5), -19, -5,3, pearlPrimodial)
		.setParents("et.research.pearlDivsion")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.primordial.txt.0")),
			new ResearchPage(iRec_primodialPearl)
			
		)
		.setSecondary()
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.mruLeft", "et.category", new AspectList().add(AspectsInit.MRU, 5).add(Aspect.ELDRITCH, 5).add(Aspect.TAINT, 5).add(Aspect.MAGIC, 5).add(Aspect.VOID, 5), -20, -3,3, pearlMRU)
		.setParents("et.research.pearlDivsion")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.mruLeft.txt.0")),
			new ResearchPage(iRec_primordialCore)
			
		)
		.setSecondary()
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.pureLeft", "et.category", new AspectList().add(AspectsInit.MRU, 5).add(Aspect.ELDRITCH, 5).add(Aspect.TAINT, 5).add(Aspect.MAGIC, 5).add(Aspect.ORDER, 5), -20, -1,3, pearlPure)
		.setParents("et.research.pearlDivsion")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.pureLeft.txt.0"))
			
		)
		.setSecondary()
		.setConcealed()
		.registerResearchItem();
		
		
		new ResearchItem("et.research.shadeLeft", "et.category", new AspectList().add(AspectsInit.MRU, 5).add(Aspect.ELDRITCH, 5).add(Aspect.TAINT, 5).add(Aspect.MAGIC, 5).add(Aspect.ENTROPY, 5), -19, 1,3, pearlShade)
		.setParents("et.research.pearlDivsion")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.shadeLeft.txt.0")),
			new ResearchPage(StatCollector.translateToLocal("et.shadeLeft.txt.1"))
			
		)
		.setSecondary()
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.nodeIrradiation", "et.category", new AspectList().add(AspectsInit.MRU, 5).add(Aspect.ARMOR, 5).add(Aspect.AURA, 5).add(Aspect.MAGIC, 5).add(Aspect.MECHANISM, 5), -22, -4,3, irradiator)
		.setParents("et.research.mruLeft")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.nodeIrradiation.txt.0")),
			new ResearchPage(StatCollector.translateToLocal("et.nodeIrradiation.txt.1")),
			new ResearchPage(StatCollector.translateToLocal("et.nodeIrradiation.txt.2")),
			new ResearchPage(iRec_irradiator)
			
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.mruSpreader", "et.category", new AspectList().add(AspectsInit.RADIATION, 5).add(Aspect.AIR, 5).add(Aspect.AURA, 5).add(Aspect.MAGIC, 5).add(Aspect.MECHANISM, 5), -22, -2,3, spreader)
		.setParents("et.research.mruLeft")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.mruSpreader.txt.0")),
			new ResearchPage(StatCollector.translateToLocal("et.mruSpreader.txt.1")),
			new ResearchPage(iRec_spreader)
			
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.fluxWiper", "et.category", new AspectList().add(Aspect.ORDER, 5).add(Aspect.AIR, 5).add(Aspect.AURA, 5).add(Aspect.MAGIC, 5).add(Aspect.MECHANISM, 5), -23, -3,3, wiper)
		.setParents("et.research.mruLeft")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.fluxWiper.txt.0")),
			new ResearchPage(iRec_wiper)
			
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.mrucuStabilizer", "et.category", new AspectList().add(Aspect.ORDER, 5).add(Aspect.AURA, 5).add(AspectsInit.MRU, 5).add(AspectsInit.MATRIX, 5).add(Aspect.MECHANISM, 5), -21, -7,3, stabilizer)
		.setParents("et.research.primordial")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.mrucuStabilizer.txt.0")),
			new ResearchPage(StatCollector.translateToLocal("et.mrucuStabilizer.txt.1")),
			new ResearchPage(StatCollector.translateToLocal("et.mrucuStabilizer.txt.2")),
			new ResearchPage(iRec_stabilizer)
			
		)
		.setConcealed()
		.registerResearchItem();
		
		new ResearchItem("et.research.mrucuAbsorber", "et.category", new AspectList().add(Aspect.EXCHANGE, 5).add(Aspect.AURA, 5).add(AspectsInit.MRU, 5).add(AspectsInit.MATRIX, 5).add(Aspect.MECHANISM, 5), -17, -7,3, absorber)
		.setParents("et.research.primordial")
		.setPages(
			new ResearchPage(StatCollector.translateToLocal("et.mrucuAbsorber.txt.0")),
			new ResearchPage(iRec_absorber)
			
		)
		.setConcealed()
		.registerResearchItem();
		
		ThaumcraftApi.addWarpToResearch("et.research.foci_sanity", 1);
		ThaumcraftApi.addWarpToResearch("et.research.foci_lifedrain", 3);
		ThaumcraftApi.addWarpToResearch("et.research.auraSpoiler", 5);
		ThaumcraftApi.addWarpToResearch("et.research.crystalDestructor", 1);
		ThaumcraftApi.addWarpToResearch("CAP_mru", 2);
		ThaumcraftApi.addWarpToResearch("et.research.mruVol4", 6);
		ThaumcraftApi.addWarpToResearch("et.research.mruVol5", 4);
		ThaumcraftApi.addWarpToResearch("et.research.shadeLeft", 6);
	}	
	
	public static ItemStack getISTC(String name, int meta)
	{
		if(meta == -1)
			meta = OreDictionary.WILDCARD_VALUE;
		int index = name.indexOf(":");
		if(index == -1)
		{
			List<ItemStack> retLst = OreDictionary.getOres(name);
			if(retLst != null && !retLst.isEmpty())
			{
				for(ItemStack stk : retLst)
				{
					if(stk != null)
					{
						String classPath = stk.getItem().getClass().toString();
						if(classPath.toLowerCase().contains("ec3"))
						{
							return stk;
						}
					}
				}
			}
		}else
		{
			try
			{
				String type = name.substring(0, index);
				Class searchClass = null;
				if(type.toLowerCase().equals("block"))
				{
					searchClass = Class.forName("thaumcraft.common.config.ConfigBlocks");
				}
				if(type.toLowerCase().equals("item"))
				{
					searchClass = Class.forName("thaumcraft.common.config.ConfigItems");
				}
				if(searchClass != null)
				{
					String fieldToSearch = name.substring(index+1, name.length());
					Field fld = searchClass.getDeclaredField(fieldToSearch);
					if(fld != null)
					{
						fld.setAccessible(true);
						Object obj = fld.get(null);
						if(obj != null)
						{
							if(obj instanceof Block)
								return new ItemStack((Block)obj,1,meta);
							if(obj instanceof Item)
								return new ItemStack((Item)obj,1,meta);
						}
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	public static ItemStack getIS(String name, int meta)
	{
		if(meta == -1)
			meta = OreDictionary.WILDCARD_VALUE;
		int index = name.indexOf(":");
		if(index == -1)
		{
			List<ItemStack> retLst = OreDictionary.getOres(name);
			if(retLst != null && !retLst.isEmpty())
			{
				for(ItemStack stk : retLst)
				{
					if(stk != null)
					{
						String classPath = stk.getItem().getClass().toString();
						if(classPath.toLowerCase().contains("ec3"))
						{
							return stk;
						}
					}
				}
			}
		}else
		{
			try
			{
				String type = name.substring(0, index);
				Class searchClass = null;
				if(type.toLowerCase().equals("block"))
				{
					searchClass = Class.forName("ec3.common.block.BlocksCore");
				}
				if(type.toLowerCase().equals("item"))
				{
					searchClass = Class.forName("ec3.common.item.ItemsCore");
				}
				if(searchClass != null)
				{
					String fieldToSearch = name.substring(index+1, name.length());
					Field fld = searchClass.getDeclaredField(fieldToSearch);
					if(fld != null)
					{
						fld.setAccessible(true);
						Object obj = fld.get(null);
						if(obj != null)
						{
							if(obj instanceof Block)
								return new ItemStack((Block)obj,1,meta);
							if(obj instanceof Item)
								return new ItemStack((Item)obj,1,meta);
						}
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
