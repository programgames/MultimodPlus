package programgames.multimodplus.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class CustomStair  extends BlockStairs {

	
	 public CustomStair(Block sourceBlock,int metadata,String name){
			super(sourceBlock,metadata);
			this.setBlockName(name);
				this.useNeighborBrightness = true;
			setHardness(0.75F);
			setResistance(0.75F);
			setStepSound(sourceBlock.stepSound);
		}
	

}
