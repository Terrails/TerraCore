package terrails.terracore.registry.newest;

import net.minecraft.item.Item;

import java.util.List;

public class ItemRegistry {

    private static List<Item> itemList;

    public ItemRegistry(List<Item> theList) {
        itemList = theList;
    }

    public static List<Item> getList() {
        return itemList;
    }

    public static Item[] getItems() {
        return getList().toArray(new Item[getList().size()]);
    }

    public static  <T extends Item> T add(T item) {
        getList().add(item);
        return item;
    }

    public static Item getItem(String name) {
        for (Item item : getItems()) {
            if (item.getRegistryName() != null && item.getRegistryName().getResourcePath().contains(name)) {
                return item;
            }
        }
        return null;
    }
}
