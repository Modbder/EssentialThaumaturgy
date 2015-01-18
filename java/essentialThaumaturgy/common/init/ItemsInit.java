package essentialThaumaturgy.common.init;

import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import essentialThaumaturgy.common.core.EssentialThaumaturgy;
import essentialThaumaturgy.common.item.ItemFocusMRUFortification;
import essentialThaumaturgy.common.item.ItemFocusMRUInfusion;
import essentialThaumaturgy.common.item.ItemFocusMRULifedrain;
import essentialThaumaturgy.common.item.ItemFocusMRULight;
import essentialThaumaturgy.common.item.ItemFocusMRUMover;
import essentialThaumaturgy.common.item.ItemFocusMRUSanitiser;
import essentialThaumaturgy.common.item.ItemFocusMRUTeleportation;
import essentialThaumaturgy.common.item.ItemMagicScribingTools;
import essentialThaumaturgy.common.item.ItemMonocle;
import essentialThaumaturgy.common.item.ItemPearl;
import essentialThaumaturgy.common.item.ItemShadeProtector;
import essentialThaumaturgy.common.utils.MRUWandOnUpdate;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import DummyCore.Items.ItemRegistry;

public class ItemsInit {
	
	public static Item 
	chaosGem,
	frozenGem,
	monocleOfRevealing,
	foci_MRUMovement,
	inertInteractor,
	matrixInteractor,
	foci_fortification,
	foci_light,
	foci_mruInfusion,
	foci_sanity,
	foci_ender,
	foci_lifedrain,
	amp,
	apa,
	aec,
	aspectProcessor,
	aspectTransvector,
	wandRod,
	staffRod,
	cap_inert,
	mruCap,
	shade_protector,
	pearl,
	primordialCore,
	mruScribingTools
	;
	
	
	
	public static void init()
	{
		chaosGem = registerItemSimple(chaosGem,Item.class,"gemChaos","gem_chaos",0,false,64);
		frozenGem = registerItemSimple(frozenGem,Item.class,"gemFrozen","gem_frozen",0,false,64);
		monocleOfRevealing = new ItemMonocle(ArmorMaterial.CHAIN, 0, 0).setUnlocalizedName("essenthaum.monocle").setTextureName("essenthaum:monoclerevealing");
		Item.itemRegistry.addObject(getIdForItem(), "essenthaum.monocle", monocleOfRevealing);
		ItemRegistry.registerItem(monocleOfRevealing, EssentialThaumaturgy.class);
		foci_MRUMovement = registerItemSimple(foci_MRUMovement,ItemFocusMRUMover.class,"foci_MRUMovement","focus_MRUMovement",0,false,1);
		inertInteractor = registerItemSimple(inertInteractor,Item.class,"inertInteractor","inertMatrixInteractor",0,false,64);
		matrixInteractor = registerItemSimple(matrixInteractor,Item.class,"matrixInteractor","matrixInteractor",0,false,1);
		foci_fortification = registerItemSimple(foci_fortification,ItemFocusMRUFortification.class,"foci_fortification","focus_fortification",0,false,1);
		foci_light = registerItemSimple(foci_light,ItemFocusMRULight.class,"foci_light","focus_light",0,false,1);
		foci_mruInfusion = registerItemSimple(foci_mruInfusion,ItemFocusMRUInfusion.class,"foci_mruInfusion","focus_infusion",0,false,1);
		foci_sanity = registerItemSimple(foci_sanity,ItemFocusMRUSanitiser.class,"foci_sanity","focus_sanity",0,false,1);
		foci_ender = registerItemSimple(foci_ender,ItemFocusMRUTeleportation.class,"foci_ender","focus_ender",0,false,1);
		foci_lifedrain = registerItemSimple(foci_lifedrain,ItemFocusMRULifedrain.class,"foci_lifedrain","focus_drain",0,false,1);
		
		amp = registerItemSimple(amp,Item.class,"awakenedMagicalPlate","awakenedMagicalPlate",0,false,64);
		apa = registerItemSimple(apa,Item.class,"awakenedMagicalAlloys","awakenedMagicalAlloys",0,false,64);
		aec = registerItemSimple(aec,Item.class,"awakenedElementalCore","awakenedElementalCore",0,false,64);
		aspectProcessor = registerItemSimple(aspectProcessor,Item.class,"aspectProcessor","aspectProcessor",0,false,64);
		aspectTransvector = registerItemSimple(aspectTransvector,Item.class,"aspectTransvector","aspectTransvector",0,false,64);
		wandRod = registerItemSimple(wandRod,Item.class,"wandRod","wand_rod_mru",0,true,1);
		staffRod = registerItemSimple(staffRod,Item.class,"staffRod","staff_rod_mru",0,true,1);
		cap_inert = registerItemSimple(cap_inert,Item.class,"cap_inert","wand_cap_mru_inert",0,true,1);
		mruCap = registerItemSimple(mruCap,Item.class,"mruCap","wand_cap_mru",0,true,1);
		shade_protector = registerItemSimple(shade_protector,ItemShadeProtector.class,"shade_protector","shade_protector",0,false,1);
		pearl = registerItemSimple(pearl,ItemPearl.class,"pearl","pearl",0,false,64);
		primordialCore = registerItemSimple(primordialCore,Item.class,"primordialCore","primordialCore",0,false,64);
		mruScribingTools = registerItemSimple(mruScribingTools,ItemMagicScribingTools.class,"inkwellmru","inkwellmru",5001,false,1);
		
		WandRod WAND_ROD_MRU = new WandRod("mru",100,new ItemStack(wandRod),10, new MRUWandOnUpdate(), new ResourceLocation("essenthaum","textures/misc/rod_mru.png"));
		StaffRod STAFF_ROD_MRU = new StaffRod("mruStaff",100,new ItemStack(staffRod),10, new MRUWandOnUpdate(), new ResourceLocation("essenthaum","textures/misc/rod_mru.png"));
		WandCap WAND_CAP_MRU = new WandCap("mru",0.6F,new ItemStack(mruCap),10);
		WAND_ROD_MRU.setCapacity(150);
		WAND_ROD_MRU.setGlowing(true);
		STAFF_ROD_MRU.setCapacity(125);
		STAFF_ROD_MRU.setGlowing(true);
		WAND_CAP_MRU.setTexture(new ResourceLocation("essenthaum","textures/misc/wand_cap_mru.png"));
	}
	
	public static Item registerItemSimple(Item i, Class<?extends Item> itemClass, String name, String textureName, int damage, boolean full3D, int stackSize)
	{
		try
		{
			i = itemClass.newInstance().setUnlocalizedName("essenthaum."+name).setTextureName("essenthaum:"+textureName).setMaxDamage(damage).setMaxStackSize(stackSize);
			if(full3D)
				i.setFull3D();
			Item.itemRegistry.addObject(getIdForItem(), "essenthaum."+name, i);
			ItemRegistry.registerItem(i, EssentialThaumaturgy.class);
			return i;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static int getIdForItem()
	{
		return ++increasement;
	}
	
	public static int increasement = 16364;

}
