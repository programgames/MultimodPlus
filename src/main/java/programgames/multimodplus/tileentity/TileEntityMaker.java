package programgames.multimodplus.tileentity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import programgames.multimodplus.Main;
import programgames.multimodplus.client.TileEntityChestSpecialRenderer;

// TODO: Auto-generated Javadoc
/**
 * The Class TileEntityMaker register all the entities.
 */
public class TileEntityMaker {


 
  public TileEntityCounter tileEntityTutoriel1;

  /**
   * Inits the maker.
   */
  public static final void init() {

    GameRegistry.registerTileEntity(TileEntityCounter.class,Main.MODID + ":tile1");

    GameRegistry.registerTileEntity(TileEntityChest.class,Main.MODID + ":tileVoid");
    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChest.class,
        new TileEntityChestSpecialRenderer());
    
    GameRegistry.registerTileEntity(TileEntityOverPoweredEnergieCube.class,Main.MODID + ":tilecube");
    GameRegistry.registerTileEntity(TileEntityDetector.class,Main.MODID + ":tiledetector");




  }

}
