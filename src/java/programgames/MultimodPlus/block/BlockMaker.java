package programgames.MultimodPlus.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMaker {

	 public static Block tutorialBlock;
	 public static Block multitexture;
	 
	    public static final void init() {
	    	GameRegistry.registerBlock(tutorialBlock = new BasicBlock("texture", Material.iron), ItemBlockMetaBlock.class,"tutorialBlock");
	    	
	
	    	GameRegistry.registerBlock(multitexture = new MultitexturedBlock("multitexture", Material.cloth), "multitexture");
	    	
	    }
}
