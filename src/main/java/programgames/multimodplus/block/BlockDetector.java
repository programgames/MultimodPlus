package programgames.multimodplus.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockRedstoneOre;
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

  private boolean isBlockConnected;

  private boolean isBlockConnected() {
    return isBlockConnected;
  }

  public void setBlockConnected(boolean blockConnected) {
    isBlockConnected = blockConnected;
  }

  public BlockDetector(String unlocalizedName, Material material) {
    super(material);
    this.setBlockName(unlocalizedName);
    this.setCreativeTab(CreativeTabs.tabBlock);
    this.setHardness(2.0F);
    this.setResistance(6.0F);
    setHarvestLevel("pickaxe", 3);
    this.setStepSound(soundTypeGravel);
    this.isBlockConnected = false;
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

    if (side == 1 && this.isBlockConnected) {
      return 15;
    } else {
      return 0;
    }

  }

  /**
   * Ticks the block if it's been scheduled.
   * @param world The world object
   * @param x coordinate
   * @param y coordinate
   * @param z coordinate
   * @param r RandomTick
   */
  public void updateTick(World world, int x, int y, int z, Random r) {
    if ((world.getBlock(x, y + 1, z) != null)
            && !(world.getBlock(x, y + 1, z) instanceof BlockAir)) {
      setBlockConnected(true);
    }
    System.out.println("updated");
  }

  /**
   * How many world ticks before ticking.
   *
   * @param world The World.
   */
  @Override
  public int tickRate(World world) {
    return  20;
  }

  @SideOnly(Side.CLIENT)
  @Override
  public void registerBlockIcons(IIconRegister iconRegister) {
    this.top = iconRegister.registerIcon("multimodplus:collector_top");
    this.sides = iconRegister.registerIcon("multimodplus:collector_side");
    this.front = iconRegister.registerIcon("multimodplus:detector4_front");
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
    if (side == 1) {
      return top;
    }
    int facing = 2;
    TileEntityDetector machine = (TileEntityDetector) world.getTileEntity(x, y, z);
    if (machine != null) {
      facing = machine.getFacing();
    }
    if (side == facing) {
      return front;
    } else {
      return sides;
    }
  }

  /**
   * Gets the block's texture. Args: side, meta
   * @param side Side of the block
   * @param metadata metadata of the block
   * @return IIcon icon of the block
   */
  @Override
  public IIcon getIcon(int side, int metadata) {
    if (side == 1) {
      return top;
    }
    if (side == 3) {
      return front;
    }
    return sides;
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
      player.addChatMessage(new ChatComponentText("Connection de block : " + isBlockConnected()));
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

      if ((world.getBlock(x, y + 1, z) != null)
              && !(world.getBlock(x, y + 1, z) instanceof BlockAir)) {
        setBlockConnected(true);
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
