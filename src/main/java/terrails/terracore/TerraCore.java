package terrails.terracore;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import terrails.terracore.base.MainModClass;

@Mod(modid = TerraCore.MOD_ID,
        name = TerraCore.MOD_NAME,
        version = TerraCore.VERSION)
public class TerraCore extends MainModClass<TerraCore> {

    public static final String MOD_ID = "terracore";
    public static final String MOD_NAME = "TerraCore";
    public static final String VERSION = "@VERSION@";

    public TerraCore() {
        super(MOD_ID, MOD_NAME, VERSION);
        this.useRegistry = false;
    }

    @Mod.EventHandler
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Mod.EventHandler
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Mod.EventHandler
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public TerraCore getInstance() {
        return this;
    }
}

