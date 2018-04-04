package terrails.terracore.base.proxies;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import terrails.terracore.base.IModEntry;
import terrails.terracore.registry.LoadingStage;

public class ClientProxy implements IProxy {

    private IModEntry modEntry;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        modEntry.getRegistry(null, LoadingStage.PRE_INIT, Side.CLIENT);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        modEntry.getRegistry(null, LoadingStage.INIT, Side.CLIENT);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        modEntry.getRegistry(null, LoadingStage.POST_INIT, Side.CLIENT);
    }

    @Override
    public void setMod(IModEntry mod) {
        this.modEntry = mod;
    }
}
