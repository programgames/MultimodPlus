package programgames.multimodplus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import programgames.multimodplus.Main;

// TODO: Auto-generated Javadoc
/**
 * The Class StrongArmor represent the green armor.
 */
public class StrongArmor extends ItemArmor {


  /** The texture name. */
  public String textureName;

  /**
   * Instancie un nouveau strong armor.
   *
   * @param unlocalizedName the unlocalized name
   * @param material the material
   * @param textureName the texture name
   * @param type the type
   */
  public StrongArmor(String unlocalizedName, ArmorMaterial material, String textureName, int type) {
    super(material, 0, type);
    this.textureName = textureName;
    this.setUnlocalizedName(unlocalizedName);
    this.setTextureName(Main.MODID + ":" + unlocalizedName);
  }

  /* (non-Javadoc)
   * @see net.minecraft.item.Item
   * #getArmorTexture(net.minecraft.item.ItemStack,
   * net.minecraft.entity.Entity, int, java.lang.String)
   */
  @Override
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return Main.MODID + ":textures/armor/" + this.textureName
        + (this.armorType == 2 ? "2" : "1") + ".png";
  }
}
