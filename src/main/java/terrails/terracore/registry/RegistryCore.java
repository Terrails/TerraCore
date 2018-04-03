package terrails.terracore.registry;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraftforge.registries.IForgeRegistryEntry;
import terrails.terracore.base.IModEntry;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RegistryCore {

    private Map<RegistryType, Registry> entries = Maps.newHashMap();
    private IModEntry modEntry;

    public RegistryCore(IModEntry modEntry) {
        this.modEntry = modEntry;
    }

    public Registry getRegistry(RegistryType type) {
        if (!entries.containsKey(type)) {
            createRegistries();
        }
        return entries.get(type);
    }

    @SuppressWarnings("unchecked")
    public void register(Object... objects) {
        Arrays.stream(objects).filter(IForgeRegistryEntry.class::isInstance).forEach(object -> getRegistry(RegistryType.getFromClass(object.getClass())).register(object));

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

    @SuppressWarnings("SuspiciousMethodCalls")
    private void createRegistries() {
        List<Registry> registries = Lists.newArrayList();
        setCustomRegistries(registries);
        for (RegistryType type : RegistryType.values()) {

            if (registries.stream().map(registry -> registry.getType() == type).findAny().orElse(false))
                continue;

            registries.add(new RegistryForgeEntry(type, modEntry));
        }
        registries.forEach(registry -> entries.put(registry.getType(), registry));
    }

    private void put(Map<Object, Registry> registries) {
        if (registries.keySet().stream().map(object -> object instanceof Block).findFirst().orElse(false)) {

        }
    }

    protected void setCustomRegistries(List<Registry> customRegistries) {}

    public Map<RegistryType, Registry> getEntries() {
        return this.entries;
    }
}
