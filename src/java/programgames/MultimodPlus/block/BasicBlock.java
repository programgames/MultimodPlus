package programgames.MultimodPlus.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import programgames.MultimodPlus.Main;
public class BasicBlock extends BlockContainer implements ITileEntityProvider {

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
		this.isBlockContainer = true;
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
	@SuppressWarnings("unchecked")
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List list) {
		for (int i = 0; i < 6; i ++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new ModTileEntity();
    }

    
    //called -> cleanup when desttroyed
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int side) {
		
		super.breakBlock(world,x,y,z,block,side);
		world.removeTileEntity(x,y,z);
	
			}

	//send event to tile entity
	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventData)
	{ 
		// TODO Auto-generated method stub
		return super.onBlockEventReceived(world,x,y,z,eventId,eventData);
		 TileEntity tileentity = world.getTileEntity(x,y,z);
	        return tileentity == null ? false : tileentity.receiveClientEvent(eventId,eventData);
	}
	
	//overide getRenderType.  ?
    
}
