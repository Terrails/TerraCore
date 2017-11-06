package terrails.terracore.api;

import net.minecraft.item.Item;

import java.util.List;

public interface IItemRegistry {

    /**
     * The list which will be used to add and register objects
     * @return {@link List}
     */
    List<Item> getList();

    /**
     * The array which is gotten from {@link #getList()}
     * @return {@link Item[]}
     */
    default Item[] getItems() {
        return getList().toArray(new Item[getList().size()]);
    }

    /**
     * Adds the item to the {@link #getList()}
     * @param item the item object
     * @param <T> makes sure to object extends {@link Item}
     * @return the item object
     */
    default <T extends Item> T add(T item) {
        getList().add(item);
        return item;
    }

    /**
     * Gets the item by iterating through {@link #getItems()}
     * @param name of the item
     * @return {@link Item}
     */
    default Item getItem(String name) {
        for (Item item : getItems()) {
            if (item.getRegistryName() != null && item.getRegistryName().getResourcePath().contains(name)) {
                return item;
            }
        }
        return null;
    }
}
