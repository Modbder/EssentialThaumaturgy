package essentialThaumaturgy.common.tile;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import essentialThaumaturgy.common.init.AspectsInit;
import essentialThaumaturgy.common.utils.ETUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;

public class TileMRUWandCharger extends TileHasMRUReqAspects{
	
	public TileMRUWandCharger()
	{
		this.setMaxMRU(5000F);
		this.setSlotsNum(2);
	}
	
	@Override
	public Aspect getSuctionType(ForgeDirection face) {
		return null;
	}

	@Override
	public int getSuctionAmount(ForgeDirection face) {
		return 0;
	}

	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		ItemStack wand = this.getStackInSlot(1);
		if(wand != null && this.getMRU() - 50 > 0)
			try
			{
				Class itemCls = wand.getItem().getClass();
				Class wandCls = Class.forName("thaumcraft.common.items.wands.ItemWandCasting");
				if(itemCls == wandCls)
				{
					NBTTagCompound tg = MiscUtils.getStackTag(wand);
					Method getMaxVis = wandCls.getMethod("getMaxVis", ItemStack.class);
					int aspectCap = Integer.parseInt(getMaxVis.invoke(wand.getItem(), wand).toString());
					int fire = tg.getInteger("ignis");
					int water = tg.getInteger("aqua");
					int earth = tg.getInteger("terra");
					int air = tg.getInteger("aer");
					int entropy = tg.getInteger("perditio");
					int order = tg.getInteger("ordo");
					
					if(fire * 2 < aspectCap && this.getMRU() - 10 > 0)
					{
						this.setMRU(this.getMRU()-10);
						++fire;
						tg.setInteger("ignis", fire);
						return;
					}
					
					if(water * 2 < aspectCap && this.getMRU() - 10 > 0)
					{
						this.setMRU(this.getMRU()-10);
						++water;
						tg.setInteger("aqua", water);
						return;
					}
					
					if(earth * 2 < aspectCap && this.getMRU() - 10 > 0)
					{
						this.setMRU(this.getMRU()-10);
						++earth;
						tg.setInteger("terra", earth);
						return;
					}
					
					if(air * 2 < aspectCap && this.getMRU() - 10 > 0)
					{
						this.setMRU(this.getMRU()-10);
						++air;
						tg.setInteger("aer", air);
						return;
					}
					
					if(order * 2 < aspectCap && this.getMRU() - 10 > 0)
					{
						this.setMRU(this.getMRU()-10);
						++order;
						tg.setInteger("ordo", order);
						return;
					}
					
					if(entropy * 2 < aspectCap && this.getMRU() - 10 > 0)
					{
						this.setMRU(this.getMRU()-10);
						++entropy;
						tg.setInteger("perditio", entropy);
						return;
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();return;
			}
	}

	
	
}
