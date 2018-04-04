package terrails.terracore.registry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import terrails.terracore.base.IModEntry;
import terrails.terracore.block.item.IItemBlock;
import terrails.terracore.block.item.ItemBlockBase;
import terrails.terracore.client.ICustomModel;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class RegistryEventHandler {

    private final IModEntry modEntry;

    public RegistryEventHandler(IModEntry modEntry) {
        this.modEntry = modEntry;
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        //  List<Block> blockRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.BLOCK), LoadingStage.REGISTER).getEntries();
        List<Block> blocks = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.BLOCK, modEntry), LoadingStage.REGISTER, Side.SERVER);
        event.getRegistry().registerAll(blocks.toArray(new Block[0]));
    }
    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerItems(RegistryEvent.Register<Item> event) {

        //  List<Item> itemRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.ITEM), LoadingStage.REGISTER).getEntries();
        List<Item> items = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.ITEM, modEntry), LoadingStage.REGISTER, Side.SERVER);
        event.getRegistry().registerAll(items.toArray(new Item[0]));

        //   List<Block> blockRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.BLOCK), LoadingStage.REGISTER).getEntries();
        List<Block> blocks = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.BLOCK, modEntry), LoadingStage.REGISTER, Side.SERVER);
        event.getRegistry().registerAll(blocks.stream()
                .filter(IItemBlock.class::isInstance)
                .map(IItemBlock.class::cast)
                .map(IItemBlock::getItemBlock)
                .toArray(ItemBlock[]::new));

        event.getRegistry().registerAll(blocks.stream()
                .filter(((Predicate<Block>) IItemBlock.class::isInstance).negate())
                .map(ItemBlockBase::new)
                .toArray(ItemBlock[]::new));
    }
    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerPotions(RegistryEvent.Register<Potion> event) {
        // List<Potion> potionRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.POTION), LoadingStage.REGISTER).getEntries();
        List<Potion> potions = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.POTION, modEntry), LoadingStage.REGISTER, Side.SERVER);
        event.getRegistry().registerAll(potions.toArray(new Potion[0]));
    }
    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerBiomes(RegistryEvent.Register<Biome> event) {
        // List<Biome> biomeRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.BIOME), LoadingStage.REGISTER).getEntries();
        List<Biome> biomes = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.BIOME, modEntry), LoadingStage.REGISTER, Side.SERVER);
        event.getRegistry().registerAll(biomes.toArray(new Biome[0]));
    }
    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        // List<SoundEvent> soundRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.SOUND_EVENT), LoadingStage.REGISTER).getEntries();
        List<SoundEvent> soundEvents = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.SOUND_EVENT, modEntry), LoadingStage.REGISTER, Side.SERVER);
        event.getRegistry().registerAll(soundEvents.toArray(new SoundEvent[0]));
    }
    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        // List<Enchantment> enchantmentRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.ENCHANTMENT), LoadingStage.REGISTER).getEntries();
        List<Enchantment> enchantments = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.ENCHANTMENT, modEntry), LoadingStage.REGISTER, Side.SERVER);
        event.getRegistry().registerAll(enchantments.toArray(new Enchantment[0]));
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerModels(ModelRegistryEvent event) {
        // List<Block> blockRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.BLOCK), LoadingStage.REGISTER).getEntries();
        List<Block> blocks = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.BLOCK, modEntry), LoadingStage.REGISTER, Side.SERVER);
        blocks.stream()
                .filter(ICustomModel.class::isInstance)
                .map(ICustomModel.class::cast)
                .forEach(ICustomModel::initModel);

        blocks.stream()
                .filter(((Predicate<Block>) ICustomModel.class::isInstance).negate())
                .forEach(RegistryEventHandler::registerModel);

        // List<Item> itemRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.ITEM), LoadingStage.REGISTER).getEntries();
        List<Item> items = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.ITEM, modEntry), LoadingStage.REGISTER, Side.SERVER);
        items.stream()
                .filter(ICustomModel.class::isInstance)
                .map(ICustomModel.class::cast)
                .forEach(ICustomModel::initModel);

        items.stream()
                .filter(((Predicate<Item>) ICustomModel.class::isInstance).negate())
                .forEach(RegistryEventHandler::registerModel);
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
