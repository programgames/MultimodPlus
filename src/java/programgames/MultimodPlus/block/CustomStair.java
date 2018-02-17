package programgames.MultimodPlus.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class CustomStair  extends BlockStairs {

	
	 public CustomStair(Block sourceBlock,String name){
			super(i,"test");
				this.useNeighborBrightness = true;
			setHardness(0.75F);
			setResistance(0.75F);
			setStepSound(sourceBlock.stepSound);
		}
	

}
