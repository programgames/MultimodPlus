package programgames.MultimodPlus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import programgames.MultimodPlus.Main;
public class BasicBlock extends Block {

	protected BasicBlock(String unlocalizedName, Material material) {
		super(material);
		// TODO Auto-generated constructor stub
		this.setBlockName(unlocalizedName);
		this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabBlock);
	    this.setHardness(2.0F);
	    this.setResistance(6.0F);
	    this.setLightLevel(1.0F);
	    this.setHarvestLevel("pickaxe", 3);
	    this.setStepSound(soundTypeMetal);
	}

}
