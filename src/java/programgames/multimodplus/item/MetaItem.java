package programgames.multimodplus.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import programgames.multimodplus.Main;


public class MetaItem extends Item {

	public IIcon[] icons = new IIcon[6];
	
    public MetaItem(String unlocalizedName) {
            super();
            this.setHasSubtypes(true);//not stack item with same metadata
            this.setUnlocalizedName(unlocalizedName);
            this.setCreativeTab(CreativeTabs.tabMaterials);
    }
    
    @Override
    public void registerIcons(IIconRegister reg) {
        for (int i = 0; i < 6; i ++) {
            this.icons[i] = reg.registerIcon(Main.MODID + ":multitexture" + i);
        }
    }
    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta > 5)
            meta = 0;

        return this.icons[meta];
    }

    @SuppressWarnings("unchecked")
	@Override
    public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List list) {
        for (int i = 0; i < 6; i ++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName() + stack.getItemDamage();
    }
}