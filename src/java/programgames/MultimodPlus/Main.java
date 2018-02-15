package programgames.MultimodPlus;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import programgames.MultimodPlus.proxy.*;


@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION)


public class Main {

    public static final String MODID = "multimodplus";
    public static final String MODNAME = "MultimodPlus";
    public static final String VERSION = "1.0.0";
        
    @Instance
    public static Main instance = new Main();
    @SidedProxy(clientSide="programgames.MultimodPlus.proxy.CommonProxy", serverSide="programgames.MultimodPlus.proxy.ServerProxy")
    public static CommonProxy proxy;

     
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
         /**The preInit Handler is called at the very beginning of the startup routine.
        In this method we should read your config file, create Blocks, Items, etc. and register
        them with the GameRegistry.
           **/
    	
    	proxy.preInit(e);
    	
    	
    	
    	
    }
        
    @EventHandler
    public void init(FMLInitializationEvent e) {
                /** The init Handler is called after the preInit Handler. In this method we can build up
                 *   data structures, add Crafting Recipes and register new handler.**/
    	
    	proxy.init(e);
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    /**	The postInit Handler is called at the very end. Its used to communicate with other mods and
     adjust your setup based on this.
     **/
    	
    	  proxy.postInit(e);
    }
}