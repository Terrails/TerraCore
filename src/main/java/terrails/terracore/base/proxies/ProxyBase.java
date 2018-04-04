package terrails.terracore.base.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import terrails.terracore.base.IModEntry;

public abstract class ProxyBase {
    protected IModEntry modEntry;

    public abstract void preInit(FMLPreInitializationEvent event);
    public abstract void init(FMLInitializationEvent event);
    public abstract void postInit(FMLPostInitializationEvent event);

    public abstract boolean isOP(EntityPlayer player);
    public abstract EntityPlayer getEntityPlayer();
    public abstract boolean isGamePaused();

    public void setMod(IModEntry modEntry) {
        this.modEntry = modEntry;
    }
}
