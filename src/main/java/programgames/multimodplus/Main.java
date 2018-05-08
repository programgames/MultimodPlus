package programgames.multimodplus;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import programgames.multimodplus.proxy.CommonProxy;


/**
 * <b>Main est la classe principale du mod.</b>

 * @author programgames
 * @version 2.0
 */

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION,useMetadata = true)


public class Main {
  /**
   * <strong>Id du mod.</strong>
   */
  public static final String MODID = "multimodplus";
  /**
   * Nom du mod.
   */
  public static final String MODNAME = "MultimodPlus";
  /**
   * Version du mod.
   */
  public static final String VERSION = "2.0.0";


  /**
   * Instance du mod.Sert a acceder au mod id ...  a partir des autres classes.
   */
  @Instance("multimodplus")
  public static Main instance = new Main();
  /**
   * References des Proxy : action sur le client ou serveur.
   */
  @SidedProxy(clientSide = "programgames.multimodplus.proxy.CommonProxy",
          serverSide = "programgames.multimodplus.proxy.ServerProxy")
  public static CommonProxy proxy;

  /**
   * Pre Init is the place to let the game know about all the blocks, items,
   * etc that your mod provides.
   * This stage’s event is the  FMLPreInitializationEvent. Common actions to preform in preInit are:
   *Registering blocks and items to the GameRegistry
   *Registering tile entities
   *Registering entities
   *Assigning ore dictionary names
   *
   * @param e Evenement de pre-initialisation envoye par forge
   */
  @EventHandler
  public void preInit(FMLPreInitializationEvent e) {
    proxy.preInit(e);

  }

  /** The init Handler is called after the preInit Handler. In this method we can build up
   *   data structures, add Crafting Recipes and register new handler.
   * @param e Evenement d'initialisation envoyé par forge
   */
  @EventHandler
  public void init(FMLInitializationEvent e) {

    proxy.init(e);
  }

  /**
   * The postInit Handler is called at the very end. Its used to communicate with other mods and
   * adjust your setup based on this.
   * @param e evenement de post-initialisation de Forge
   */
  @EventHandler
  public void postInit(FMLPostInitializationEvent e) {
    proxy.postInit(e);
  }
}