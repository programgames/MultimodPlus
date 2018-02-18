package programgames.MultimodPlus.proxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import programgames.MultimodPlus.block.BlockMaker;
import programgames.MultimodPlus.crafting.CraftingMaker;
import programgames.MultimodPlus.item.ItemMaker;
import programgames.MultimodPlus.tileentity.TileEntityMaker;
import programgames.MultimodPlus.world.Worldgen;


public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {

		ItemMaker.init();
		BlockMaker.init();
		CraftingMaker.make();
		GameRegistry.registerWorldGenerator(new Worldgen(), 0);
		TileEntityMaker.init();

	}

	public void init(FMLInitializationEvent e) {

	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}
