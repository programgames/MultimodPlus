package programgames.MultimodPlus.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import programgames.MultimodPlus.block.BlockMaker;
import programgames.MultimodPlus.item.FirstItem;
import programgames.MultimodPlus.item.ItemMaker;

public class CraftingMaker {

	public static void make()
	{
	    GameRegistry.addRecipe(new ItemStack(BlockMaker.tutorialBlock), new Object[] {"##", "##", '#', ItemMaker.tutorialItem});
	    GameRegistry.addSmelting(Items.diamond, new ItemStack(ItemMaker.tutorialItem), 1.0F);
	}

}
