package programgames.MultimodPlus.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import programgames.MultimodPlus.Main;


public class ItemMaker {

	public static Item metaitem;
	public static Item tutorialItem;
	
	   public static final void init() {
	    
		   tutorialItem = new Item();
	        
	        tutorialItem = new Item().setUnlocalizedName("firstItem").setCreativeTab(CreativeTabs.tabMisc).setTextureName(Main.MODID + ":icone");;
	        GameRegistry.registerItem(tutorialItem, "tutorialItem");
		   GameRegistry.registerItem(metaitem = new MetaItem("metaitem"), "metaitem");
	    }
}
