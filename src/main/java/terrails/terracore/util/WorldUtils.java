package terrails.terracore.util;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.capability.wrappers.BlockLiquidWrapper;

import java.util.*;

public class WorldUtils {

    public static boolean equalsState(IBlockState state1, IBlockState state2) {
        Block block1 = state1.getBlock();
        Block block2 = state2.getBlock();
        return (block1.getMetaFromState(state1) == block2.getMetaFromState(state2)) && (block1 == block2);
    }
    public static boolean isReplaceable(IBlockState state) {
        return state.getMaterial().isReplaceable() || isLiquid(state);
    }
    public static boolean isLiquid(IBlockState state) {
        return state instanceof BlockLiquid || state instanceof BlockLiquidWrapper || state instanceof IFluidBlock || state.getMaterial().isLiquid();
    }

    public static class Placement {

        public static class PlacementProperties {

            private Type type;
            private Kind kind;
            private DeleteBlocks deleteBlocks = DeleteBlocks.ALL;

            public PlacementProperties(Type type) {
                this.type = type;
            }

            public enum Type {
                CUBOID,
                SPHERE
            }
            public enum Kind {
                NORMAL,
                HOLLOW,
                WALL,
                FLAT
            }
            public enum DeleteBlocks {
                ALL,
                NONE
            }

            public Type getType() {
                return type;
            }
            public Kind getKind() {
                return kind;
            }
            public DeleteBlocks getDeleteBlocks() {
                return deleteBlocks;
            }

            public PlacementProperties setType(Type type) {
                this.type = type;
                return this;
            }
            public PlacementProperties setKind(Kind kind) {
                this.kind = kind;
                return this;
            }
            public PlacementProperties setBlockType(DeleteBlocks blockType) {
                this.deleteBlocks = blockType;
                return this;
            }
        }

        private final IBlockState blockState;
        private final BlockPos pos;
        private final World world;

        private final PlacementProperties.Type type;
        private final PlacementProperties.Kind kind;
        private final PlacementProperties.DeleteBlocks deleteBlocks;

        /**
         * Used to check a certain facing instead of a radius,
         * example: Using UP will make it go only up with radius
         * and anything under the pos will be ignored
         */
        private final EnumFacing facing;

        private final int radiusX, radiusY, radiusZ;

        public Placement(PlacementProperties properties, EnumFacing facing, World world, BlockPos pos, int radiusX, int radiusY, int radiusZ, IBlockState blockState) {
            this.blockState = blockState;
            this.type = properties.type;
            this.kind = properties.kind;
            this.deleteBlocks = properties.deleteBlocks;
            this.radiusX = radiusX;
            this.radiusY = radiusY;
            this.radiusZ = radiusZ;
            this.facing = facing;
            this.world = world;
            this.pos = pos;
        }
        public Placement(PlacementProperties properties, World world, BlockPos pos, int radiusX, int radiusY, int radiusZ, IBlockState blockState) {
            this(properties, null, world, pos, radiusX, radiusY, radiusZ, blockState);
        }
        public Placement(PlacementProperties properties, EnumFacing facing, World world, BlockPos pos, int radius, IBlockState blockState) {
            this(properties, facing, world, pos, radius, radius, radius, blockState);
        }
        public Placement(PlacementProperties properties, World world, BlockPos pos, int radius, IBlockState blockState) {
            this(properties, null, world, pos, radius, radius, radius, blockState);
        }

