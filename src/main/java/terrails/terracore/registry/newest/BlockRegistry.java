package terrails.terracore.registry.newest;

import net.minecraft.block.Block;

import java.util.List;

public class BlockRegistry {

    private static List<Block> blockList;

    public BlockRegistry(List<Block> theList) {
        blockList = theList;
    }

    public static List<Block> getList() {
        return blockList;
    }

    public static Block[] getBlocks() {
        return getList().toArray(new Block[getList().size()]);
    }

    public static  <T extends Block> T add(T block) {
        getList().add(block);
        return block;
    }

    public static Block getBlock(String name) {
        for (Block block : getBlocks()) {
            if (block.getRegistryName() != null && block.getRegistryName().getResourcePath().contains(name)) {
                return block;
            }
        }
        return null;
    }
}
