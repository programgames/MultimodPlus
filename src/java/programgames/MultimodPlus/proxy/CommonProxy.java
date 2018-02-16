package programgames.MultimodPlus.proxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import programgames.MultimodPlus.block.BlockMaker;
import programgames.MultimodPlus.crafting.CraftingMaker;
import programgames.MultimodPlus.item.FirstItem;
import programgames.MultimodPlus.item.ItemMaker;


public class CommonProxy {

	 public void preInit(FMLPreInitializationEvent e) {
		 	
		 ItemMaker.init();
		 BlockMaker.init();
		CraftingMaker.make();
		
	    }

	    public void init(FMLInitializationEvent e) {

	    }

	    public void postInit(FMLPostInitializationEvent e) {

	    }
}
