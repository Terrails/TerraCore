package terrails.terracore.base;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;
import terrails.terracore.base.proxies.ProxyBase;
import terrails.terracore.base.registry.LoadingStage;
import terrails.terracore.base.registry.RegistryEventHandler;
import terrails.terracore.base.registry.RegistryList;
import terrails.terracore.base.registry.RegistryType;
import terrails.terracore.block.item.ItemBlockBase;

import java.util.List;

public abstract class MainModClass<T extends MainModClass> implements IModEntry<T>, IModRegistry {

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

        if (this.useRegistry) {
            MinecraftForge.EVENT_BUS.register(new RegistryEventHandler(this));
        }
    }
    public void init(FMLInitializationEvent event) {
        this.proxyBase.init(event);
    }
    public void postInit(FMLPostInitializationEvent event) {
        this.proxyBase.postInit(event);
    }

    /** IModRegistry **/

    @SideOnly(Side.CLIENT)
    @Override
    public void initClientProxy(LoadingStage stage) {
        this.registerProxyEntries(Side.CLIENT, stage);
    }
    @SideOnly(Side.SERVER)
    @Override
    public void initServerProxy(LoadingStage stage) {
        this.registerProxyEntries(Side.SERVER, stage);
    }

    @Override
    public <R extends IForgeRegistryEntry> List<R> getForgeEntries(List<IForgeRegistryEntry> list, RegistryType type) {
        RegistryList regList = RegistryList.newInstance(type, this);
        list.add(new ItemBlockBase(null));
        this.registerForgeEntries(regList);
        return (regList.isEmpty() ? (List<R>) list : regList);
    }

    // Used for mods which use the old methods... TODO: Remove in 1.13
    @Deprecated
    public void registerProxyEntries(Side side, LoadingStage stage) {}
    @Deprecated
    public void registerForgeEntries(RegistryList list) {}

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
    public IModRegistry getRegistry() {
        return this;
    }

    /** END **/

    private ProxyBase createProxies() {
        try {
            Side side = FMLCommonHandler.instance().getSide();
            String target = side.isClient() ? "terrails.terracore.base.proxies.ClientProxy" : "terrails.terracore.base.proxies.ServerProxy";
            Object proxy = Class.forName(target).newInstance();
            if (proxy instanceof ProxyBase) {
                if (side.isClient()) {
                    MinecraftForge.EVENT_BUS.register(proxy);
                }
                return (ProxyBase) proxy;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
