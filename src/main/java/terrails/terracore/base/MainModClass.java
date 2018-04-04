package terrails.terracore.base;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;
import terrails.terracore.registry.*;

public abstract class MainModClass<T extends MainModClass> implements IModEntry<T>, IRegistryEntry {

    private final String modId;
    private final String modName;
    private final String version;

  //  private Map<RegistryType, RegistryForgeEntry> registryEntries = Maps.newHashMap();

    public MainModClass(String modId, String modName, String version) {
        this.modId = modId;
        this.modName = modName;
        this.version = version;
    }

    public void preInit(FMLPreInitializationEvent event) {
        this.register(null, LoadingStage.PRE_INIT);
        MinecraftForge.EVENT_BUS.register(new RegistryEventHandler(this));
    }
    public void init(FMLInitializationEvent event) {
        this.register(null, LoadingStage.INIT);
    }
    public void postInit(FMLPostInitializationEvent event) {
        this.register(null, LoadingStage.POST_INIT);
    }

    /** IRegistryEntry **/
/*
    @Override
    public Map<RegistryType, RegistryForgeEntry> getRegistries() {
        return this.registryEntries;
    }

    @Override
    public RegistryForgeEntry getRegistry(RegistryType type) {
        if (!registryEntries.containsKey(type)) {
            addRegistry(type);
        }
        return registryEntries.get(type);
    }

    @Override
    public void addRegistry(RegistryType type) {
        if (registryEntries.containsKey(type)) return;

        Map<RegistryType, RegistryForgeEntry> registryMap = Maps.newHashMap();
        getCustomRegistries(registryMap);
        if (registryMap.containsKey(type)) {
            registryEntries.put(type, registryMap.get(type));
        }

        registryEntries.put(type, new RegistryForgeEntry(type, this));
    }

    @Override
    public void getCustomRegistries(Map<RegistryType, RegistryForgeEntry> map) {}
*/
    @Override
    public RegistryList register(RegistryList<? extends IForgeRegistryEntry> list, LoadingStage stage) {
        return list;
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
