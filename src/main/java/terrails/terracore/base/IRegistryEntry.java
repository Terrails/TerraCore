package terrails.terracore.base;

import terrails.terracore.registry.*;

import java.util.Map;

public interface IRegistryEntry {

    Map<RegistryType, Registry> getRegistries();
    <R extends Registry> R getRegistry(RegistryType type);
    Map<RegistryType, Registry> getCustomRegistries();

    Registry register(Registry registry, LoadingStage stage);
    //   <R extends Registry> R addRegistry(R registry, RegistryType type);
 //   <R extends Registry> R addRegistry(RegistryType type);

    // Map<RegistryType, SimpleRegistry> getRegistries();

 //   <R extends SimpleRegistry> void addRegistry(Class<R> registry, RegistryType type);
 //   <R extends SimpleRegistry> R getRegistry(Class<R> clazz, RegistryType type);
}
