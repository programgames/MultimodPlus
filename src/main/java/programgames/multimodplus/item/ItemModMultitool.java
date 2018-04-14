package programgames.multimodplus.item;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.HashSet;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import programgames.multimodplus.Main;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemModMultitool.
 */
public class ItemModMultitool extends ItemPickaxe {

  /** The effective against. */
  private static HashSet<Block> effectiveAgainst = Sets.newHashSet(new Block[] {
      Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, 
      Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, 
      Blocks.soul_sand, Blocks.mycelium});

  /**
   * Instancie un nouveau item mod multitool.
   *
   * @param unlocalizedName the unlocalized name
   * @param material the material
   */
  protected ItemModMultitool(String unlocalizedName, ToolMaterial material) {
    super(material);
    this.setUnlocalizedName(unlocalizedName);
    this.setTextureName(Main.MODID + ":" + unlocalizedName);
  }

  /* (non-Javadoc)
   * @see net.minecraft.item.ItemTool#getToolClasses(net.minecraft.item.ItemStack)
   */
  @Override
  public Set<String> getToolClasses(ItemStack stack) {
    
    return ImmutableSet.of("pickaxe", "shovel");
  }
  
  /* (non-Javadoc)
   * @see net.minecraft.item.ItemPickaxe#func_150897_b(net.minecraft.block.Block)
   */
  @Override
  public boolean func_150897_b(Block block) {
    
    return effectiveAgainst.contains(block) ? true : super.func_150897_b(block);
  }
  
  /* (non-Javadoc)
   * @see net.minecraft.item.ItemPickaxe
   * #func_150893_a(net.minecraft.item.ItemStack, net.minecraft.block.Block)
   */
  @Override
  public float func_150893_a(ItemStack stack, Block block) {
    
    return effectiveAgainst.contains(block)
        ? this.efficiencyOnProperMaterial : super.func_150893_a(stack, block);
  }
}