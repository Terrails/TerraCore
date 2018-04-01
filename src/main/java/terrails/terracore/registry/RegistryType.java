package terrails.terracore.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Arrays;

public enum RegistryType {
    BLOCKS(Block.class), ITEMS(Item.class);

    private final Class clazz;

    <T extends IForgeRegistryEntry> RegistryType(Class<T> clazz) {
        this.clazz = clazz;
    }

    public static <T extends IForgeRegistryEntry> RegistryType getRegistryType(Class<T> clazz) {
        return Arrays.stream(RegistryType.values()).filter(type -> type.clazz.isInstance(clazz)).findFirst().orElse(null);
    }
}
