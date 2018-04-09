package terrails.terracore.base.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import terrails.terracore.base.registry.LoadingStage;

public class ServerProxy extends ProxyBase {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        this.modEntry.getProxyRegistry().registerProxyEntries(Side.SERVER, LoadingStage.PRE_INIT);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        this.modEntry.getProxyRegistry().registerProxyEntries(Side.SERVER, LoadingStage.INIT);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        this.modEntry.getProxyRegistry().registerProxyEntries(Side.SERVER, LoadingStage.POST_INIT);
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
