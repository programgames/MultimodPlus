package programgames.multimodplus.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

/**
 * The Class CustomStair is custom stair.
 * 
 * @author programgames
 */
public class CustomStair extends BlockStairs {

  /**
   * Instantiates a new custom stair.
   *
   * @param sourceBlock
   *          the source block
   * @param metadata
   *          the metadata
   * @param name
   *          the name
   */
  public CustomStair(Block sourceBlock, int metadata, String name) {
    super(sourceBlock, metadata);
    this.setBlockName(name);
    this.useNeighborBrightness = true;
    setHardness(0.75F);
    setResistance(0.75F);
    setStepSound(sourceBlock.stepSound);
  }

}
