package programgames.multimodplus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import programgames.multimodplus.Main;

public class AlarmBlock extends Block {

    /**
     * Instancie un nouveau teleporter block.
     *
     * @param unlocalizedName the unlocalized name
     * @param material the material
     */
    protected  AlarmBlock(String unlocalizedName, Material material) {
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
}
