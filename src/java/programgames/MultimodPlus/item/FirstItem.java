package programgames.MultimodPlus.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import programgames.MultimodPlus.Main;
import programgames.MultimodPlus.block.FirstBlock;


public class FirstItem {

	public static Item tutorialItem;

    public static final void init() {
        tutorialItem = new Item();
        
        tutorialItem = new Item().setUnlocalizedName("firstItem").setCreativeTab(CreativeTabs.tabMisc).setTextureName(Main.MODID + ":icone");;
        GameRegistry.registerItem(tutorialItem, "tutorialItem");

    }
}
