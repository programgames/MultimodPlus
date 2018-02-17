package programgames.MultimodPlus.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import programgames.MultimodPlus.Main;

public class StrongArmor extends ItemArmor {

	
	public String textureName;

	public StrongArmor(String unlocalizedName, ArmorMaterial material, String textureName, int type) {
	    super(material, 0, type);
	    this.textureName = textureName;
	    this.setUnlocalizedName(unlocalizedName);
	    this.setTextureName(Main.MODID + ":" + unlocalizedName);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
	    return Main.MODID + ":textures/armor/" + this.textureName + (this.armorType == 2 ? "2" : "1") + ".png";
	}
}
