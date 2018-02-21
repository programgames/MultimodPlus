
package programgames.multimodplus.gui;

import cpw.mods.fml.common.network.NetworkRegistry;
import programgames.multimodplus.Main;

 
public class GuiMaker {
	
    public static final void init() {

        NetworkRegistry.INSTANCE.registerGuiHandler(Main.MODID, new GuiHandlerChest());
    }

}
