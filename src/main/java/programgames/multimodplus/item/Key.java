package programgames.multimodplus.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

// TODO: Auto-generated Javadoc
/**
 * The Class Key.
 */
public class Key extends Item {

  /**
   * Instancie un nouveau key.
   *
   * @param unlocalizedName the unlocalized name
   */
  public Key(String unlocalizedName) {
    super();
    this.setHasSubtypes(true);//not stack item with same metadata
    this.setUnlocalizedName(unlocalizedName);
    this.setCreativeTab(CreativeTabs.tabMaterials);
  }

  /* (non-Javadoc)
   * @see net.minecraft.item.Item#onItemUse(net.minecraft.item.ItemStack,
   * net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World,
   * int, int, int, int, float, float, float)
   */
  /**
   * Define what happen when used.
   */
  public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y,
      int z, int side, float hitX, float hitY, float hitZ) {
    if (!world.isRemote) {
      player.addChatMessage(new ChatComponentText("serveur : side " + side));
      player.addChatMessage(new ChatComponentText("serveur : metadata "
          + world.getBlockMetadata(x, y, z)));
    }
    if (world.getBlock(x, y, z).rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side))) {
      return true;
    }
    return false;
  }

}
