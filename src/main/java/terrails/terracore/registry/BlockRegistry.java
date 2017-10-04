package terrails.terracore.registry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import terrails.terracore.Constants;

import java.util.HashMap;
import java.util.Map;

public class BlockRegistry {

    private static Map<String, Block> blockMap = new HashMap<>();
    private static String modID;

    public static void setBlockMap(Map<String, Block> map, String modName) {
        blockMap = map;
        modID = modName;
    }
    public static Map<String, Block> getBlockMap() {
        return blockMap;
    }
    public static Block[] getBlocks() {
        return getBlockMap().values().toArray(new Block[getBlockMap().size()]);
    }

    public static Block addBlock(String key, Block block) {
        getBlockMap().put(key, block);
        return block;
    }
    public static Block getBlock(String blockName) {
        for (Map.Entry<String, Block> entry : getBlockMap().entrySet()) {
            String key = entry.getKey();
            Block value = entry.getValue();
            if (key.equals(blockName)) {
                return value;
            }
        }
        Constants.getLogger(modID).error(blockName + ", is an invalid block name, please report to mod author! (dead bush given to prevent a crash)");
        return Blocks.DEADBUSH;
    }
}
