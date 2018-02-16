package programgames.MultimodPlus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import programgames.MultimodPlus.Main;

public class MultitexturedBlock extends Block {

	public IIcon[] icons = new IIcon[6];
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		
	
		 for (int i = 0; i < 6; i ++) {
		        this.icons[i] = reg.registerIcon(this.textureName + i);
		    }
	
		 }

	protected MultitexturedBlock(String unlocalizedName, Material material) 
    { 
		
        super(material);
       
        this.setBlockName(unlocalizedName);
        this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
    
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(2.0F);
        this.setResistance(6.0F);
        this.setStepSound(soundTypeGravel);
    }

	@Override
	public IIcon getIcon(int side, int meta) {
		
	    return this.icons[side];
	}
}
