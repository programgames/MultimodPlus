package programgames.MultimodPlus.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import programgames.MultimodPlus.item.ItemMaker;

public class BlockMaker {

	 public static Block tutorialBlock;
	 public static Block multitexture;
	 public static Block firstore;
	 public static BlockStairs stair;
	 public static TeleporterBlock simpleBlock;
	 public static Block block16;
	    public static final void init() {
	    	GameRegistry.registerBlock(multitexture = new ChestBlock("multitexture", Material.cloth), "multitexture");
	    	GameRegistry.registerBlock(firstore = new FirstOre("firstOre", Material.rock, ItemMaker.chocolate, 2, 0,1), "firstOre");
	    	GameRegistry.registerBlock(stair =  new CustomStair(firstore,0,"OreStair"),"OreStair");
	    	GameRegistry.registerBlock(simpleBlock = new TeleporterBlock("simpleBlock",Material.sand),"simpleBlock");
	    	GameRegistry.registerBlock(block16 = new CounterBlock("block16",Material.rock),"block16");

	    }
}
