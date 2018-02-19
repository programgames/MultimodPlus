package programgames.MultimodPlus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import programgames.MultimodPlus.client.RenderInventoryMaker;
import programgames.MultimodPlus.tileentity.TileEntityVoid;

public class MultitexturedBlock extends Block {

	public IIcon[] icons = new IIcon[6];

	public MultitexturedBlock(String unlocalizedName, Material material) 
	{ 
		super(material);
		this.setBlockName(unlocalizedName);
		//this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(6.0F);
		this.setStepSound(soundTypeGravel);
	}
	
	 public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
	    {
	        if(stack.getItemDamage() == 0)
	        {
	            TileEntity tile = world.getTileEntity(x, y, z);
	            if(tile instanceof TileEntityVoid)
	            {
	                int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
	                ((TileEntityVoid)tile).setDirection((byte)direction);
	            }
	        }
	    }
	   @Override
	   public TileEntity createTileEntity(World world, int metadata)
	   {
	       return new TileEntityVoid();
	   }

	   @Override
	   public boolean hasTileEntity(int metadata)
	   {
	       return true;
	   }
	    public boolean isOpaqueCube()
	    {
	        return false;
	    }

	    public boolean renderAsNormalBlock()
	    {
	        return false;
	    }

	    public int getRenderType()
	    {
	        return RenderInventoryMaker.tesrRenderId;
	    }
	  
}
