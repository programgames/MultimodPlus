package programgames.MultimodPlus.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import programgames.MultimodPlus.item.ItemMaker;

public class BlockMaker {

	 public static Block tutorialBlock;
	 public static Block multitexture;
	 public static Block firstore;
	 
	    public static final void init() {
	    	GameRegistry.registerBlock(tutorialBlock = new BasicBlock("texture", Material.iron), ItemBlockMetaBlock.class,"tutorialBlock");
	    	GameRegistry.registerBlock(multitexture = new MultitexturedBlock("multitexture", Material.cloth), "multitexture");
	    	GameRegistry.registerBlock(firstore = new FirstOre("chocolate", Material.rock, ItemMaker.chocolate, 2, 0,1), "tutorial_ore");

	    }
}
