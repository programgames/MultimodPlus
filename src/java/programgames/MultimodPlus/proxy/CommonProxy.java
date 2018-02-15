package programgames.MultimodPlus.proxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import programgames.MultimodPlus.block.FirstBlock;
import programgames.MultimodPlus.crafting.CraftingMaker;
import programgames.MultimodPlus.item.FirstItem;

public class CommonProxy {

	 public void preInit(FMLPreInitializationEvent e) {
		 	
		 FirstItem.init();
		 FirstBlock.init();
		CraftingMaker.make();
	    }

	    public void init(FMLInitializationEvent e) {

	    }

	    public void postInit(FMLPostInitializationEvent e) {

	    }
}
