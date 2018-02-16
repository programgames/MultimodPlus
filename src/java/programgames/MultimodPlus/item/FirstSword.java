package programgames.MultimodPlus.item;

import net.minecraft.item.ItemSword;
import programgames.MultimodPlus.Main;

public class FirstSword  extends ItemSword {

	
	public FirstSword(String unlocalizedName, ToolMaterial material) {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(Main.MODID + ":" + unlocalizedName);
}
}
