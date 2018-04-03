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
        if (object.getClass() == Block.class) {
            return BLOCK;
        } else if (object.getClass() == Item.class) {
            return ITEM;
        } else if (object.getClass() == Potion.class) {
            return POTION;
        } else if (object.getClass() == Biome.class) {
            return BIOME;
        } else if (object.getClass() == SoundEvent.class) {
            return SOUND_EVENT;
        } else if (object.getClass() == Enchantment.class) {
            return ENCHANTMENT;
        }
        return null;
    }
}