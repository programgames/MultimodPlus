package programgames.multimodplus.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import programgames.multimodplus.block.BlockMaker;
import programgames.multimodplus.item.ItemMaker;

public class CraftingMaker {

	public static void make()
	{
	    GameRegistry.addRecipe(new ItemStack(BlockMaker.tutorialBlock), new Object[] {"##", "##", '#', ItemMaker.tutorialItem});
	    GameRegistry.addSmelting(Items.diamond, new ItemStack(ItemMaker.tutorialItem), 1.0F);
	    
	    //register a craft using metadata of an item ex : specif dye
	    //GameRegistry.addShapelessRecipe(new ItemStack(Blocks.coal_block), new Object[]{new ItemStack(Items.dye, 1, 0)});
	}

}
