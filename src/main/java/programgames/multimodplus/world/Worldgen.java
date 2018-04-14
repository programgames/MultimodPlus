package programgames.multimodplus.world;

import cpw.mods.fml.common.IWorldGenerator;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import programgames.multimodplus.block.BlockMaker;


/**
 * Cette classe gere la generation des mineraix.
 * @author programgames
 *
 */
public class Worldgen implements IWorldGenerator {

  /**
   * Generateur de l'ore chocolat.
   */
  private WorldGenerator genOreChocolate;

  /**
   * Fonction qui initialise les generateurs.
   * @see Worldgen
   */
  public Worldgen() {
    this.genOreChocolate = new WorldGenMinable(BlockMaker.firstore,8);//8 = spawnSize;

  }

  /**
   * The generate(…) method is called for every dimension. But you do not want to run the same
   * generator in every dimension, so we create a switch statement with a case for
   * every dimension ID: 
   */
  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world,
      IChunkProvider chunkGenerator,IChunkProvider chunkProvider) {

    switch (world.provider.dimensionId) {
      case 0: //Overworld
        this.runGenerator(this.genOreChocolate, world, random, chunkX, chunkZ, 20, 0, 64);
        break;
      case -1: //Nether

        break;
      case 1: //End
      
        break;
      default:
        break;
    }

  }

  /**This method takes all the position and calculates a random height with them
   * (It is necessary to add 1 to the heightDiff because of the way the nextInt method works).
   *Additionally, a random position inside of the chunk is calculated (The variables chunkX
   *and chunkZ are multiplied by 16 because they define the chunk's coordinates, not the ones of
   *the blocks). Finally, the generator is called at the special position.
   *The whole process is repeated as often as the argument chancesToSpawn defines.
   */

  private void runGenerator(WorldGenerator generator, World world, Random rand, int chunkX,
      int chunkZ, int chancesToSpawn, int minHeight, int maxHeight) {
    
    if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) {
      throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
    }
    int heightDiff = maxHeight - minHeight + 1;
    
    for (int i = 0; i < chancesToSpawn; i++) {
      int x = chunkX * 16 + rand.nextInt(16);
      int y = minHeight + rand.nextInt(heightDiff);
      int z = chunkZ * 16 + rand.nextInt(16);
      generator.generate(world, rand,x, y, z);

    }
  }

}
