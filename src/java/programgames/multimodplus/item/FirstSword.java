package programgames.multimodplus.item;

import net.minecraft.item.ItemSword;
import programgames.multimodplus.Main;

public class FirstSword  extends ItemSword {

	
	public FirstSword(String unlocalizedName, ToolMaterial material) {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(Main.MODID + ":" + unlocalizedName);
}
}
