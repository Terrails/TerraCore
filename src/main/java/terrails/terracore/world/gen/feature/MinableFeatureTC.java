package terrails.terracore.world.gen.feature;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class MinableFeatureTC extends MinableFeature {

    @Override
    public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> chunkGenerator, Random random, BlockPos pos, MinableConfig config) {
        if (config.size < 4) {
            return generateSmallVein(world, random, pos, config);
        } else {
            return super.place(world, chunkGenerator, random, pos, config);
        }
    }

    private boolean generateSmallVein(IWorld world, Random random, BlockPos pos, MinableConfig config) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        boolean value = false;
        int newX = x + 8;
        int newZ = z + 8;
        IBlockState blockState = world.getBlockState(new BlockPos(newX, y, newZ));
        if (blockState.getBlock().isReplaceableOreGen(blockState, world.getWorld(), new BlockPos(x, y, z), config.canReplace)) {
            for (int i = 0; i < config.size; i++) {
                int posX = newX + random.nextInt(2);
                int posY = y + random.nextInt(2);
                int posZ = newZ + random.nextInt(2);
                world.setBlockState(new BlockPos(posX, posY, posZ), config.state, 2);
                value = true;
            }
        }
        return value;
    }
}
