package programgames.MultimodPlus.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import programgames.MultimodPlus.Main;
import programgames.MultimodPlus.block.BasicBlock;
import programgames.MultimodPlus.block.CustomStair;
import programgames.MultimodPlus.block.FirstOre;
import programgames.MultimodPlus.block.ItemBlockMetaBlock;
import programgames.MultimodPlus.block.MultitexturedBlock;
import programgames.MultimodPlus.block.SimpleBlock;
import programgames.MultimodPlus.item.ItemMaker;

public class TileEntityMaker {

	
	public TileEntityTutoriel tileEntityTutoriel1;
	public static final void init() {
	     GameRegistry.registerTileEntity(TileEntityTutoriel.class,Main.MODID + ":tile1");

    }

}