        public void build() {
            if (type == PlacementProperties.Type.CUBOID) {
                for (BlockPos pos : getPositions().getFirst()) {
                    world.setBlockState(pos, blockState);
                }
                for (BlockPos pos : getPositions().getSecond()) {
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            } else if (type == PlacementProperties.Type.SPHERE) {
                sphere(null);
            }
        }
        public void sphere(IBlockState innerState) {

            double radiusX = this.radiusX + .5;
            double radiusY = this.radiusY + .5;
            double radiusZ = this.radiusZ + .5;

            double nextXn = 0;
            forX: for (int x = 0; x <= Math.ceil(radiusX); x++) {
                final double xn = nextXn;
                nextXn = (x + 1) * (1 / radiusX);
                double nextYn = 0;
                forY: for (int y = 0; y <= Math.ceil(radiusY); y++) {
                    final double yn = nextYn;
                    nextYn = (y + 1) * (1 / radiusY);
                    double nextZn = 0;
                    for (int z = 0; z <= Math.ceil(radiusZ); z++) {
                        final double zn = nextZn;
                        nextZn = (z + 1) * (1 / radiusZ);

                        double distanceSq = MathUtils.squared(xn, yn, zn);
                        if (distanceSq > 1) {
                            if (z == 0) {
                                if (y == 0) {
                                    break forX;
                                }
                                break forY;
                            }
                            break;
                        }

                        IBlockState state = blockState;
                        if (MathUtils.squared(nextXn, yn, zn) <= 1 && MathUtils.squared(xn, nextYn, zn) <= 1 && MathUtils.squared(xn, yn, nextZn) <= 1) {
                            if (innerState != null && innerState.getMaterial() != Material.AIR) {
                                state = innerState;
                            } else {
                                if (deleteBlocks == PlacementProperties.DeleteBlocks.ALL) {
                                    state = Blocks.AIR.getDefaultState();
                                } else if (kind == PlacementProperties.Kind.HOLLOW) {
                                    state = null;
                                }
                            }
                        }

                        if (state != null) {
                            world.setBlockState(pos.add(x, y, z), state);
                            world.setBlockState(pos.add(-x, y, z), state);
                            world.setBlockState(pos.add(x, -y, z), state);
                            world.setBlockState(pos.add(x, y, -z), state);
                            world.setBlockState(pos.add(-x, -y, z), state);
                            world.setBlockState(pos.add(x, -y, -z), state);
                            world.setBlockState(pos.add(-x, y, -z), state);
                            world.setBlockState(pos.add(-x, -y, -z), state);
                        }
                    }
                }
            }
        }

        private Tuple<List<BlockPos>, List<BlockPos>> getPositions() {
            int radiusX = this.radiusX;
            int radiusZ = this.radiusZ;
            int radiusY = kind == PlacementProperties.Kind.FLAT ? 0 : this.radiusY;

            int radiusYs = facing == EnumFacing.UP ? 0 : radiusY;
            int radiusYe = facing == EnumFacing.DOWN ? 0 : radiusY;

            List<BlockPos> placeBlocks = Lists.newArrayList();
            List<BlockPos> outsideBlocks = Lists.newArrayList();
            for (int y = -radiusYs; y <= radiusYe; y++) {
                for (int x = -radiusX; x <= radiusX; x++) {
                    for (int z = -radiusZ; z <= radiusZ; z++) {
                        BlockPos pos = this.pos.add(x, y, z);

                        switch (kind) {
                            case HOLLOW: {
                                boolean a = Math.abs(x) == Math.abs(radiusX);
                                boolean b = y < 0 ? Math.abs(y) == Math.abs(radiusYs) : Math.abs(y) == Math.abs(radiusYe);
                                boolean c = Math.abs(z) == Math.abs(radiusZ);

                                if (a || b || c) {
                                    placeBlocks.add(pos);
                                } else if (deleteBlocks == PlacementProperties.DeleteBlocks.ALL) {
                                    outsideBlocks.add(pos);
                                }
                                break;
                            }
                            case WALL: {
                                boolean a = Math.abs(x) == Math.abs(radiusX);
                                boolean b = Math.abs(z) == Math.abs(radiusZ);

                                if (a || b) {
                                    placeBlocks.add(pos);
                                } else if (deleteBlocks == PlacementProperties.DeleteBlocks.ALL) {
                                    outsideBlocks.add(pos);
                                }
                                break;
                            }
                            case FLAT:
                                if (pos.getY() == this.pos.getY()) {
                                    placeBlocks.add(pos);
                                } else if (deleteBlocks == PlacementProperties.DeleteBlocks.ALL) {
                                    outsideBlocks.add(pos);
                                }
                                break;
                            case NORMAL:
                                placeBlocks.add(pos);
                                break;
                        }
                    }
                }
            }
            return new Tuple<>(placeBlocks, outsideBlocks);
        }
    }
    public static class Checker {

        public static class CheckerProperties {

            private Kind kind = Kind.NORMAL;
            private Check check = Check.ALL;
            private BlockType blockType = BlockType.ALL;

            public enum Kind {
                NORMAL,
                HOLLOW,
                WALL,
                FLAT
            }
            public enum Check {
                ALL,
                ANY
            }
            public enum BlockType {
                ALL,
                SOLID,
                AIR
            }

            public Kind getKind() {
                return kind;
            }
            public Check getCheck() {
                return check;
            }
            public BlockType getBlockType() {
                return blockType;
            }

            public CheckerProperties setKind(Kind kind) {
                this.kind = kind;
                return this;
            }
            public CheckerProperties setCheck(Check check) {
                this.check = check;
                return this;
            }
            public CheckerProperties setBlockType(BlockType blockType) {
                this.blockType = blockType;
                return this;
            }
        }

        private final IBlockState[] blockStates;
        private final BlockPos pos;
        private final IBlockAccess world;

        private final CheckerProperties.Kind kind;
        private final CheckerProperties.Check check;
        private final CheckerProperties.BlockType blockType;

        /**
         * Used to check a certain facing instead of a radius,
         * example: Using UP will make it go only up with radius
         * and anything under the pos will be ignored
         */
        private final EnumFacing facing;

