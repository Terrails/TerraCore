package terrails.terracore.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {

    void preInit(FMLPreInitializationEvent event);
    void init(FMLInitializationEvent event);
    void postInit(FMLPostInitializationEvent event);

    boolean isOP(EntityPlayer player);
    EntityPlayer getEntityPlayer();
    boolean isGamePaused();
}
