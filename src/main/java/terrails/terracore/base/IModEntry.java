package terrails.terracore.base;

import net.minecraftforge.fml.relauncher.Side;
import terrails.terracore.base.proxies.IProxy;
import terrails.terracore.registry.LoadingStage;
import terrails.terracore.registry.RegistryList;

public interface IModEntry<T> {

    T getInstance();

    String getId();
    String getName();
    String getVersion();

    IProxy getProxy();

    RegistryList getRegistry(RegistryList list, LoadingStage stage, Side side);
}
