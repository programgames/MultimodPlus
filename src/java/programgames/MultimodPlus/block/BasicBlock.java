package programgames.MultimodPlus.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import programgames.MultimodPlus.Main;
import programgames.MultimodPlus.proxy.CommonProxy;
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
	
	@Override
    public void registerBlockIcons(IIconRegister reg) { }

	@Override
	public IIcon getIcon(int side, int meta) {
	    if (meta > 5)
	        meta = 0;

	    return BlockMaker.multitexture.getIcon(meta, 0);
	}
	@Override
	public int damageDropped(int meta) {
	    return meta;
	}
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 6; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
}
