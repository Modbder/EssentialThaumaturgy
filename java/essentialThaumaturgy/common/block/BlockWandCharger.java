package essentialThaumaturgy.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import essentialThaumaturgy.common.core.EssentialThaumaturgy;
import essentialThaumaturgy.common.tile.TileMRUAuraDestructor;
import essentialThaumaturgy.common.tile.TileMRUAuraStabiliser;
import essentialThaumaturgy.common.tile.TileMRUCrystalDestructor;
import essentialThaumaturgy.common.tile.TileMRUWandCharger;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockWandCharger extends BlockContainer{

	public IIcon[] sidedIcon = new IIcon[3];
	
	public BlockWandCharger()
	{
		super(Material.rock);
	}
	
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
    	sidedIcon[0] = p_149651_1_.registerIcon(getTextureName()+"_top");
    	sidedIcon[1] = p_149651_1_.registerIcon(getTextureName()+"_side");
    	sidedIcon[2] = p_149651_1_.registerIcon(getTextureName()+"_bot");
    }
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
    	if(p_149691_1_ == 0)
    		return sidedIcon[2];
    	if(p_149691_1_ == 1)
    		return sidedIcon[0];
        return this.sidedIcon[1];
    }
    
	protected BlockWandCharger(Material p_i45386_1_) {
		super(p_i45386_1_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new TileMRUWandCharger();
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
