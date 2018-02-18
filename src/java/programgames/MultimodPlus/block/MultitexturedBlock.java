package programgames.MultimodPlus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import programgames.MultimodPlus.Main;
import scala.reflect.internal.Trees.CaseDef;

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
	public IIcon getIcon(int side, int metadata) {
		
		if(metadata == 1)
		{
			if(side == 4)
				return this.icons[3];
			if(side == 3 )
				return this.icons[5];
			if(side == 5)
				return this.icons[2];
			if(side == 2)
				return this.icons[4];
			if(side == 1 || side == 0)
				return this.icons[side];
		}
		if(metadata == 2)
		{
			if(side == 4)
				return this.icons[5];
			if(side == 3 )
				return this.icons[2];
			if(side == 5)
				return this.icons[4];
			if(side == 2)
				return this.icons[3];
			if(side == 1 || side == 0)
				return this.icons[side];
		}
		if(metadata == 3)
		{
			if(side == 4)
				return this.icons[5];
			if(side == 3 )
				return this.icons[4];
			if(side == 5)
				return this.icons[3];
			if(side == 2)
				return this.icons[5];
			if(side == 1 || side == 0)
				return this.icons[side];
		}
		return this.icons[side];

	}
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
	{
		int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, direction, 2);
	}
}
