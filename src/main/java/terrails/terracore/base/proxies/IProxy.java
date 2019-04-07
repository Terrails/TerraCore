package terrails.terracore.base.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public interface IProxy {

    void setup(final FMLCommonSetupEvent event);

    EntityPlayer getPlayer();
}
