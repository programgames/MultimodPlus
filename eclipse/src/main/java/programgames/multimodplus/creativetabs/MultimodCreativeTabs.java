package programgames.multimodplus.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import programgames.multimodplus.block.BlockMaker;

public class MultimodCreativeTabs extends CreativeTabs {

  /**
   * Instancie un nouveau multimod creative tabs.
   *
   * @param lable the lable
   */
  public MultimodCreativeTabs(String lable) {
    super(lable);

    // TODO Stub du constructeur généré automatiquement
  }
  
  @Override
  public Item getTabIconItem() {
    return Item.getItemFromBlock(BlockMaker.block16);
  }

}
