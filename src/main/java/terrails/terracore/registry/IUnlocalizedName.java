package terrails.terracore.registry;

import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IUnlocalizedName<T extends IForgeRegistryEntry<T>> {

    String getEntryName();
    T setEntryName(String name);
}
