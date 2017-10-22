package terrails.terracore.registry;

import net.minecraft.item.Item;
import terrails.terracore.Constants;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {

    private static List<Item> itemList = new ArrayList<>();

    public static void setItemList(List<Item> customList) {
        itemList = customList;
    }

    public static List<Item> getItemList() {
        return itemList;
    }

    public static Item[] getItems() {
        return getItemList().toArray(new Item[getItemList().size()]);
    }

    public static <T extends Item> Item addItem(T item) {
        getItemList().add(item);
        return item;
    }

    public static Item getItem(String itemName) {
        for (Item item : getItems()) {
            if (item.getRegistryName() != null && item.getRegistryName().getResourcePath().contains(itemName)) {
                return item;
            }
        }
        Constants.getLogger("TerraCore Item Registry").info("There was an error with " + itemName + ", report to github!");
        return null;
    }
}