package essentialThaumaturgy.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;

public class BlockColoredTransparent extends Block{
	
	public BlockColoredTransparent() {
		super(Material.glass);
	}

	public BlockColoredTransparent(Material p_i45394_1_) {
		super(p_i45394_1_);
	}
	
	 @SideOnly(Side.CLIENT)
	 public int getRenderBlockPass()
	 {
	     return 1;
	 }
	 
	    public boolean isOpaqueCube()
	    {
	        return false;
	    }

	    @SideOnly(Side.CLIENT)
	    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	    {
	        Block block = p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_);

	        if (true)
	        {
	            if (p_149646_1_.getBlockMetadata(p_149646_2_, p_149646_3_, p_149646_4_) != p_149646_1_.getBlockMetadata(p_149646_2_ - Facing.offsetsXForSide[p_149646_5_], p_149646_3_ - Facing.offsetsYForSide[p_149646_5_], p_149646_4_ - Facing.offsetsZForSide[p_149646_5_]))
	            {
	                return true;
	            }

	            if (block == this)
	            {
	                return false;
	            }
	        }

	        return super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
	    }

}
