package terrails.terracore.registry.newest;

import net.minecraft.potion.Potion;

import java.util.List;

public class PotionRegistry {

    private static List<Potion> potionList;

    public PotionRegistry(List<Potion> theList) {
        potionList = theList;
    }

    public static List<Potion> getList() {
        return potionList;
    }

    public static Potion[] getPotions() {
        return getList().toArray(new Potion[getList().size()]);
    }

    public static  <T extends Potion> T add(T potion) {
        getList().add(potion);
        return potion;
    }

    public static Potion getPotion(String name) {
        for (Potion potion : getPotions()) {
            if (potion.getRegistryName() != null && potion.getRegistryName().getResourcePath().contains(name)) {
                return potion;
            }
        }
        return null;
    }
}
