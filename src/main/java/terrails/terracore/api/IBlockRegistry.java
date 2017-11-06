package terrails.terracore.api;

import net.minecraft.block.Block;

import java.util.List;

public interface IBlockRegistry {

    /**
     * The list which will be used to add and register objects
     * @return {@link List}
     */
    List<Block> getList();

    /**
     * The array which is gotten from {@link #getList()}
     * @return {@link Block[]}
     */
    default Block[] getBlocks() {
        return getList().toArray(new Block[getList().size()]);
    }

    /**
     * Adds the block to the {@link #getList()}
     * @param block the block object
     * @param <T> makes sure to object extends {@link Block}
     * @return the block object
     */
    default <T extends Block> T add(T block) {
        getList().add(block);
        return block;
    }

    /**
     * Gets the block by iterating through {@link #getBlocks()}
     * @param name of the block
     * @return {@link Block}
     */
    default Block getBlock(String name) {
        for (Block block : getBlocks()) {
            if (block.getRegistryName() != null && block.getRegistryName().getResourcePath().contains(name)) {
                return block;
            }
        }
        return null;
    }
}
