package terrails.terracore.registry;


import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;

public enum RegistryType {
    BLOCK,
    ITEM,
    POTION,
    BIOME,
    SOUND_EVENT,
    ENCHANTMENT;

    public static RegistryType getFromClass(Class clazz) {
        if (clazz.getName().equalsIgnoreCase(Block.class.getName())) {
            return BLOCK;
        } else if (clazz.getName().equalsIgnoreCase(Item.class.getName())) {
            return ITEM;
        } else if (clazz.getName().equalsIgnoreCase(Potion.class.getName())) {
            return POTION;
        } else if (clazz.getName().equalsIgnoreCase(Biome.class.getName())) {
            return BIOME;
        } else if (clazz.getName().equalsIgnoreCase(SoundEvent.class.getName())) {
            return SOUND_EVENT;
        } else if (clazz.getName().equalsIgnoreCase(Enchantment.class.getName())) {
            return ENCHANTMENT;
        }
        return null;
    }
}