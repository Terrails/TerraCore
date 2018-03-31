package terrails.terracore.base;

import terrails.terracore.registry.RegistryType;
import terrails.terracore.registry.SimpleRegistry;

import java.util.Map;

public interface IRegistryEntry {

    Map<RegistryType, SimpleRegistry> getRegistries();

    <R extends SimpleRegistry> void addRegistry(Class<R> registry, RegistryType type);
    <R extends SimpleRegistry> R getRegistry(Class<R> clazz, RegistryType type);
}
