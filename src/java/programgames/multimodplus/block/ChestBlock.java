package programgames.multimodplus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import programgames.multimodplus.Main;
import programgames.multimodplus.client.RenderInventoryMaker;
import programgames.multimodplus.tileentity.TileEntityChest;


/**
 * This class reprensent a block used as chest .
 *
 * @author programgames
 */
public class ChestBlock extends Block {

  /** The icons. */
  public IIcon[] icons = new IIcon[6];

  
  /**
   * Instancie un nouveau chest block.
   *
   * @param unlocalizedName the unlocalized name
   * @param material the material
   */
  public ChestBlock(String unlocalizedName, Material material) {
    super(material);
    this.setBlockName(unlocalizedName);
    // this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
    this.setCreativeTab(CreativeTabs.tabBlock);
    this.setHardness(2.0F);
    this.setResistance(6.0F);
    this.setStepSound(soundTypeGravel);
  }

  /**
   * Say if a tile entity is present.
   */
  @Override
  public boolean hasTileEntity(int metadata) {
    return true;
  }

  /* (non-Javadoc)
   * @see net.minecraft.block.Block#createTileEntity(net.minecraft.world.World, int)
   */
  
  /**
   * function creating the tile entity for the block.
   * @param world le monde
   * @param metadata la metadata
   * @return the tile entity of the block
   */
  @Override
  public TileEntity createTileEntity(World world, int metadata) {
    return new TileEntityChest();
  }

  /* (non-Javadoc)
   * @see net.minecraft.block.Block#onBlockPlacedBy(net.minecraft.world.World, int, int, int,
   * net.minecraft.entity.EntityLivingBase, net.minecraft.item.ItemStack)
   */
  /**
   * Function called when the block is placed.She set the direction of the entity
   * who placed the block.
   */
  public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living,
      ItemStack stack) {
    
    if (stack.getItemDamage() == 0) {
      TileEntity tile = world.getTileEntity(x, y, z);
      if (tile instanceof TileEntityChest) {
        int direction =
            MathHelper.floor_double((double) (living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        ((TileEntityChest) tile).setDirection((byte) direction);
      }
    }
    TileEntity tile = world.getTileEntity(x, y, z);
    if (tile instanceof TileEntityChest) {
      if (stack.hasDisplayName()) {
        ((TileEntityChest) tile).setCustomName(stack.getDisplayName());
      }
    }

  }

  /* (non-Javadoc)
   * @see net.minecraft.block.Block#rotateBlock
   * (net.minecraft.world.World, int, int, int, net.minecraftforge.common.util.ForgeDirection)
   */
  /**
   * function used to rotate the block, in terms of the direction.
   */
  @Override
  public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
    if ((axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) && !world.isRemote
        && world.getBlockMetadata(x, y, z) == 0) {
      TileEntity tile = world.getTileEntity(x, y, z);
      if (tile instanceof TileEntityChest) {
        TileEntityChest tileTuto = (TileEntityChest) tile;
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

  /* (non-Javadoc)
   * @see net.minecraft.block.Block#getValidRotations(net.minecraft.world.World, int, int, int)
   */
  /**
   * Comptablity of the rotation for the forge API.
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

  /* (non-Javadoc)
   * @see net.minecraft.block.Block#getRenderType()
   */
  /**
   * get the id of the inventory id renderer.
   */
  public int getRenderType() {
    return RenderInventoryMaker.tesrRenderId;
  }

  /* (non-Javadoc)
   * @see net.minecraft.block.Block#onBlockActivated(net.minecraft.world.World, int, int, int,
   * net.minecraft.entity.player.EntityPlayer, int, float, float, float)
   */
  /**
   * Called when we right-click on the block.
   */
  public boolean onBlockActivated(World world, int x, int y, int z,
      EntityPlayer player, int side, float hitX,  float hitY, float hitZ) {
    if (world.isRemote) {
      return true;
    } else {
      player.openGui(Main.instance, 0, world, x, y, z);
      return true;
    }
  }

  /* (non-Javadoc)
   * @see net.minecraft.block.Block#breakBlock(net.minecraft.world.World, int, int, int,
   * net.minecraft.block.Block, int)
   */
  /**
   * Called when the block is destroyed to return all the item entity onthe ground.
   */
  public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
    
    TileEntity tileentity = world.getTileEntity(x, y, z);

    if (tileentity instanceof IInventory) {
      
      IInventory inv = (IInventory) tileentity;
      
      for (int i1 = 0; i1 < inv.getSizeInventory(); ++i1) {
        ItemStack itemstack = inv.getStackInSlot(i1);

        if (itemstack != null) {
          
          float f = world.rand.nextFloat() * 0.8F + 0.1F;
          float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
          
          EntityItem entityitem;

          for (float f2 = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world
              .spawnEntityInWorld(entityitem)) {
            
            int j1 = world.rand.nextInt(21) + 10;

            if (j1 > itemstack.stackSize) {
              j1 = itemstack.stackSize;
            }

            itemstack.stackSize -= j1;
            entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1),
                (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j1,
                    itemstack.getItemDamage()));
            float f3 = 0.05F;
            
            entityitem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
            entityitem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.2F);
            entityitem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);

            if (itemstack.hasTagCompound()) {
              entityitem.getEntityItem().setTagCompound((NBTTagCompound)
                  itemstack.getTagCompound().copy());
            }
          }
        }
      }
      world.func_147453_f(x, y, z, block);
    }
    super.breakBlock(world, x, y, z, block, metadata);
  }

  // HITBOX https://www.minecraftforgefrance.fr/showthread.php?tid=1509#classe5
}
