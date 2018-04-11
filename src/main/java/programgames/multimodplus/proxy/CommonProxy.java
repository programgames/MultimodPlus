package programgames.multimodplus.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import programgames.multimodplus.block.BlockMaker;
import programgames.multimodplus.client.RenderInventoryMaker;
import programgames.multimodplus.crafting.CraftingMaker;
import programgames.multimodplus.gui.GuiMaker;
import programgames.multimodplus.item.ItemMaker;
import programgames.multimodplus.sounds.SoundMaker;
import programgames.multimodplus.tileentity.TileEntityMaker;
import programgames.multimodplus.world.Worldgen;

/**
 * Cette classe est le proxy client, qui gere les actiosn cote client.
 * @version 2.0
 * @author programgames
 */
public class CommonProxy {
  

  /**
   * Fonction de pre init du proxy.
   * 
   * @param e de forge de pre initialisation
   */
  public void preInit(FMLPreInitializationEvent e) {

    ItemMaker.init();
    BlockMaker.init();
    GameRegistry.registerWorldGenerator(new Worldgen(), 0);
    TileEntityMaker.init();
    RenderInventoryMaker.init();
    GuiMaker.init();
    SoundMaker.init();


  }

  /**
   * Fonction d'initialisation du proxy.
   * @param e event d'initialisation de forge.
   */
  public void init(FMLInitializationEvent e) {

    CraftingMaker.make();

  }
  /**
   * Fonction de post initialisation du proxy.
   * @param e de post initialisation de forge
   */
  
  public void postInit(FMLPostInitializationEvent e) {

  }
}
