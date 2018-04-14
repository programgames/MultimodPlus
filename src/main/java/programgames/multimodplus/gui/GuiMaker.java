
package programgames.multimodplus.gui;

import cpw.mods.fml.common.network.NetworkRegistry;
import programgames.multimodplus.Main;


// TODO: Auto-generated Javadoc
/**
 * The Class GuiMaker is used to register the guiHandler.
 */
public class GuiMaker {

  /**
   * Inits the guiMaker.
   */
  public static final void init() {

    NetworkRegistry.INSTANCE.registerGuiHandler(Main.MODID, new GuiHandler());
  }

}
