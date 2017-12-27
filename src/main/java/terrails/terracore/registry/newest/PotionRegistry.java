package terrails.terracore.registry.newest;

import net.minecraft.potion.Potion;

import java.util.List;

public class PotionRegistry {

    protected static List<Potion> potionList;

    public static List<Potion> getList() {
        if (potionList == null) {
            throw new RuntimeException("TerraCore ItemRegistry list is null");
        }
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
