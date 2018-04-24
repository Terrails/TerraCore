package terrails.terracore.base;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;
import terrails.terracore.base.registry.LoadingStage;
import terrails.terracore.base.registry.RegistryType;

import java.util.List;

public interface IModRegistry {

    <R extends IForgeRegistryEntry<R>> List<R> getForgeEntries(List<IForgeRegistryEntry> list, RegistryType type);

    @SideOnly(Side.CLIENT)
    void initClientProxy(LoadingStage stage);
    @SideOnly(Side.SERVER)
    void initServerProxy(LoadingStage stage);
}
