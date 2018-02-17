package programgames.MultimodPlus.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import programgames.MultimodPlus.Main;

public class FirstFood extends ItemFood {

	@SuppressWarnings("unused")
	private PotionEffect[] effects;

	FirstFood(String unlocalizedName, int healAmount, float saturationModifier, boolean isWolfFood,PotionEffect... effects)
	{
		super(healAmount,saturationModifier,isWolfFood);
		this.setUnlocalizedName(unlocalizedName);
	    this.setTextureName(Main.MODID + ":" + unlocalizedName);
	    this.setCreativeTab(CreativeTabs.tabFood);
	    this.effects = effects;
	}
	//Function to applly the effect after eaten
	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
	    super.onFoodEaten(stack, world, player);
	        
	    
	    /**
	     * This is a loop which will iterate over every PotionEffect in the list we set, check whether the effect is valid (green statements)
	     *  and apply it to the player who had eaten the food. To do this, the PotionEffect in the array is cloned with the blue statement. 
	     *  This is necessary, because if you applied a potion effect from the array multiple times (because you eat the food again) it would
	     *   not work, because the Effect in the array is already used up (Its duration is zero). Thats why we need to clone the PotionEffect.
	     *   You only apply the PotionEffect on Server side, so if we are on Client side the red statement is false and nothing happens.
	     */
	    for (int i = 0; i < effects.length; i ++) {
	        if (!world.isRemote && effects[i] != null && effects[i].getPotionID() > 0)
	            player.addPotionEffect(new PotionEffect(this.effects[i].getPotionID(), this.effects[i].getDuration(), this.effects[i].getAmplifier(), this.effects[i].getIsAmbient()));
	    }
	}

}
