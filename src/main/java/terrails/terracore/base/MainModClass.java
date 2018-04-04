package terrails.terracore.base;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import terrails.terracore.base.proxies.IProxy;
import terrails.terracore.registry.*;

public abstract class MainModClass<T extends MainModClass> implements IModEntry<T> {

    private final String modId;
    private final String modName;
    private final String version;
    protected IProxy proxy;

    public MainModClass(String modId, String modName, String version) {
        this.modId = modId;
        this.modName = modName;
        this.version = version;

        this.proxy = createProxies();
        if (this.proxy != null)
            this.proxy.setMod(this);
    }

    public void preInit(FMLPreInitializationEvent event) {
        this.proxy.preInit(event);
        this.register(null, LoadingStage.PRE_INIT, Side.SERVER);
        MinecraftForge.EVENT_BUS.register(new RegistryEventHandler(this));
    }
    public void init(FMLInitializationEvent event) {
        this.proxy.init(event);
        this.register(null, LoadingStage.INIT, Side.SERVER);
    }
    public void postInit(FMLPostInitializationEvent event) {
        this.proxy.postInit(event);
        this.register(null, LoadingStage.POST_INIT, Side.SERVER);
    }

    /** Registry **/
    protected RegistryList register(RegistryList list, LoadingStage stage, Side side) {
        // Using Side.SERVER for registries... and Side.CLIENT for client stuff
        // Yes... I know that some of it is common but I just call is SERVER
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
    public RegistryList getRegistry(RegistryList list, LoadingStage stage, Side side) {
        return this.register(list, stage, side);
    }
    @Override
    public IProxy getProxy() {
        return this.proxy;
    }

    /** END **/


    private IProxy createProxies() {
        try {
            Side side = FMLCommonHandler.instance().getEffectiveSide();
            String proxy = side == Side.CLIENT ? "terrails.terracore.base.proxies.ClientProxy" : "terrails.terracore.base.proxies.ServerProxy";
            Object object = Class.forName(proxy).newInstance();
            if (object instanceof IProxy) {
                return ((IProxy) object);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
