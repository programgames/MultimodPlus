package programgames.multimodplus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import programgames.multimodplus.Main;

public class TeleporterBlock extends Block {

  /**
   * Instancie un nouveau teleporter block.
   *
   * @param unlocalizedName the unlocalized name
   * @param material the material
   */
  protected  TeleporterBlock(String unlocalizedName, Material material) {
    super(material);

    this.setBlockName(unlocalizedName);
    this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
    this.setCreativeTab(CreativeTabs.tabBlock);
    this.setHardness(2.0F);
    this.setResistance(6.0F);
    this.setLightLevel(1.0F);
    this.setHarvestLevel("pickaxe", 3);
    this.setStepSound(soundTypeMetal);
  }

  /**
   * Define the hitbox. 
   */
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
    
    float f = 0.0625F;
    return AxisAlignedBB.getBoundingBox((float)i + f, j, (float)k + f, (float)(i + 1) - f,
        (float)(j + 1) - f, (float)(k + 1) - f);
  }

  /**
   * Define what is happening when an enntity collide with the block.
   */
  public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
    
    if (entity.isEating()) {
      entity.travelToDimension(1);        
    }
  }
}
