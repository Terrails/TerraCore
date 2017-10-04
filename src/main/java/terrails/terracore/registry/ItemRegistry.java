package terrails.terracore.registry;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import terrails.terracore.Constants;

import java.util.Map;

public class ItemRegistry {

    private static Map<String, Item> itemMap;
    private static String modID;

    public static void setItemMap(Map<String, Item> map, String modName) {
        itemMap = map;
        modID = modName;
    }
    public static Map<String, Item> getItemMap() {
        return itemMap;
    }
    public static Item[] getItems() {
        return getItemMap().values().toArray(new Item[getItemMap().size()]);
    }

    public static Item addItem(String key, Item item) {
        getItemMap().put(key, item);
        return item;
    }
    public static Item getItem(String itemName) {
        for (Map.Entry<String, Item> entry : getItemMap().entrySet()) {
            String key = entry.getKey();
            Item value = entry.getValue();
            if (key.equals(itemName)) {
                return value;
            }
        }
        Constants.getLogger(modID).error(itemName + ", is an invalid item name, please report to mod author! (string given to prevent a crash)");
        return Items.STRING;
    }
}
