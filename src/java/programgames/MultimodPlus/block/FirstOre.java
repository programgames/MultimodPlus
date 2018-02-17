package programgames.MultimodPlus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import programgames.MultimodPlus.Main;

public class FirstOre extends Block {


	private Item drop;
	private int meta;
	private int least_quantity;
	private int most_quantity;

	protected FirstOre(String unlocalizedName, Material mat, Item drop, int meta, int least_quantity, int most_quantity) {
		super(mat);
		this.drop = drop;
		this.meta = meta;
		this.least_quantity = least_quantity;
		this.most_quantity = most_quantity;

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

	   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	    {
	        float f = 0.0625F;
	        return AxisAlignedBB.getBoundingBox((float)i + f, j, (float)k + f, (float)(i + 1) - f, (float)(j + 1) - f, (float)(k + 1) - f);
	    }

	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
	{
	    entity.setFire(10);
	}
	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		if (this.least_quantity >= this.most_quantity)
			return this.least_quantity;
		return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);
	}
}
