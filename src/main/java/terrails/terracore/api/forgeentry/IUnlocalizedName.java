package terrails.terracore.api.forgeentry;

import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IUnlocalizedName<T extends IForgeRegistryEntry<T>> extends IForgeRegistryEntry<T> {

    String getUnlocalizedName();
    T setUnlocalizedName(String name);
}
