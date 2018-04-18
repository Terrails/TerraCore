package terrails.terracore.base.registry;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import terrails.terracore.base.IModEntry;
import terrails.terracore.registry.IItemBlock;
import terrails.terracore.block.item.ItemBlockBase;
import terrails.terracore.registry.IPostRegistry;

import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class RegistryEventHandler {

    private final IModEntry modEntry;

    public RegistryEventHandler(IModEntry modEntry) {
        this.modEntry = modEntry;
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        List<Block> blocks = getRegistry(RegistryType.BLOCK);
        event.getRegistry().registerAll(blocks.toArray(new Block[0]));

        blocks.stream().filter(IPostRegistry.class::isInstance).map(IPostRegistry.class::cast).forEach(IPostRegistry::initEntry);
    }
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        List<Item> items = getRegistry(RegistryType.ITEM);
        event.getRegistry().registerAll(items.toArray(new Item[0]));
        items.stream().filter(IPostRegistry.class::isInstance).map(IPostRegistry.class::cast).forEach(IPostRegistry::initEntry);

        List<Block> blocks = getRegistry(RegistryType.BLOCK);
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
        List<Potion> potions = getRegistry(RegistryType.POTION);
        event.getRegistry().registerAll(potions.toArray(new Potion[0]));
        potions.stream().filter(IPostRegistry.class::isInstance).map(IPostRegistry.class::cast).forEach(IPostRegistry::initEntry);
    }
    @SubscribeEvent
    public void registerBiomes(RegistryEvent.Register<Biome> event) {
        List<Biome> biomes = getRegistry(RegistryType.BIOME);
        event.getRegistry().registerAll(biomes.toArray(new Biome[0]));
        biomes.stream().filter(IPostRegistry.class::isInstance).map(IPostRegistry.class::cast).forEach(IPostRegistry::initEntry);
    }
    @SubscribeEvent
    public void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        List<SoundEvent> soundEvents = getRegistry(RegistryType.SOUND_EVENT);
        event.getRegistry().registerAll(soundEvents.toArray(new SoundEvent[0]));
        soundEvents.stream().filter(IPostRegistry.class::isInstance).map(IPostRegistry.class::cast).forEach(IPostRegistry::initEntry);
    }
    @SubscribeEvent
    public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        List<Enchantment> enchantments = getRegistry(RegistryType.ENCHANTMENT);
        event.getRegistry().registerAll(enchantments.toArray(new Enchantment[0]));
        enchantments.stream().filter(IPostRegistry.class::isInstance).map(IPostRegistry.class::cast).forEach(IPostRegistry::initEntry);
    }

    private RegistryList getRegistry(RegistryType type) {
        return this.modEntry.getRegistry(RegistryList.newInstance(type, this.modEntry));
    }
}
