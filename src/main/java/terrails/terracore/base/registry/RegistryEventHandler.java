package terrails.terracore.base.registry;

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
import terrails.terracore.base.IModEntry;
import terrails.terracore.registry.IItemBlock;
import terrails.terracore.block.item.ItemBlockBase;
import terrails.terracore.registry.client.ICustomModel;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class RegistryEventHandler {

    private final IModEntry modEntry;

    public RegistryEventHandler(IModEntry modEntry) {
        this.modEntry = modEntry;
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        List<Block> blocks = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.BLOCK, modEntry));
        event.getRegistry().registerAll(blocks.toArray(new Block[0]));
    }
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        List<Item> items = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.ITEM, modEntry));
        event.getRegistry().registerAll(items.toArray(new Item[0]));

        List<Block> blocks = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.BLOCK, modEntry));
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
    public void registerPotions(RegistryEvent.Register<Potion> event) {
        List<Potion> potions = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.POTION, modEntry));
        event.getRegistry().registerAll(potions.toArray(new Potion[0]));
    }
    @SubscribeEvent
    public void registerBiomes(RegistryEvent.Register<Biome> event) {
        List<Biome> biomes = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.BIOME, modEntry));
        event.getRegistry().registerAll(biomes.toArray(new Biome[0]));
    }
    @SubscribeEvent
    public void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        List<SoundEvent> soundEvents = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.SOUND_EVENT, modEntry));
        event.getRegistry().registerAll(soundEvents.toArray(new SoundEvent[0]));
    }
    @SubscribeEvent
    public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        List<Enchantment> enchantments = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.ENCHANTMENT, modEntry));
        event.getRegistry().registerAll(enchantments.toArray(new Enchantment[0]));
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerModels(ModelRegistryEvent event) {
        List<Block> blocks = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.BLOCK, modEntry));
        blocks.stream()
                .filter(ICustomModel.class::isInstance)
                .map(ICustomModel.class::cast)
                .forEach(ICustomModel::initModel);

        blocks.stream()
                .filter(((Predicate<Block>) ICustomModel.class::isInstance).negate())
                .forEach(RegistryEventHandler::registerModel);

        List<Item> items = this.modEntry.getRegistry(RegistryList.newInstance(RegistryType.ITEM, modEntry));
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
