package terrails.terracore.world.generator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WorldGenCustomMinable extends WorldGenerator {

    private final int veinSize;
    private final IBlockState oreToGenerate;
    private final BlockMatcher blockToReplace;

    public WorldGenCustomMinable(IBlockState oreToGenerate, int minVeinSize, int maxVeinSize, Block blockToReplace){
        this.oreToGenerate = oreToGenerate;
        this.veinSize = ThreadLocalRandom.current().nextInt(minVeinSize, maxVeinSize + 1);
        this.blockToReplace = BlockMatcher.forBlock(blockToReplace);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        if (veinSize < 4) {
            return generateSmallVein(world, rand, x, y, z);
        } else {
            WorldGenerator generate = new WorldGenMinable(oreToGenerate, veinSize, blockToReplace);
            return generate.generate(world, rand, pos);
        }
    }

    private boolean generateSmallVein(World world, Random random, int x, int y, int z) {
        boolean value = false;
        int newX = x + 8;
        int newZ = z + 8;
        IBlockState blockState = world.getBlockState(new BlockPos(newX, y, newZ));
        if (blockState.getBlock().isReplaceableOreGen(blockState, world, new BlockPos(x, y, z), blockToReplace)) {
            for (int i = 0; i < veinSize; i++) {
                int posX = newX + random.nextInt(2);
                int posY = y + random.nextInt(2);
                int posZ = newZ + random.nextInt(2);
                world.setBlockState(new BlockPos(posX, posY, posZ), oreToGenerate, 2);
                value = true;
            }
        }
        return value;
    }
}
