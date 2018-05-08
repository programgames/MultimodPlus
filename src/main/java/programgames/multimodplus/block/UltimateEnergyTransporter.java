package programgames.multimodplus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import programgames.multimodplus.crafting.CraftingMaker;
import programgames.multimodplus.tileentity.TileEntityTransporter;

public class UltimateEnergyTransporter extends Block {


  /**
   * Create a block.
   * @param  unlocalizedName name.
   * @param material the material.
   */
  public UltimateEnergyTransporter(String unlocalizedName, Material material) {
    
    super(material);
    this.setBlockName(unlocalizedName);
    // this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
    this.setHardness(2.0F);
    this.setResistance(6.0F);
    this.setStepSound(soundTypeGravel);
    this.setCreativeTab(CraftingMaker.tutorialCreativeTabs);
  }
  
  @Override
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  @Override
  public TileEntity createTileEntity(World world, int metadata) {
    return new TileEntityTransporter(800);
  }


  /**
   * Called when the block is placed in the world.
   */
  public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living,
      ItemStack stack) {
    
    if (stack.getItemDamage() == 0) {
      TileEntity tile = world.getTileEntity(x, y, z);
      if (tile instanceof TileEntityTransporter) {
        int direction =
            MathHelper.floor_double((double) (living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        ((TileEntityTransporter) tile).setDirection((byte) direction);
      }
    }
    TileEntity tile = world.getTileEntity(x, y, z);
    if (tile instanceof TileEntityTransporter) {
      if (stack.hasDisplayName()) {
        ((TileEntityTransporter) tile).setCustomName(stack.getDisplayName());
      }
    }

  }
  
  @Override
  public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
    if ((axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) && !world.isRemote
        && world.getBlockMetadata(x, y, z) == 0) {
      TileEntity tile = world.getTileEntity(x, y, z);
      if (tile instanceof TileEntityTransporter) {
        TileEntityTransporter tileTuto = (TileEntityTransporter) tile;
        byte direction = tileTuto.getDirection();
        direction++;
        if (direction > 3) {
          direction = 0;
        }
        tileTuto.setDirection(direction);
        return true;
      }
    }
    return false;
  }

  /**
   * Get the rotations that can apply to the block at the specified coordinates.
   * Null means no rotations are possible.
   * Note, this is up to the block to decide. It may not be accurate or representative.
   * @param world The world
   * @param x X position
   * @param y Y position
   * @param z Z position
   * @return An array of valid axes to rotate around, or null for none or unknown
   */
  public ForgeDirection[] getValidRotations(World world, int x, int y, int z) {
    return world.getBlockMetadata(x, y, z) == 0
        ? new ForgeDirection[] { ForgeDirection.UP, ForgeDirection.DOWN }
        : ForgeDirection.VALID_DIRECTIONS;
  }

  /* (non-Javadoc)
   * @see net.minecraft.block.Block#isOpaqueCube()
   */
  /**
   * Return to is the block is transparent.
   */
  public boolean isOpaqueCube() {
    return false;
  }

  /* (non-Javadoc)
   * @see net.minecraft.block.Block#renderAsNormalBlock()
   */
  /**
   * return false here because this block is rendered not in the normal way.
   */
  public boolean renderAsNormalBlock() {
    return false;
  }
  
  
}
