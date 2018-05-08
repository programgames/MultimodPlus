package programgames.multimodplus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import programgames.multimodplus.Main;

/**
 * A simple alarm powered by redstone.
 */
class AlarmBlock extends Block {

  private static float hardness = 2.0F;
  private static float resistance = 6.0F;
  private static float lightLevel = 1.0F;

  /**
   * Create a new AlarmBlock.
   *
   * @param unlocalizedName the unlocalized name
   * @param material        the material
   */
  protected  AlarmBlock(final String unlocalizedName, final Material material) {
        super(material);

    this.setBlockName(unlocalizedName);
    this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
    this.setCreativeTab(CreativeTabs.tabBlock);
    this.setHardness(hardness);
    this.setResistance(resistance);
    this.setLightLevel(lightLevel);
    this.setHarvestLevel("pickaxe", 3);
    this.setStepSound(soundTypeMetal);
  }
}
