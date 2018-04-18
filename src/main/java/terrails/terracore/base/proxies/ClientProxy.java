package terrails.terracore.base.proxies;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import terrails.terracore.base.registry.LoadingStage;
import terrails.terracore.base.registry.RegistryList;
import terrails.terracore.base.registry.RegistryType;
import terrails.terracore.registry.client.ICustomModel;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

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

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        List<Block> blocks = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.BLOCK, this.modEntry));
        blocks.stream()
                .filter(ICustomModel.class::isInstance)
                .map(ICustomModel.class::cast)
                .forEach(ICustomModel::initModel);

        blocks.stream()
                .filter(((Predicate<Block>) ICustomModel.class::isInstance).negate())
                .forEach(ClientProxy::registerModel);

        List<Item> items = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.ITEM, this.modEntry));
        items.stream()
                .filter(ICustomModel.class::isInstance)
                .map(ICustomModel.class::cast)
                .forEach(ICustomModel::initModel);

        items.stream()
                .filter(((Predicate<Item>) ICustomModel.class::isInstance).negate())
                .forEach(ClientProxy::registerModel);
    }
    private static void registerModel(Block block) {
        ModelResourceLocation resourceLocation = new ModelResourceLocation(Objects.requireNonNull(block.getRegistryName()), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, resourceLocation);
    }
    private static void registerModel(Item item) {
        ModelResourceLocation resourceLocation = new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, resourceLocation);
    }
}
