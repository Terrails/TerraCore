package terrails.terracore.base;

import net.minecraftforge.fml.relauncher.Side;
import terrails.terracore.base.proxies.LoadingStage;
import terrails.terracore.base.registry.RegistryList;

public interface IProxyRegistry {

    void registerForgeEntries(RegistryList list);
    void registerProxyEntries(Side side, LoadingStage stage);
}
