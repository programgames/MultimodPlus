package programgames.MultimodPlus.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import programgames.MultimodPlus.item.FirstItem;

public class FirstBlock {

	 public static Block tutorialBlock;

	    public static final void init() {
	    	GameRegistry.registerBlock(tutorialBlock = new BasicBlock("texture", Material.iron), "tutorialBlock");

	    }
}
