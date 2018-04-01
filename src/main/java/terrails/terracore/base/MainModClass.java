package terrails.terracore.base;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import terrails.terracore.registry.RegistryEventHandler;
import terrails.terracore.registry.RegistryType;
import terrails.terracore.registry.SimpleRegistry;

import java.util.Arrays;
import java.util.Map;

public abstract class MainModClass<T extends MainModClass> implements IModEntry<T>, IRegistryEntry {

    private final String modId;
    private final String modName;
    private final String version;

    private Map<RegistryType, SimpleRegistry> registries;

    public MainModClass(String modId, String modName, String version) {
        this.modId = modId;
        this.modName = modName;
        this.version = version;
    }

    public void preInit(FMLPreInitializationEvent event) {
        if (!hasCustomRegistry()) {
            MinecraftForge.EVENT_BUS.register(new RegistryEventHandler(this));
            Arrays.stream(RegistryType.values()).forEach(type -> addRegistry(SimpleRegistry.class, type));
            Arrays.stream(RegistryType.values()).forEach(type -> registerForgeEntries(getRegistry(SimpleRegistry.class, type), type));
        }
    }

    @SuppressWarnings("unchecked")
    public void registerForgeEntries(SimpleRegistry registry, RegistryType type) {}
    public boolean hasCustomRegistry() {
        return false;
    }

    /** IRegistryEntry **/
    @Override
    public Map<RegistryType, SimpleRegistry> getRegistries() {
        return registries;
    }
    @Override
    public <R extends SimpleRegistry> void addRegistry(Class<R> registry, RegistryType type) {
        if (registries == null) {
            registries = Maps.newHashMap();
        }
        switch (type) {
            case BLOCK:
                registries.put(type, registry.cast(new SimpleRegistry<Block>(type, this)));
                break;
            case ITEM:
                registries.put(type, registry.cast(new SimpleRegistry<Item>(type, this)));
                break;
            case POTION:
                registries.put(type, registry.cast(new SimpleRegistry<Potion>(type, this)));
                break;
            case BIOME:
                registries.put(type, registry.cast(new SimpleRegistry<Biome>(type, this)));
                break;
            case ENCHANTMENT:
                registries.put(type, registry.cast(new SimpleRegistry<Enchantment>(type, this)));
                break;
            case SOUND_EVENT:
                registries.put(type, registry.cast(new SimpleRegistry<SoundEvent>(type, this)));
                break;
        }
    }
    @Override
    public <R extends SimpleRegistry> R getRegistry(Class<R> clazz, RegistryType type) {
        SimpleRegistry registry = registries.get(type);

        if (clazz.isInstance(registry)) {
            return clazz.cast(registry);
        }

        return null;
    }

    /** IModEntry **/
    @Override
    public String getId() {
        return this.modId;
    }
    @Override
    public String getName() {
        return this.modName;
    }
    @Override
    public String getVersion() {
        return this.version;
    }
}
