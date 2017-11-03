package terrails.terracore;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {

    public static final String MOD_ID = "terracore";
    public static final String MOD_NAME = "TerraCore";
    public static final String MOD_VERSION = "2.1.3";
    public static final String MC_VERSION = "[1.10],[1.10.1],[1.10.2],[1.11],[1.11.1],[1.11.2],[1.12],[1.12.1],[1.12.2]";

    public static final String CLIENT_PROXY = "terrails.terracore.proxies.ClientProxy";
    public static final String SERVER_PROXY = "terrails.terracore.proxies.ClientProxy";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static Logger getLogger(String name) {
        return LogManager.getLogger(name);
    }
}
