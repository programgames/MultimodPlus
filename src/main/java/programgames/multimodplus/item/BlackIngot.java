package programgames.multimodplus.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * The type Black ingot.
 */
public class BlackIngot extends Item {

  /**
   * Constructor of a basic black  ingot.
   * @param unlocalizedName the unlocalized name.
   */
  public BlackIngot(String unlocalizedName) {
    super();
    this.setHasSubtypes(true);//not stack item with same metadata
    this.setUnlocalizedName(unlocalizedName);
    this.setCreativeTab(CreativeTabs.tabMaterials);
  }
}
