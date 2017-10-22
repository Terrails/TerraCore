package terrails.terracore.registry;

import net.minecraft.potion.Potion;
import terrails.terracore.Constants;

import java.util.ArrayList;
import java.util.List;

public class PotionRegistry {

    private static List<Potion> potionList = new ArrayList<>();

    public static void setPotionList(List<Potion> customList) {
        potionList = customList;
    }

    public static List<Potion> getPotionList() {
        return potionList;
    }

    public static Potion[] getPotions() {
        return getPotionList().toArray(new Potion[getPotionList().size()]);
    }

    public static <T extends Potion> Potion addPotion(T potion) {
        getPotionList().add(potion);
        return potion;
    }

    public static Potion getPotion(String potionName) {
        for (Potion potion : getPotions()) {
            if (potion.getRegistryName() != null && potion.getRegistryName().getResourcePath().contains(potionName)) {
                return potion;
            }
        }
        Constants.getLogger("TerraCore Potion Registry").info("There was an error with " + potionName + ", report to github!");
        return null;
    }
}
