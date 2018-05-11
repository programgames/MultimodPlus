package programgames.multimodplus.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import programgames.multimodplus.tileentity.TileEntityDetector;



public class BlockDetector  extends BlockContainer {

  private IIcon top;
  private IIcon sides;
  private IIcon front;
  private IIcon back;
  private IIcon back_powered;

  /**
   * Constructor of the Block.
   *
   * @param unlocalizedName the unlocalizedName
   * @param material material
   */
  public BlockDetector(String unlocalizedName, Material material) {
    super(material);
    this.setBlockName(unlocalizedName);
    this.setCreativeTab(CreativeTabs.tabBlock);
    this.setHardness(2.0F);
    this.setResistance(6.0F);
    setHarvestLevel("pickaxe", 2);
    this.setStepSound(soundTypeStone);

  }

  /**
   * Return the power of redstone on all side.
   * @param blockAccess The Block acess
   * @param x coordinate
   * @param y coordinate
   * @param z coordinate
   * @param side The side of the block
   * @return int level of redstone
   */
  @Override
  public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int side) {

    if (side == 1 && ((TileEntityDetector)blockAccess.getTileEntity(x,y,z)).isConnected) {
      return 15;
    } else {
      return 0;
    }

  }

  @Override
  public void registerBlockIcons(IIconRegister iconRegister) {
    this.top = iconRegister.registerIcon("multimodplus:detector_top");
    this.sides = iconRegister.registerIcon("multimodplus:detector_side");
    this.front = iconRegister.registerIcon("multimodplus:detector_front");
    this.back =  iconRegister.registerIcon("multimodplus:detector_back");
    this.back_powered =  iconRegister.registerIcon("multimodplus:detector_back_powered");

  }

  /**
   * Can this block provide power. Only wire currently seems to have this change based on its state.
   */
  @Override
  public boolean canProvidePower() {
    return true;
  }

  /**
   * Return icons of the block.
   * @param world BlockAcess
   * @param x coordinate
   * @param y coordinate
   * @param z coordinate
   * @param side Side of the block
   * @return IIcon Icon of the block
   */
  @Override
  public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {

    int facing = 2;

    TileEntityDetector machine = (TileEntityDetector) world.getTileEntity(x, y, z);
    if (machine != null) {
      facing = machine.getFacing();
    }
    if(side == 0 || side == 4 || side == 5)
    {
      return this.sides;
    }

    return null;

  }

  /**
   * Gets the block's texture. Args: side, meta
   * @param side Side of the block
   * @param metadata metadata of the block
   * @return IIcon icon of the block
   */
  @Override
  public IIcon getIcon(int side, int metadata) {
//    if (side == 1) {
//      return top;
//    }
//    if (side == 3) {
//      return front;
//    }
//
    return null;
  }

  /**
   * Called upon block activation (right click on the block.)
   * @param world  world object
   * @param x coordinate
   * @param y coordinate
   * @param z coordinate
   * @param player player's entity
   * @param par6 par6
   * @param par7 par7
   * @param par8 par8
   * @param par9 par9
   * @return boolean
   */
  @Override
  public boolean onBlockActivated(World world, int x, int y, int z,
                                   EntityPlayer player,
                                   int par6, float par7, float par8, float par9) {
    if (world.isRemote) {
      return true;
    } else {
      player.addChatMessage(new ChatComponentText("face : "
              + ((TileEntityDetector)world.getTileEntity(x,y,z)).getFacing()));

      return true;
    }
  }

  /**
   * Returns a new instance of a block's tile entity class. Called on placing the block.
   *
   * @param world The world
   * @param meta The metadata
   * @return TileEntityDetector
   */
  @Override
  public TileEntity createNewTileEntity(World world, int meta) {
    return new TileEntityDetector();
  }

  /**
   * Called when the block is placed in the world.
   * @param world world object
   * @param x coordinate
   * @param y coordinate
   * @param z coordinate
   * @param player player's
   * @param item  ItemStack of the Block.
   */
  @Override
  public void onBlockPlacedBy(World world, int x, int y, int z,
                              EntityLivingBase player, ItemStack item) {
    TileEntity tile = world.getTileEntity(x, y, z);
    if (tile instanceof TileEntityDetector) {
      TileEntityDetector machine = (TileEntityDetector)tile;
      int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

      if (l == 0) {
        machine.setFacing(2);
      }

      if (l == 1) {
        machine.setFacing(5);
      }

      if (l == 2) {
        machine.setFacing(3);
      }

      if (l == 3) {
        machine.setFacing(4);
      }
    }
  }

  /**
   * Called when block is broken.
   * @param world world object
   * @param x coordinate
   * @param y coordinate
   * @param z coordinate
   * @param block Broken block
   * @param wut wut ?
   */
  @Override
  public void breakBlock(World world, int x, int y, int z, Block block, int wut) {
    world.func_147453_f(x, y, z, block);
    super.breakBlock(world, x, y, z, block, wut);
    world.removeTileEntity(x,y,z);
  }




  /**
   * Determine if this block can make a redstone connection on the side provided,
   * Useful to control which sides are inputs and outputs for redstone wires.
   * Side:
   * -1: UP
   * 0: NORTH
   * 1: EAST
   * 2: SOUTH
   * 3: WEST
   *
   * @param world The current world
   * @param x     X Position
   * @param y     Y Position
   * @param z     Z Position
   * @param side  The side that is trying to make the connection
   * @return True to make the connection
   */
  @Override
  public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
    if (side == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Called throughout the code as a replacement for ITileEntityProvider.createNewTileEntity.
   * Return the same thing you would from that function.
   * This will fall back to ITileEntityProvider.createNewTileEntity(World)
   * if this block is a ITileEntityProvider
   *
   * @param world The world object.
   * @param metadata The Metadata of the current block
   * @return A instance of a class extending TileEntity
   */
  @Override
  public TileEntity createTileEntity(World world, int metadata) {
    return new TileEntityDetector();
  }

  /**
   * Called throughout the code as a replacement for block instanceof BlockContainer
   * Moving this to the Block base class allows for mods that wish to extend vanilla
   * blocks, and also want to have a tile entity on that block, may.
   * Return true from this function to specify this block has a tile entity.
   *
   * @param metadata Metadata of the current block
   * @return True if block has a tile entity, false otherwise
   */
  @Override
  public boolean hasTileEntity(int metadata) {
    return true;
  }
}
