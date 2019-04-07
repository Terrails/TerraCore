package terrails.terracore.base.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientProxy implements IProxy {

    @Override
    public void setup(FMLCommonSetupEvent event) {

    }

    @Override
    public EntityPlayer getPlayer() {
        return Minecraft.getInstance().player;
    }
}
