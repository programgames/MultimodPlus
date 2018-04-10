package programgames.multimodplus.item;

import net.minecraft.item.ItemSword;
import programgames.multimodplus.Main;

// TODO: Auto-generated Javadoc
/**
 * The Class FirstSword reprensent the red/grey sword.
 */
public class FirstSword  extends ItemSword {


  /**
   * Instancie un nouveau first sword.
   *
   * @param unlocalizedName the unlocalized name
   * @param material the material
   */
  public FirstSword(String unlocalizedName, ToolMaterial material) {
    super(material);
    this.setUnlocalizedName(unlocalizedName);
    this.setTextureName(Main.MODID + ":" + unlocalizedName);
  }
}
