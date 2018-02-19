package programgames.MultimodPlus.client;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderInventoryMaker {

    public static int tesrRenderId;
    
	 public static final void init() {
		 	tesrRenderId = RenderingRegistry.getNextAvailableRenderId();
		 	RenderingRegistry.registerBlockHandler(new TESRInventoryRenderer());
	 }
}
