package terrails.terracore.base;

import terrails.terracore.base.proxies.ProxyBase;

public interface IModEntry<T> {

    T getInstance();

    String getId();
    String getName();
    String getVersion();

    IModRegistry getRegistry();

    ProxyBase getProxy();
}
