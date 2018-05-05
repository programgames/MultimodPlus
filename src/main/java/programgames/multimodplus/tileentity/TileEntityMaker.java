package programgames.multimodplus.tileentity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import programgames.multimodplus.Main;

// TODO: Auto-generated Javadoc
/**
 * The Class TileEntityMaker register all the entities.
 */
public class TileEntityMaker {


 
  public TileEntityCounter tileEntityTutoriel1;

  /**
   * Inits the maker.
   */
  public static final void init(FMLPreInitializationEvent event) {

    GameRegistry.registerTileEntity(TileEntityCounter.class,Main.MODID + ":tile1");

    GameRegistry.registerTileEntity(TileEntityChest.class,Main.MODID + ":tileVoid");



    
    GameRegistry.registerTileEntity(TileEntityOverPoweredEnergieCube.class,Main.MODID + ":tilecube");
    GameRegistry.registerTileEntity(TileEntityDetector.class,Main.MODID + ":tiledetector");




  }

}
