package programgames.multimodplus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import programgames.multimodplus.Main;
import programgames.multimodplus.tileentity.TileEntityCounter;

/**
 * This Class reprensent a block capable of counting when we click on it.
 * 
 * @author programgames
 *
 */
public class CounterBlock extends Block {

  /** The icons(6 faces). */
  public IIcon[] icons = new IIcon[6];

  /**
   * Instancie un nouveau counter block.
   *
   * @param unlocalizedName
   *          the unlocalized name
   * @param material
   *          the material
   */
  public CounterBlock(String unlocalizedName, Material material) {
    super(material);
    this.setBlockName(unlocalizedName);
    this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
    this.setCreativeTab(CreativeTabs.tabBlock);
    this.setHardness(2.0F);
    this.setResistance(6.0F);
    this.setStepSound(soundTypeGravel);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.minecraft.block.Block#registerBlockIcons(net.minecraft.client.renderer.
   * texture.IIconRegister)
   */
  @Override
  public void registerBlockIcons(IIconRegister reg) {

    for (int i = 0; i < 6; i++) {
      this.icons[i] = reg.registerIcon(this.textureName + i);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.minecraft.block.Block#getIcon(int, int)
   */
  @Override
  public IIcon getIcon(int side, int metadata) {

    if (metadata == 1) {
      if (side == 4) {
        return this.icons[3];
      }
      if (side == 3) {
        return this.icons[5];
      }
      if (side == 5) {
        return this.icons[2];
      }
      if (side == 2) {
        return this.icons[4];
      }
      if (side == 1 || side == 0) {
        return this.icons[side];
      }
    }
    if (metadata == 2) {
      if (side == 4) {
        return this.icons[5];
      }
      if (side == 3) {
        return this.icons[2];
      }
      if (side == 5) {
        return this.icons[4];
      }
      if (side == 2) {
        return this.icons[3];
      }
      if (side == 1 || side == 0) {
        return this.icons[side];
      }
    }
    if (metadata == 3) {
      if (side == 4) {
        return this.icons[5];
      }
      if (side == 3) {
        return this.icons[4];
      }
      if (side == 5) {
        return this.icons[3];
      }
      if (side == 2) {
        return this.icons[5];
      }
      if (side == 1 || side == 0) {
        return this.icons[side];
      }
    }
    return this.icons[side];

  }

  /**
   * override the placement of the block.
   * 
   * @see net.minecraft.block.Block#onBlockPlacedBy(net.minecraft.world.World,
   * int, int, int, net.minecraft.entity.EntityLivingBase,
   * net.minecraft.item.ItemStack)
   */
  public void onBlockPlacedBy(World world, int x, int y, int z,
      EntityLivingBase living, ItemStack stack) {
    int direction = MathHelper.floor_double((double) (living.rotationYaw * 4.0F / 360.0F)
        + 2.5D) & 3;
    
    world.setBlockMetadataWithNotify(x, y, z, direction, 2);
  }

  /**
   * override the rotation.
   * 
   * @see net.minecraft.block.Block#rotateBlock(net.minecraft.world.World, int,
   * int, int, net.minecraftforge.common.util.ForgeDirection)
   */
  public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
    if ((axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) && !world.isRemote) {
      int direction = world.getBlockMetadata(x, y, z) + 1;
      if (direction > 3) {
        direction = 0;
      }
      world.setBlockMetadataWithNotify(x, y, z, direction, 3);
      return true;
    }
    return false;
  }

  /**
   * Override the actication of the block.
   * 
   * @see net.minecraft.block.Block#onBlockActivated(net.minecraft.world.World,
   * int, int, int, net.minecraft.entity.player.EntityPlayer, int, float, float,
   * float)
   */
  
  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
      int side, float hitX, float hitY, float hitZ) {
    /*
     * petit explication sur la condition world.isRemote world.isRemote = monde
     * client, c'est celui qui va gérer le rendu !world.isRemote = monde serveur, il
     * va gérer le reste, notamment la sauvegarde, les variables d'une entité de
     * bloc sont donc à manipuler côté serveur seulement, d'où la condition juste en
     * dessous
     */
    if (!world.isRemote) {
      TileEntity tile = world.getTileEntity(x, y, z); // on obtient l'instance du TileEntity
      if (tile instanceof TileEntityCounter) {
        
        TileEntityCounter tileTuto = (TileEntityCounter) tile; 
                                                              
        if (side == 0) {
          tileTuto.decrease();
        } else if (side == 1) {
          tileTuto.increase();
        }
        player.addChatMessage(new ChatComponentTranslation("tile.tutoriel2.number",
            tileTuto.getNumber())); 
        return true;
      }
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.minecraft.block.Block#getValidRotations(net.minecraft.world.World,
   * int, int, int)
   */
  public ForgeDirection[] getValidRotations(World world, int x, int y, int z) {
    return new ForgeDirection[] { ForgeDirection.UP, ForgeDirection.DOWN };
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.minecraft.block.Block#createTileEntity(net.minecraft.world.World,
   * int)
   */
  @Override
  public TileEntity createTileEntity(World world, int metadata) {
    return new TileEntityCounter();
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.minecraft.block.Block#hasTileEntity(int)
   */
  @Override
  public boolean hasTileEntity(int metadata) {
    return true;
  }

}
