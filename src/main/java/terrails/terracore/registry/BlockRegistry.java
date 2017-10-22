package terrails.terracore.registry;

import net.minecraft.block.Block;
import terrails.terracore.Constants;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry {

    private static List<Block> blockList = new ArrayList<>();

    public static void setBlockList(List<Block> customList) {
        blockList = customList;
    }

    public static List<Block> getBlockList() {
        return blockList;
    }

    public static Block[] getBlocks() {
        return getBlockList().toArray(new Block[getBlockList().size()]);
    }

    public static <T extends Block> Block addBlock(T block) {
        getBlockList().add(block);
        return block;
    }

    public static Block getBlock(String blockName) {
        for (Block block : getBlocks()) {
            if (block.getRegistryName() != null && block.getRegistryName().getResourcePath().contains(blockName)) {
                return block;
            }
        }
        Constants.getLogger("TerraCore Block Registry").info("There was an error with " + blockName + ", report to github!");
        return null;
    }
}
