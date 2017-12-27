package terrails.terracore.registry.newest;

import net.minecraft.item.Item;

import java.util.List;

public class ItemRegistry {

    protected static List<Item> itemList;

    public static List<Item> getList() {
        if (itemList == null) {
            throw new RuntimeException("TerraCore ItemRegistry list is null");
        }
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
