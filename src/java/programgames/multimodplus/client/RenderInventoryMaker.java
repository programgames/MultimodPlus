package programgames.multimodplus.client;

import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * This class register all render for the enventoty.
 * @author programgames
 *
 */
public class RenderInventoryMaker {

  public static int tesrRenderId;

  /**
  * init of the class.
  */
  public static final void init() {
    tesrRenderId = RenderingRegistry.getNextAvailableRenderId();
    RenderingRegistry.registerBlockHandler(new TesrInventoryRenderer());
  }
}
