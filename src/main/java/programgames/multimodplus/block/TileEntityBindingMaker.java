package programgames.multimodplus.block;


import cpw.mods.fml.client.registry.ClientRegistry;
import programgames.multimodplus.client.TileEntityChestSpecialRenderer;
import programgames.multimodplus.tileentity.TileEntityChest;

public class TileEntityBindingMaker {

  public static final void init()
  {
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChest.class,
            new TileEntityChestSpecialRenderer());

  }
}
