package terrails.terracore.registry;

import net.minecraft.potion.Potion;
import terrails.terracore.Constants;

import java.util.Map;

public class PotionRegistry {

    private static Map<String, Potion> potionMap;
    private static String modID;

    public static void setPotionMap(Map<String, Potion> map, String modName) {
        potionMap = map;
        modID = modName;
    }
    public static Map<String, Potion> getPotionMap() {
        return potionMap;
    }
    public static Potion[] getPotions() {
        return getPotionMap().values().toArray(new Potion[getPotionMap().size()]);
    }

    public static Potion addPotion(String key, Potion potion) {
        getPotionMap().put(key, potion);
        return potion;
    }
    public static Potion getPotion(String potionName) {
        for (Map.Entry<String, Potion> entry : getPotionMap().entrySet()) {
            String key = entry.getKey();
            Potion value = entry.getValue();
            if (key.equals(potionName)) {
                return value;
            }
        }
        Constants.getLogger(modID).error(potionName + ", is an invalid potion name, please report to mod author! (unluck given to prevent a crash)");
        return Potion.getPotionFromResourceLocation("unluck");
    }
}
