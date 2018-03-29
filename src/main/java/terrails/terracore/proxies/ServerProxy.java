package terrails.terracore.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
    }

    @Override
    public void init(FMLInitializationEvent event) {
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
    }

    @Override
    public boolean isOP(EntityPlayer player) {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        for (String op : server.getPlayerList().getOppedPlayerNames()) {
            if (player.getName().trim().equalsIgnoreCase(op.trim())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public EntityPlayer getEntityPlayer() {
        return null;
    }

    @Override
    public boolean isGamePaused() {
        return false;
    }
}
