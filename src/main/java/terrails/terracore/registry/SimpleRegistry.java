package terrails.terracore.registry;

import com.google.common.collect.Lists;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.List;

public class SimpleRegistry<T extends IForgeRegistryEntry> {

    private List<T> registryEntries = Lists.newArrayList();

    public List<T> getRegistryEntries() {
        return this.registryEntries;
    }

    public T add(T entry) {
        if (entry.getRegistryName() == null) {
            throw new NullPointerException(entry + ", doesn't have a registry name!");
        }
        registryEntries.add(entry);
        return entry;
    }

    @SuppressWarnings("unchecked")
    public RegistryType getRegistryType() {
        Class<T> clazz = (Class<T>) getClass();
        return RegistryType.getRegistryType(clazz);
    }

    public T get(String string) {
        if (string.contains(":")) {
            ResourceLocation location = new ResourceLocation(string);
            return registryEntries.stream()
                    .filter(value -> value.getRegistryName() != null)
                    .filter(value -> value.getRegistryName().equals(location))
                    .findFirst().orElse(null);
        }
        return registryEntries.stream()
                .filter(value -> value.getRegistryName() != null)
                .filter(value -> value.getRegistryName().getResourcePath().equals(string))
                .findFirst().orElse(null);
    }
    public T get(ResourceLocation location) {
        return get(location.toString());
    }
}
