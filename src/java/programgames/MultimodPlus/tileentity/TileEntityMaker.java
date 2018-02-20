package programgames.MultimodPlus.tileentity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import programgames.MultimodPlus.Main;
import programgames.MultimodPlus.client.TileEntityChestSpecialRenderer;

public class TileEntityMaker {


	public TileEntityCounter tileEntityTutoriel1;
	
	public static final void init() {
		
		GameRegistry.registerTileEntity(TileEntityCounter.class,Main.MODID + ":tile1");

		GameRegistry.registerTileEntity(TileEntityChest.class,Main.MODID + ":tileVoid");
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChest.class, new TileEntityChestSpecialRenderer());


	}

}
