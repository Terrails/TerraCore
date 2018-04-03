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

    public static RegistryType getFromObject(Object object) {
        if (object instanceof Block) {
            return BLOCK;
        } else if (object instanceof Item) {
            return ITEM;
        } else if (object instanceof Potion) {
            return POTION;
        } else if (object instanceof Biome) {
            return BIOME;
        } else if (object instanceof SoundEvent) {
            return SOUND_EVENT;
        } else if (object instanceof Enchantment) {
            return ENCHANTMENT;
        }
        return null;
    }
}