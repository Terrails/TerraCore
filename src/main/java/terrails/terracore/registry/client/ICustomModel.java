package terrails.terracore.registry.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ICustomModel {

    @SideOnly(Side.CLIENT)
    void initModel();
}
