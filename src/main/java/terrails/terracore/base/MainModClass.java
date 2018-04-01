package terrails.terracore.base;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
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

    @SuppressWarnings("unchecked")
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new RegistryEventHandler(this));

        Arrays.stream(RegistryType.values()).forEach(type -> addRegistry(SimpleRegistry.class, type));
     //   addRegistry(SimpleRegistry.class, RegistryType.BLOCKS);
    //    addRegistry(SimpleRegistry.class, RegistryType.ITEMS);
        Arrays.stream(RegistryType.values()).forEach(type -> registerForgeEntries(getRegistry(SimpleRegistry.class, type), type));
    //    this.registerForgeEntries(getRegistry(SimpleRegistry.class, RegistryType.BLOCKS));
    //    this.registerForgeEntries(getRegistry(SimpleRegistry.class, RegistryType.ITEMS));
    }

    public void registerForgeEntries(SimpleRegistry registry, RegistryType type) {

    }


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
            case BLOCKS:
                registries.put(type, registry.cast(new SimpleRegistry<Block>()));
                break;
            case ITEMS:
                registries.put(type, registry.cast(new SimpleRegistry<Item>()));
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
