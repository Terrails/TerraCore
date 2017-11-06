package terrails.terracore.registry;

import net.minecraft.potion.Potion;

import java.util.List;

public interface IPotionRegistry {
    
    /**
     * The list which will be used to add and register objects
     * @return {@link List}
     */
    List<Potion> getList();

    /**
     * The array which is gotten from {@link #getList()}
     * @return {@link Potion[]}
     */
    default Potion[] getPotions() {
        return getList().toArray(new Potion[getList().size()]);
    }

    /**
     * Adds the potion to the {@link #getList()}
     * @param potion the potion object
     * @param <T> makes sure to object extends {@link Potion}
     * @return the potion object
     */
    default <T extends Potion> T add(T potion) {
        getList().add(potion);
        return potion;
    }

    /**
     * Gets the potion by iterating through {@link #getPotions()}
     * @param name of the potion
     * @return {@link Potion}
     */
    default Potion getPotion(String name) {
        for (Potion potion : getPotions()) {
            if (potion.getRegistryName() != null && potion.getRegistryName().getResourcePath().contains(name)) {
                return potion;
            }
        }
        return null;
    }
}
