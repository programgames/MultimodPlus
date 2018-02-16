package programgames.MultimodPlus.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import programgames.MultimodPlus.Main;


public class ItemMaker {

	public static Item metaitem;
	public static Item tutorialItem;
	public static Item tutorialSword;
	public static Item multitool;
	   public static final void init() {
	    
		   tutorialItem = new Item();
	       tutorialSword = new Item();
	       multitool = new Item();
	        tutorialItem = new Item().setUnlocalizedName("firstItem").setCreativeTab(CreativeTabs.tabMisc).setTextureName(Main.MODID + ":icone");;
	        GameRegistry.registerItem(tutorialItem, "tutorialItem");
		   GameRegistry.registerItem(metaitem = new MetaItem("metaitem"), "metaitem");
		   
		   ToolMaterial tutorial = EnumHelper.addToolMaterial("TUTORIAL", 3, 1000, 15.0F, 4.0F, 30);
		   GameRegistry.registerItem(tutorialSword = new FirstSword("tutorial_sword", tutorial).setTextureName(Main.MODID + ":sword"), "tutorial_sword");
		   GameRegistry.registerItem(multitool = new ItemModMultitool("tutorial_multitool", tutorial).setTextureName(Main.MODID + ":multitool"), "multitool");


	    }
}
