package terrails.terracore.base;

import terrails.terracore.base.proxies.ProxyBase;
import terrails.terracore.base.registry.RegistryList;

public interface IModEntry<T> {

    T getInstance();

    String getId();
    String getName();
    String getVersion();

    IProxyRegistry getProxyRegistry();

    ProxyBase getProxy();
    RegistryList getRegistry(RegistryList list);
}
