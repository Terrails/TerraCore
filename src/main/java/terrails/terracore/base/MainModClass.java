package terrails.terracore.base;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import terrails.terracore.registry.*;

import java.util.Arrays;
import java.util.Map;

public abstract class MainModClass<T extends MainModClass> implements IModEntry<T>, IRegistryEntry {

    private final String modId;
    private final String modName;
    private final String version;

    private RegistryCore registry = new RegistryCore(this);

    public MainModClass(String modId, String modName, String version) {
        this.modId = modId;
        this.modName = modName;
        this.version = version;
    }

    public void preInit(FMLPreInitializationEvent event) {
        register(null, LoadingStage.PRE_INIT);
        MinecraftForge.EVENT_BUS.register(new RegistryEventHandler(this));
        //Arrays.stream(RegistryType.values()).forEach(type -> addRegistry(new RegistryForgeEntry(type, this), type));
    //    Arrays.stream(RegistryType.values()).forEach(type -> register(this.registry.getRegistry(type), LoadingStage.REGISTER));
    }

    public void init(FMLInitializationEvent event) {
        register(null, LoadingStage.INIT);
    }

    public void postInit(FMLPostInitializationEvent event) {
        register(null, LoadingStage.POST_INIT);
    }

    /** IRegistryEntry **/
    @Override
    public Map<RegistryType, Registry> getRegistries() {
        return this.registry.getEntries();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R extends Registry> R getRegistry(RegistryType type) {
        return (R) registry.getRegistry(type);
    }

    @Override
    public Map<RegistryType, Registry> getCustomRegistries() { return null; }

    @Override
    public Registry register(Registry registry, LoadingStage stage) {
        return registry;
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
    @Override
    public IRegistryEntry getRegistry() {
        return this;
    }
}
