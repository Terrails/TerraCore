package terrails.terracore.registry;

import com.google.common.collect.Maps;
import net.minecraftforge.registries.IForgeRegistryEntry;
import terrails.terracore.base.IModEntry;

import java.util.Arrays;
import java.util.Map;

public class RegistryCore {

    private Map<RegistryType, Registry> entries = Maps.newHashMap();
    private IModEntry modEntry;

    public RegistryCore(IModEntry modEntry) {
        this.modEntry = modEntry;
    }

    public Registry getRegistry(RegistryType type) {
        if (!entries.containsKey(type)) {
            addRegistry(type);
        }
        return entries.get(type);
    }

    @SuppressWarnings("unchecked")
    public void addRegistry(RegistryType type) {
        if (entries.containsKey(type)) return;

        Map<RegistryType, Registry> registryMap = modEntry.getRegistry().getCustomRegistries();
        if (registryMap == null) registryMap = Maps.newHashMap();
        if (registryMap.containsKey(type)) {
            entries.put(type, registryMap.get(type));
        }

        entries.put(type, new RegistryForgeEntry(type, modEntry));
    }

    @SuppressWarnings("unchecked")
    public void register(Object... objects) {
        Arrays.stream(objects).filter(IForgeRegistryEntry.class::isInstance).forEach(object -> getRegistry(RegistryType.getFromObject(object)).register(object));

        /*
        for (Object object : objects) {

            if (object instanceof Block) {
                Block block = (Block) object;
                entries.get(RegistryType.BLOCK).register(block);
            } else if (object instanceof Item) {
                Item item = (Item) object;
                entries.get(RegistryType.ITEM).register(item);
            } else if (object instanceof Potion) {
                Potion potion = (Potion) object;
                entries.get(RegistryType.POTION).register(potion);
            } else if (object instanceof Biome) {
                Biome biome = (Biome) object;
                entries.get(RegistryType.BIOME).register(biome);
            } else if (object instanceof SoundEvent) {
                SoundEvent soundEvent = (SoundEvent) object;
                entries.get(RegistryType.SOUND_EVENT).register(soundEvent);
            } else if (object instanceof Enchantment) {
                Enchantment enchantment = (Enchantment) object;
                entries.get(RegistryType.ENCHANTMENT).register(enchantment);
            }
        }
        */
    }

    public Map<RegistryType, Registry> getEntries() {
        return this.entries;
    }
}
