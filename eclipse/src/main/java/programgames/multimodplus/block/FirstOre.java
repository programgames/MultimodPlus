package programgames.multimodplus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import programgames.multimodplus.Main;
/**
 * This class reprensent the oreChocolate.
 * @author programgames
 *
 */

public class FirstOre extends Block {


  private Item drop;
  private int meta;
  private int leastQuantity;
  private int mostQuantity;

  protected FirstOre(String unlocalizedName, Material mat, Item drop, int meta, int leastQuantity, 
      int mostQuantity) {
    super(mat);
    this.drop = drop;
    this.meta = meta;
    this.leastQuantity = leastQuantity;
    this.mostQuantity = mostQuantity;

    this.setBlockName(unlocalizedName);
    this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
    this.setCreativeTab(CreativeTabs.tabBlock);
    this.setStepSound(soundTypeStone);
    this.setHardness(10.0f);
    this.setResistance(20.0f);
    this.setHarvestLevel("pickaxe", 2);
    //this.setTickRandomly(true);
  }
  
  @Override
  public Item getItemDropped(int meta, Random random, int fortune) {
    return this.drop;
  }
  //multiple drops needed ? https://web.archive.org/web/20161126050512/http://bedrockminer.jimdo.com:80/modding-tutorials/basic-modding-1-7/blocks-dropping-special-items/

  @Override
  public int damageDropped(int metadata) {
    return this.meta;
  }

  /**
   * define the hitbox.
   */
  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
    float f = 0.0625F;
    return AxisAlignedBB.getBoundingBox((float)i + f, j, (float)k + f, (float)(i + 1) - f,
        (float)(j + 1) - f, (float)(k + 1) - f);
  }

  /**
   * Define what is happening when the entity collide with the block.
   */
  public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
    entity.setFire(10);
  }
  
  /**
   * Define how much item are dropped when destroyed.
   */
  @Override
  public int quantityDropped(int meta, int fortune, Random random) {
    if (this.leastQuantity >= this.mostQuantity) {
      return this.leastQuantity;
    }
    return this.leastQuantity
        + random.nextInt(this.mostQuantity - this.leastQuantity + fortune + 1);
  }
}
