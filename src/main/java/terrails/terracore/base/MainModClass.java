package terrails.terracore.base;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import terrails.terracore.base.proxies.ProxyBase;
import terrails.terracore.base.registry.LoadingStage;
import terrails.terracore.base.registry.RegistryEventHandler;
import terrails.terracore.base.registry.RegistryList;

public abstract class MainModClass<T extends MainModClass> implements IModEntry<T>, IProxyRegistry {

    private final String modId;
    private final String modName;
    private final String version;
    private final ProxyBase proxyBase;

    /** Fields used for "config" options of the MainClass **/ // TODO: Add more stuff
    protected boolean useRegistry = true;

    public MainModClass(String modId, String modName, String version) {
        this.modId = modId;
        this.modName = modName;
        this.version = version;

        this.proxyBase = createProxies();
        if (this.proxyBase != null) {
            this.proxyBase.setMod(this);
        }
    }

    public void preInit(FMLPreInitializationEvent event) {
        this.proxyBase.preInit(event);

        if (useRegistry) {
            MinecraftForge.EVENT_BUS.register(new RegistryEventHandler(this));
        }
    }
    public void init(FMLInitializationEvent event) {
        this.proxyBase.init(event);
    }
    public void postInit(FMLPostInitializationEvent event) {
        this.proxyBase.postInit(event);
    }

    /** IProxyRegistry **/ // TODO: Find a better name for it

    @Override
    public void registerForgeEntries(RegistryList list) {}

    @Override
    public void registerProxyEntries(Side side, LoadingStage stage) {}

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
    public ProxyBase getProxy() {
        return this.proxyBase;
    }
    @Override
    public IProxyRegistry getProxyRegistry() {
        return this;
    }
    @Override
    public RegistryList getRegistry(RegistryList list) {
        this.registerForgeEntries(list);
        return list;
    }

    /** END **/

    private ProxyBase createProxies() {
        try {
            Side side = FMLCommonHandler.instance().getEffectiveSide();
            String target = side.isClient() ? "terrails.terracore.base.proxies.ClientProxy" : "terrails.terracore.base.proxies.ServerProxy";
            Object proxy = Class.forName(target).newInstance();
            if (proxy instanceof ProxyBase) {
                return (ProxyBase) proxy;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
