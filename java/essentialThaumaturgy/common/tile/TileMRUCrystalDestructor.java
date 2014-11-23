package essentialThaumaturgy.common.tile;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
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

public class TileMRUCrystalDestructor extends TileHasMRUAndAspects{
	
	public TileMRUCrystalDestructor()
	{
		this.setMaxMRU(5000F);
		this.setSlotsNum(2);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		ItemStack crystal = this.getStackInSlot(1);
		if(crystal != null && this.getMRU() - 500 > 0)
			try
			{
				Class itemCls = crystal.getItem().getClass();
				Class crystalCls = Class.forName("ec3.common.item.ItemBlockElementalCrystal");
				if(itemCls == crystalCls)
				{
					NBTTagCompound crystalTag = MiscUtils.getStackTag(crystal);
					float fire = crystalTag.getFloat("fire");
					float water = crystalTag.getFloat("water");
					float earth = crystalTag.getFloat("earth");
					float air = crystalTag.getFloat("air");
					float size = crystalTag.getFloat("size");
					
					if(!this.worldObj.isRemote)
					{
						int randomAspectGen = this.worldObj.rand.nextInt(6);
						Aspect apt = null;
						int amount = 1;
						switch(randomAspectGen)
						{
							case 0:
							{
								if(this.worldObj.rand.nextFloat() * 20000 < fire)
								{
									apt = Aspect.FIRE;
								}
							}
							case 1:
							{
								if(this.worldObj.rand.nextFloat() * 20000 < water)
								{
									apt = Aspect.WATER;
								}
							}
							case 2:
							{
								if(this.worldObj.rand.nextFloat() * 20000 < earth)
								{
									apt = Aspect.EARTH;
								}
							}
							case 3:
							{
								if(this.worldObj.rand.nextFloat() * 20000 < air)
								{
									apt = Aspect.AIR;
								}
							}
							case 4:
							{
								if(this.worldObj.rand.nextFloat() * 20000 < size)
								{
									apt = Aspect.ORDER;
								}
							}
							case 5:
							{
								if(this.worldObj.rand.nextFloat() * 20000 < size)
								{
									apt = Aspect.ENTROPY;
								}
							}
						}
						if(apt != null)
						{
							this.addToContainer(apt, amount);
							this.setMRU(this.getMRU()-500);
						}
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();return;
			}
	}

	
	
}
