package terrails.terracore.base.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ServerProxy implements IProxy {

    @Override
    public void setup(FMLCommonSetupEvent event) {

    }

    @Override
    public EntityPlayer getPlayer() {
        return null;
    }
}
