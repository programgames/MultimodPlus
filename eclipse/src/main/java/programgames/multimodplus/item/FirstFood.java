package programgames.multimodplus.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import programgames.multimodplus.Main;

// TODO: Auto-generated Javadoc
/**
 * The Class FirstFood reprensent the chocolate.
 */

public class FirstFood extends ItemFood {


  /** The effects. */
  private PotionEffect[] effects;

  /**
   * Instancie un nouveau first food.
   *
   * @param unlocalizedName the unlocalized name
   * @param healAmount the heal amount
   * @param saturationModifier the saturation modifier
   * @param isWolfFood the is wolf food
   * @param effects the effects
   */
  FirstFood(String unlocalizedName, int healAmount, float saturationModifier, boolean isWolfFood,
      PotionEffect... effects) {
    super(healAmount,saturationModifier,isWolfFood);
    this.setUnlocalizedName(unlocalizedName);
    this.setTextureName(Main.MODID + ":" + unlocalizedName);
    this.setCreativeTab(CreativeTabs.tabFood);
    this.effects = effects;
  }

  /* (non-Javadoc)
   * @see net.minecraft.item.ItemFood#
   * onFoodEaten(net.minecraft.item.ItemStack, net.minecraft.world.World,
   * net.minecraft.entity.player.EntityPlayer)
   */
  @Override
  protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
    super.onFoodEaten(stack, world, player);


    /**
     * This is a loop which will iterate over every PotionEffect in the list we set, check whether
     *  the effect is valid (green statements)
     *  and apply it to the player who had eaten the food. To do this, the PotionEffect in the
     *  array is cloned with the blue statement. 
     *  This is necessary, because if you applied a potion effect from the array multiple times
     *  (because you eat the food again) it would
     *   not work, because the Effect in the array is already used up (Its duration is zero).
     *   Thats why we need to clone the PotionEffect.
     *   You only apply the PotionEffect on Server side, so if we are on Client side the red
     *   statement is false and nothing happens.
     */
    for (int i = 0; i < effects.length; i++) {
      
      if (!world.isRemote && effects[i] != null && effects[i].getPotionID() > 0) {
        player.addPotionEffect(new PotionEffect(this.effects[i].getPotionID(),
            this.effects[i].getDuration(), this.effects[i].getAmplifier(),
            this.effects[i].getIsAmbient()));
      }
    }
  }

}
