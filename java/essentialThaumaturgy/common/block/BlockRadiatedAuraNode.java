package essentialThaumaturgy.common.block;

import java.lang.reflect.Method;
import java.util.Random;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import essentialThaumaturgy.common.tile.TileNodeIrradiator;
import essentialThaumaturgy.common.tile.TileRadiatedNode;
import essentialThaumaturgy.common.utils.ThaumcraftHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockRadiatedAuraNode extends BlockContainer{
	
	public IIcon blankIcon;
	
	public BlockRadiatedAuraNode()
	{
		super((Material) ThaumcraftHelper.getField("thaumcraft.common.config.Config", "airyMaterial", null));
		this.setStepSound(new net.minecraft.block.Block.SoundType("cloth", 0.0F, 1.0F));
		this.setTickRandomly(true);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
		blankIcon = reg.registerIcon("thaumcraft:blank");
    }
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return blankIcon;
    }
    
    @Override
    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
    {
        	try
        	{
        		Class utilsFX = Class.forName("thaumcraft.client.lib.UtilsFX");
        		Method infusedStoneSparkle = utilsFX.getDeclaredMethod("infusedStoneSparkle", World.class,int.class,int.class,int.class,int.class);
        		infusedStoneSparkle.setAccessible(true);
        		infusedStoneSparkle.invoke(null, worldObj,target.blockX, target.blockY, target.blockZ,0);
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        		return false;
        	}
        return super.addHitEffects(worldObj, target, effectRenderer);
    }
    
    @Override
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
        	Object proxy = ThaumcraftHelper.getField("thaumcraft.common.Thaumcraft", "proxy", null);
        	try
        	{
        		Method burst = proxy.getClass().getDeclaredMethod("burst", World.class,double.class,double.class,double.class,float.class);
        		burst.setAccessible(true);
        		burst.invoke(proxy, world, (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, 1.0F);
        		world.playSound((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "thaumcraft:craftfail", 1.0F, 1.0F, false);
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        		return false;
        	}
        return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
	@Override
    public int getRenderBlockPass()
    {
        return 0;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
    	return null;
    }
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
    {
        return AxisAlignedBB.getBoundingBox(0, 0, 0, 0, 0, 0);
    }
    
    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int i, ForgeDirection forgedirection)
    {
        return false;
    }
    
    public int quantityDropped(Random p_149745_1_)
    {
        return 0;
    }

    
    
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new TileRadiatedNode();
	}
	
    public void breakBlock(World w, int x, int y, int z, Block b, int meta)
    {
    	if(!w.isRemote)
    	{
            TileEntity te = w.getTileEntity(x, y, z);
            if(te != null && (te instanceof INode) && ((INode)te).getAspects().size() > 0)
            {
                Aspect arr$[] = ((INode)te).getAspects().getAspects();
                int len$ = arr$.length;
                for(int i$ = 0; i$ < len$; i$++)
                {
                    Aspect aspect = arr$[i$];
                    for(int a = 0; a <= ((INode)te).getAspects().getAmount(aspect) / 10; a++)
                        if(((INode)te).getAspects().getAmount(aspect) >= 5)
                        {
                            ItemStack ess = new ItemStack((Item)(ThaumcraftHelper.getField("thaumcraft.common.config.ConfigItems", "itemWispEssence", null)),1,0);
                            AspectList al = new AspectList();
                            ThaumcraftHelper.invokeVirtualMethod("thaumcraft.common.items.ItemWispEssence","setAspects", ess.getItem(), ess,(new AspectList()).add(aspect, 2));
                            this.dropBlockAsItem(w, x, y, z, ess);
                        }

                }
            }
    	}
    	super.breakBlock(w, x, y, z, b, meta);
    }
    
    public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_) 
    {
    	TileEntity te = world.getTileEntity(x, y - 1, z);
    	try
    	{
    		Class TileNodeStabilizer = Class.forName("thaumcraft.common.tiles.TileNodeStabilizer");
    		Class TileNodeConverter = Class.forName("thaumcraft.common.tiles.TileNodeConverter");
    		if(world.isAirBlock(x, y-1, z) || te == null || !(te.getClass().isAssignableFrom(TileNodeStabilizer)))
    		{
    			explode(world,x,y,z);
    		}
    		else
    		{
    			te = world.getTileEntity(x, y + 1, z);
        		if(world.isAirBlock(x, y+1, z) || te == null || !(te.getClass().isAssignableFrom(TileNodeConverter)))
        		{
        			explode(world,x,y,z);
        		}
    		}
    		if(!(world.getTileEntity(x+1, y, z) instanceof TileNodeIrradiator) && !(world.getTileEntity(x-1, y, z) instanceof TileNodeIrradiator) && !(world.getTileEntity(x, y, z+1) instanceof TileNodeIrradiator) && !(world.getTileEntity(x, y, z-1) instanceof TileNodeIrradiator))
    			explode(world,x,y,z);
    		super.onNeighborBlockChange(world, x, y, z, p_149695_5_);
    	}catch(Exception e)
    	{
    		e.printStackTrace();return;
    	}
    }


    public static void explode(World world, int x, int y, int z)
    {
        if(!world.isRemote)
        {
            world.setBlockToAir(x, y, z);
            world.createExplosion(null, (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, 3F, false);
            for(int a = 0; a < 50; a++)
            {
                int xx = (x + world.rand.nextInt(8)) - world.rand.nextInt(8);
                int yy = (y + world.rand.nextInt(8)) - world.rand.nextInt(8);
                int zz = (z + world.rand.nextInt(8)) - world.rand.nextInt(8);
                if(!world.isAirBlock(xx, yy, zz))
                    continue;
                if(yy < y)
                    world.setBlock(xx, yy, zz, (Block)(ThaumcraftHelper.getField("thaumcraft.common.config.ConfigBlocks", "blockFluxGoo", null)), 8, 3);
                else
                	world.setBlock(xx, yy, zz, (Block)(ThaumcraftHelper.getField("thaumcraft.common.config.ConfigBlocks", "blockFluxGas", null)), 8, 3);
            }

        }
    }
}
