package programgames.multimodplus.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;
import programgames.multimodplus.Main;


// TODO: Auto-generated Javadoc
/**
 * this class reprensent the registering of all item.
 * @author JULIEN
 *
 */
public class ItemMaker {

  /** The metaitem. */
  public static Item metaitem;
  
  /** The tutorial item. */
  public static Item tutorialItem;
  
  /** The tutorial sword. */
  public static Item tutorialSword;
  
  /** The multitool. */
  public static Item multitool;
  
  /** The tutorial helmet. */
  public static Item tutorialHelmet;
  
  /** The tutorial chestplate. */
  public static Item tutorialChestplate;
  
  /** The tutorial leggings. */
  public static Item tutorialLeggings;
  
  /** The tutorial boots. */
  public static Item tutorialBoots;
  
  /** The tutorial. */
  public static ToolMaterial tutorial;
  
  /** The tutorial armor. */
  public static ArmorMaterial tutorial_armor;
  
  /** The chocolate. */
  public static ItemFood chocolate;
  
  /** The key. */
  public static Item key;

  public static Item blackIngot;
  
  /**
   * Inits the itemMaker.
   */
  public static final void init() {

    tutorialItem = new Item().setUnlocalizedName("firstItem")
        .setCreativeTab(CreativeTabs.tabMisc).setTextureName(Main.MODID + ":icone");;
    GameRegistry.registerItem(tutorialItem, "tutorialItem");
    GameRegistry.registerItem(metaitem = new MetaItem("metaitem"), "metaitem");

    tutorial = EnumHelper.addToolMaterial("tutorial", 3, 1000, 4.0F, 4.0F, 30);
    GameRegistry.registerItem(tutorialSword = new FirstSword("tutorial_sword", tutorial)
        .setTextureName(Main.MODID + ":sword"), "tutorial_sword");
    
    GameRegistry.registerItem(multitool = new ItemModMultitool("tutorial_multitool", tutorial)
        .setTextureName(Main.MODID + ":multitool"), "multitool");

    tutorial_armor = EnumHelper.addArmorMaterial("tutorial_armor", 16, new int[] {3, 8, 6, 3}, 30);
    GameRegistry.registerItem(tutorialHelmet =
        new StrongArmor("StrongArmor0",tutorial_armor, "StrongArmor", 0),
        "StrongHelmet"); //0 for helmet
    
    GameRegistry.registerItem(tutorialChestplate =
        new StrongArmor("StrongArmor1",tutorial_armor, "StrongArmor", 1),
        "StrongChestplate"); // 1 for chestplate
    
    GameRegistry.registerItem(tutorialLeggings = new StrongArmor("StrongArmor2",tutorial_armor,
        "StrongArmor", 2), "StrongLeggings"); // 2 for leggings
    GameRegistry.registerItem(tutorialBoots = new StrongArmor("StrongArmor3",tutorial_armor,
        "StrongArmor", 3), "StrongBoots"); // 3 for boots


    GameRegistry.registerItem(chocolate = new FirstFood(
            "chocolate",
            2,
            0.2f,
            false,
        new PotionEffect(Potion.moveSpeed.id, 1200, 1),
        new PotionEffect(Potion.jump.id, 600, 0),
        new PotionEffect(Potion.regeneration.id, 200, 1))
        .setAlwaysEdible(), "chocolate");

    GameRegistry.registerItem(key = new Key("key").setTextureName(Main.MODID + ":key"),"key");
    GameRegistry.registerItem(
            blackIngot = new BlackIngot(
                    "blackIngot").setTextureName(Main.MODID + ":blackIngot"),"black Ingot");

  }
}
