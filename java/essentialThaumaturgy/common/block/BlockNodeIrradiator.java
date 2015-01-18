package essentialThaumaturgy.common.block;

import java.lang.reflect.Method;
import java.util.Random;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import essentialThaumaturgy.common.core.EssentialThaumaturgy;
import essentialThaumaturgy.common.tile.TileNodeIrradiator;
import essentialThaumaturgy.common.tile.TileRadiatedNode;
import essentialThaumaturgy.common.utils.ThaumcraftHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockNodeIrradiator extends BlockContainer{
	
	public IIcon blankIcon;
	
	public BlockNodeIrradiator()
	{
		super(Material.rock);
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
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        return ForgeDirection.OPPOSITES[side];
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
    

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new TileNodeIrradiator();
	}
	
	@Override
	 public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	 {
	     if (par1World.isRemote)
	     {
	         return true;
	     }else
	     {
		     if(!par5EntityPlayer.isSneaking())
		     {
		    	 par5EntityPlayer.openGui(EssentialThaumaturgy.core, 7322, par1World, par2, par3, par4);
		         return true;
		     }
		     else
		     {
		    	 return false;
		     }
	     }
	 }
	
}
