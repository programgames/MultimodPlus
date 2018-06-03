package programgames.multimodplus.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import programgames.multimodplus.Main;



public class MetaItem extends Item {

  /**
   * The Icons.
   */
  public IIcon[] icons = new IIcon[6];

  /**
   * Instancie un nouveau meta item qui a chaque metadata a une nouvbelle texture.
   *
   * @param unlocalizedName the unlocalized name
   */
  public MetaItem(String unlocalizedName) {
    super();
    this.setHasSubtypes(true);//not stack item with same metadata
    this.setUnlocalizedName(unlocalizedName);
    this.setCreativeTab(CreativeTabs.tabMaterials);
  }

  /**
   * Method the registerIcons ...
   *
   * @param reg of type IIconRegister
   */
  @Override
  public void registerIcons(IIconRegister reg) {
    for (int i = 0; i < 6; i++) {
      this.icons[i] = reg.registerIcon(Main.MODID + ":multitexture" + i);
    }
  }

  /**
   * Method the getIconFromDamage ...
   *
   * @param meta of type int
   * @return IIcon
   */
  @Override
  public IIcon getIconFromDamage(int meta) {
    if (meta > 5) {
      meta = 0;
    }
    return this.icons[meta];
  }


  /**
   * Method the getSubItems ...
   *
   * @param item of type Item
   * @param tab of type CreativeTabs
   * @param list of type List
   */
  @SuppressWarnings("unchecked")
  @Override
  public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List list) {
    for (int i = 0; i < 6; i++) {
      list.add(new ItemStack(item, 1, i));
    }
  }


  /**
   * Method the onItemUse ...
   *
   * @param stack of type ItemStack
   * @param player of type EntityPlayer
   * @param world of type World
   * @param x of type int
   * @param y of type int
   * @param z of type int
   * @param side of type int
   * @param hitX of type float
   * @param hitY of type float
   * @param hitZ of type float
   * @return boolean
   */
  public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z,
                           int side, float hitX, float hitY, float hitZ) {
    if (!world.isRemote) {
      player.addChatMessage(new ChatComponentText("serveur : side " + side));
      player.addChatMessage(new ChatComponentText("serveur : metadata " + world.getBlockMetadata(x, y, z)));
    }
    if (world.getBlock(x, y, z).rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side))) {
      return true;
    }
    return false;
  }

  /**
   * Method the getUnlocalizedName ...
   *
   * @param stack of type ItemStack
   * @return String
   */
  @Override
  public String getUnlocalizedName(ItemStack stack) {
    return this.getUnlocalizedName() + stack.getItemDamage();
  }
}