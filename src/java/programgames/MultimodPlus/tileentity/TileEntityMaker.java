package programgames.MultimodPlus.tileentity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import programgames.MultimodPlus.Main;
import programgames.MultimodPlus.client.TileEntityTutorielSpecialRenderer;

public class TileEntityMaker {


	public TileEntityTutoriel tileEntityTutoriel1;
	public static final void init() {
		
		GameRegistry.registerTileEntity(TileEntityTutoriel.class,Main.MODID + ":tile1");
		GameRegistry.registerTileEntity(TileEntityVoid.class,Main.MODID + ":tileVoid");

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVoid.class, new TileEntityTutorielSpecialRenderer());


	}

}
