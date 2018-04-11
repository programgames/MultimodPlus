package programgames.multimodplus.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import programgames.multimodplus.item.ItemMaker;

/**
 * The BlockMaker class register all the blocks of the mods.
 * @author programgames
 *
 */
public class BlockMaker {


  public static Block multitexture;
  public static Block firstore;
  public static BlockStairs stair;
  public static TeleporterBlock simpleBlock;
  public static Block block16;
  public static Block cube;
  public static Block transporter;
  public static Block alarm;

  /**
   * Function called in the proxy.
   */
  public static final void init() {
    GameRegistry.registerBlock(multitexture = new ChestBlock("multitexture", Material.cloth),
        "multitexture");
    
    GameRegistry.registerBlock(firstore = new FirstOre("firstOre", Material.rock,
        ItemMaker.chocolate, 2, 0, 1),
        "firstOre");
    
    GameRegistry.registerBlock(stair = new CustomStair(firstore, 0, "OreStair"),
        "OreStair");
    
    GameRegistry.registerBlock(simpleBlock = new TeleporterBlock("simpleBlock", Material.sand),
        "simpleBlock");
    
    GameRegistry.registerBlock(block16 = new CounterBlock("block16", Material.rock),
        "block16");
    GameRegistry.registerBlock(cube = new OverPoweredEnergieCube("OPCUBE", Material.rock),"cube");

    GameRegistry.registerBlock(cube = new UltimateEnergyTransporter("Energy Transporter", Material.rock),"Energy Transporter");

    GameRegistry.registerBlock(alarm = new AlarmBlock("Alarm", Material.rock),"Alarm");
  }
}
