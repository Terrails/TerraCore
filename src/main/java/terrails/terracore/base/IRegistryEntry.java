package terrails.terracore.base;

import net.minecraftforge.registries.IForgeRegistryEntry;
import terrails.terracore.registry.*;

import java.util.List;
import java.util.Map;

public interface IRegistryEntry {

   // Map<RegistryType, RegistryForgeEntry> getRegistries();
   // RegistryForgeEntry getRegistry(RegistryType type);
   // void getCustomRegistries(Map<RegistryType, RegistryForgeEntry> map);
   // void addRegistry(RegistryType type);

    RegistryList register(RegistryList<? extends IForgeRegistryEntry> list, LoadingStage stage);
}
