package terrails.terracore.helper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHelper {

    public static void fill(int radius, BlockPos pos, World world, IBlockState block, boolean wall, boolean currentPos) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos blockPos = pos.add(x, 0, z);

                if (!blockPos.equals(pos) || currentPos) {
                    if (wall) {
                        if (Math.abs(x) == radius || Math.abs(z) == radius) {
                            world.setBlockState(blockPos, block);
                        }
                    } else if (!blockPos.equals(pos) || currentPos) {
                        world.setBlockState(blockPos, block);
                    }
                }
            }
        }
    }

    public static boolean check(int radius, BlockPos pos, IBlockAccess world, IBlockState block, boolean wall, boolean currentPos) {
        boolean value = false;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos blockPos = pos.add(x, 0, z);

                if (!blockPos.equals(pos) || currentPos) {
                    if (wall) {
                        if (Math.abs(x) == radius || Math.abs(z) == radius) {
                            if (world.getBlockState(blockPos).getBlock().equals(block.getBlock())) {
                                value = true;
                            } else return false;
                        }
                    } else {
                        if (world.getBlockState(blockPos).getBlock().equals(block.getBlock())) {
                            value = true;
                        } else return false;
                    }
                }
            }
        }
        return value;
    }
    public static boolean checkAny(int radius, BlockPos pos, IBlockAccess world, IBlockState block, boolean wall, boolean currentPos) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos blockPos = pos.add(x, 0, z);

                if (!blockPos.equals(pos) || currentPos) {
                    if (wall) {
                        if (Math.abs(x) == radius || Math.abs(z) == radius) {
                            if (world.getBlockState(blockPos).getBlock().equals(block.getBlock())) {
                                return true;
                            }
                        }
                    } else {
                        if (world.getBlockState(blockPos).getBlock().equals(block.getBlock())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }





    private static void fill5x5(IBlockState block, World world, BlockPos pos) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                final BlockPos blockPos = pos.add(x, 0, z);

                if (x == 0 && z == 0) {
                    world.setBlockState(blockPos, block);
                } else if (Math.abs(x) == 2 || Math.abs(z) == 2) {
                    world.setBlockState(blockPos, block);
                } else {
                    world.setBlockState(blockPos, block);
                }
            }
        }
    }
    private static void fill6x6Wall(IBlockState block, World world, BlockPos pos) {
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                final BlockPos blockPos = pos.add(x, 0, z);

                if (Math.abs(x) == 3 || Math.abs(z) == 3) {
                    world.setBlockState(blockPos, block);
                } else if (Math.abs(x) == -3 || Math.abs(z) == -3) {
                    world.setBlockState(blockPos, block);
                }
            }
        }
    }
    private static boolean check3x3(IBlockState block, World world, BlockPos blockPos) {
        final BlockPos position = new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        if (!(world.getBlockState(position) == block))
            return true;

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {

                final BlockPos pos = position.add(x, 0, z);
                final IBlockState blockState = world.getBlockState(pos);

                if (Math.abs(x) == 1 || Math.abs(z) == 1) { // Outer Layer of 5x5 Area
                    if (blockState != block) return false;
                }
                if (x == 0 && z == 0) { // Inner 1x1 Area
                    if (blockState != block) return false;
                } else { // Inner Layer of 5x5 Area
                    if (blockState != block) return false;
                }
            }
        }
        return true;
    }
    private static boolean check5x5(IBlockState block, World world, BlockPos blockPos) {
        final BlockPos position = new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());

        if (!(world.getBlockState(position) == block))
            return false;

        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {

                final BlockPos pos = position.add(x, 0, z);
                final IBlockState blockState = world.getBlockState(pos);
                int booleanMethod = 0;

                if (Math.abs(x) == 2 || Math.abs(z) == 2) { // Outer Layer of 5x5 Area
                    if (blockState != block) //return false;
                        booleanMethod =+ 1;
                } else if (x == 0 && z == 0) {
                    if (blockState != block) //return false;
                        booleanMethod =+ 1;
                } else { // Inner Layer of 5x5 Area
                    if (blockState != block) //return false;
                        booleanMethod =+ 1;
                }
                if (booleanMethod == 25) {
                    return false;
                }

            }
        }
        return true;
    }
    private static boolean check6x6Wall(IBlockState block, World world, BlockPos blockPos) {
        final BlockPos position = new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());

        if (world.getBlockState(position) == block)
            return true;

        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {

                final BlockPos pos = position.add(x, 0, z);
                final IBlockState blockState = world.getBlockState(pos);

                if (Math.abs(x) == 3 || Math.abs(z) == 3) {
                    if (blockState == block) return true;
                } else if (Math.abs(x) == -3 || Math.abs(z) == -3) {
                    if (blockState == block) return true;
                }
            }
        }
        return false;
    }
}
