package programgames.multimodplus.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import programgames.multimodplus.block.BlockMaker;
import programgames.multimodplus.creativetabs.MultimodCreativeTabs;
import programgames.multimodplus.item.ItemMaker;

/**
 * This class is used to register recipes.
 * @author programgames
 *
 */
public class CraftingMaker {

  public static CreativeTabs tutorialCreativeTabs
      = new MultimodCreativeTabs("tutorial_creative_tabs");

  /**
   * Function creating some recipes.
   */
  public static void make() {
    GameRegistry.addRecipe(new ItemStack(BlockMaker.simpleBlock),
        new Object[] { "##", "##", '#', ItemMaker.tutorialItem });
    GameRegistry.addSmelting(Items.diamond, new ItemStack(ItemMaker.tutorialItem), 1.0F);




    // register a craft using metadata of an item ex : specif dye
    // GameRegistry.addShapelessRecipe(new ItemStack(Blocks.coal_block), new
    // Object[]{new ItemStack(Items.dye, 1, 0)});
  }

}