        private final int radiusX, radiusY, radiusZ;

        public Checker(CheckerProperties properties, EnumFacing facing, IBlockAccess world, BlockPos pos, int radiusX, int radiusY, int radiusZ, IBlockState... blockStates) {
            this.blockStates = blockStates;
            this.kind = properties.kind;
            this.check = properties.check;
            this.blockType = properties.blockType;
            this.radiusX = radiusX;
            this.radiusY = radiusY;
            this.radiusZ = radiusZ;
            this.facing = facing;
            this.world = world;
            this.pos = pos;
        }
        public Checker(CheckerProperties properties, EnumFacing facing, IBlockAccess world, BlockPos pos, int radius, IBlockState... blockStates) {
            this(properties, facing, world, pos, radius, radius, radius, blockStates);
        }
        public Checker(CheckerProperties properties, EnumFacing facing, IBlockAccess world, BlockPos pos, int radius) {
            this(properties, facing, world, pos, radius, radius, radius, (IBlockState[]) null);
        }
        public Checker(CheckerProperties properties, IBlockAccess world, BlockPos pos, int radius, IBlockState... blockStates) {
            this(properties, null, world, pos, radius, radius, radius, blockStates);
        }
        public Checker(CheckerProperties properties, IBlockAccess world, BlockPos pos, int radiusX, int radiusY, int radiusZ) {
            this(properties, null, world, pos, radiusX, radiusY, radiusZ, (IBlockState[]) null);
        }
        public Checker(CheckerProperties properties, IBlockAccess world, BlockPos pos, int radius) {
            this(properties, null, world, pos, radius, radius, radius, (IBlockState[]) null);
        }

        public boolean check() {
            boolean value = false;
            for (BlockPos pos : getPositions()) {
                IBlockState state = world.getBlockState(pos);
                boolean isBlock = isCorrectBlock(state);

                if (check == CheckerProperties.Check.ANY && isBlock) {
                    return true;
                } else if (check == CheckerProperties.Check.ALL) {
                    if (!isBlock) {
                        return false;
                    } else value = true;
                }
            }
            return value;
        }
        public int count() {
            int value = 0;
            for (BlockPos pos : getPositions()) {
                IBlockState state = world.getBlockState(pos);
                boolean isBlock = isCorrectBlock(state);

                if (isBlock) {
                    value++;
                }
            }
            return value;
        }

        private boolean isCorrectBlock(IBlockState state) {
            Block block = state.getBlock();
            boolean isSolid = !block.equals(Blocks.AIR) && !block.isReplaceable(world, pos);
            boolean isAir = block.equals(Blocks.AIR) && !(state instanceof BlockLiquid || state instanceof BlockLiquidWrapper || state instanceof IFluidBlock || state.getMaterial().isLiquid());
            boolean i = ((isAir && this.blockType == CheckerProperties.BlockType.AIR) || (isSolid && this.blockType == CheckerProperties.BlockType.SOLID)) || blockType == CheckerProperties.BlockType.ALL;
            return (blockStates != null && Arrays.stream(blockStates).map(state1 -> equalsState(state1, state)).findAny().orElse(Boolean.FALSE)) || i;
        }
        private List<BlockPos> getPositions() {
            int radiusX = this.radiusX;
            int radiusZ = this.radiusZ;
            int radiusY = kind == CheckerProperties.Kind.FLAT ? 0 : this.radiusY;

            int radiusYs = facing == EnumFacing.UP ? 0 : radiusY;
            int radiusYe = facing == EnumFacing.DOWN ? 0 : radiusY;

            List<BlockPos> positions = Lists.newArrayList();
            for (int y = -radiusYs; y <= radiusYe; y++) {
                for (int x = -radiusX; x <= radiusX; x++) {
                    for (int z = -radiusZ; z <= radiusZ; z++) {
                        BlockPos pos = this.pos.add(x, y, z);

                        switch (kind) {
                            case HOLLOW: {
                                boolean a = Math.abs(x) == Math.abs(radiusX);
                                boolean b = y < 0 ? Math.abs(y) == Math.abs(radiusYs) : Math.abs(y) == Math.abs(radiusYe);
                                boolean c = Math.abs(z) == Math.abs(radiusZ);

                                if (a || b || c) {
                                    positions.add(pos);
                                }
                                break;
                            }
                            case WALL: {
                                boolean a = Math.abs(x) == Math.abs(radiusX);
                                boolean b = Math.abs(z) == Math.abs(radiusZ);

                                if (a || b) {
                                    positions.add(pos);
                                }
                                break;
                            }
                            case FLAT:
                                if (pos.getY() == this.pos.getY()) {
                                    positions.add(pos);
                                }
                                break;
                            case NORMAL:
                                positions.add(pos);
                                break;
                        }
                    }
                }
            }
            return positions;
        }
    }
}