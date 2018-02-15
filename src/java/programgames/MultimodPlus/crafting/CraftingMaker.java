package programgames.MultimodPlus.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import programgames.MultimodPlus.block.FirstBlock;
import programgames.MultimodPlus.item.FirstItem;

public class CraftingMaker {

	public static void make()
	{
	    GameRegistry.addRecipe(new ItemStack(FirstBlock.tutorialBlock), new Object[] {"##", "##", '#', FirstItem.tutorialItem});
	    GameRegistry.addSmelting(Items.diamond, new ItemStack(FirstItem.tutorialItem), 1.0F);
	}

}
