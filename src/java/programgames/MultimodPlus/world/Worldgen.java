package programgames.MultimodPlus.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import programgames.MultimodPlus.block.BlockMaker;

/**
 * @author JULIEN
 *
 */
public class Worldgen implements IWorldGenerator {

	private WorldGenerator gen_tutorial_ore; //Generates Tutorial Ore (used in Overworld)

	public Worldgen() {
		this.gen_tutorial_ore = new WorldGenMinable(BlockMaker.firstore,8);//8 = spawnSize;
		
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,IChunkProvider chunkProvider) {

		switch (world.provider.dimensionId) {
		case 0: //Overworld
			this.runGenerator(this.gen_tutorial_ore, world, random, chunkX, chunkZ, 20, 0, 64);
			break;
		case -1: //Nether

			break;
		case 1: //End

			break;
		}

	}

//	This method takes all the position and calculates a random height with them (It is necessary to add 1 to the heightDiff because of the way the nextInt method works).
//	Additionally, a random position inside of the chunk is calculated (The variables chunk_X and chunk_Z are multiplied by 16 because they define the chunk's coordinates, not the ones of
//	the blocks). Finally, the generator is called at the special position.
//	The whole process is repeated as often as the argument chancesToSpawn defines.
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i ++) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			generator.generate(world, rand,x, y, z);

		}
	}

}
