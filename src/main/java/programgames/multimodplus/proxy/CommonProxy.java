package programgames.multimodplus.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import programgames.multimodplus.block.BlockMaker;
import programgames.multimodplus.block.TileEntityBindingMaker;
import programgames.multimodplus.client.RenderDetector;
import programgames.multimodplus.client.RenderInventoryMaker;
import programgames.multimodplus.common.EventHandler;
import programgames.multimodplus.crafting.CraftingMaker;
import programgames.multimodplus.gui.GuiMaker;
import programgames.multimodplus.item.ItemMaker;
import programgames.multimodplus.tileentity.TileEntityMaker;
import programgames.multimodplus.world.Worldgen;

/**
 * Client proxy.
 *
 * @author programgames
 * @version 2.0
 */
public class CommonProxy {

  public static int renderDetectorId;


  /**
   * Pre-init initialisation.
   *
   * @param event de forge de pre initialisation
   */
  public void preInit(FMLPreInitializationEvent event) {


    ItemMaker.init();
    BlockMaker.init();
    GameRegistry.registerWorldGenerator(new Worldgen(), 0);
    TileEntityMaker.init(event);

    renderDetectorId = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler(renderDetectorId, new RenderDetector());


    if (event.getSide().isClient()) {
      MinecraftForge.EVENT_BUS.register(new EventHandler());
      RenderInventoryMaker.init();
      GuiMaker.init();
      TileEntityBindingMaker.init();
    }

  }

  /**
   * Init proxy.
   *
   * @param e init event.
   */
  public void init(FMLInitializationEvent e) {

    CraftingMaker.make();

  }

  /**
   * post init proxy.
   *
   * @param e de post initialisation de forge
   */

  public void postInit(FMLPostInitializationEvent e) {

  }
}
