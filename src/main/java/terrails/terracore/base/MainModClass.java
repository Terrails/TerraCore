package terrails.terracore.base;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import terrails.terracore.registry.RegistryEventHandler;
import terrails.terracore.registry.RegistryType;
import terrails.terracore.registry.SimpleRegistry;

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

        this.registerBlocks(getRegistry(SimpleRegistry.class, RegistryType.BLOCKS));
        this.registerItems(getRegistry(SimpleRegistry.class, RegistryType.ITEMS));
    }

    public void registerBlocks(SimpleRegistry<Block> registry) {

    }

    public void registerItems(SimpleRegistry<Block> registry) {

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
        registries.put(type, registry.cast(SimpleRegistry.class));
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
