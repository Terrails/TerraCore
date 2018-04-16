package terrails.terracore.base.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import terrails.terracore.base.registry.LoadingStage;

public class ClientProxy extends ProxyBase {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        this.modEntry.getProxyRegistry().registerProxyEntries(Side.CLIENT, LoadingStage.PRE_INIT);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        this.modEntry.getProxyRegistry().registerProxyEntries(Side.CLIENT, LoadingStage.INIT);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        this.modEntry.getProxyRegistry().registerProxyEntries(Side.CLIENT, LoadingStage.POST_INIT);
    }

    @Override
    public boolean isOP(EntityPlayer player) {
        return false;
    }

    @Override
    public EntityPlayer getEntityPlayer() {
        return Minecraft.getMinecraft().player;
    }

    @Override
    public boolean isGamePaused() {
        return Minecraft.getMinecraft().isGamePaused();
    }

}
