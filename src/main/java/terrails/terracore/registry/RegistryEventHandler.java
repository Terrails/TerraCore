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
import terrails.terracore.base.IRegistryEntry;
import terrails.terracore.block.item.IItemBlock;
import terrails.terracore.block.item.ItemBlockBase;
import terrails.terracore.client.ICustomModel;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class RegistryEventHandler {

    private final IRegistryEntry registryEntry;

    public RegistryEventHandler(IRegistryEntry registryEntry) {
        this.registryEntry = registryEntry;
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        List<Block> blockRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.BLOCK), LoadingStage.REGISTER).getEntries();
        event.getRegistry().registerAll(blockRegistry.toArray(new Block[0]));
        //      List<Block> blockRegistry = this.registryEntry.getRegistry(RegistryType.BLOCK).getEntries();
     //   event.getRegistry().registerAll(blockRegistry.toArray(new Block[0]));
      //  if (registryEntry.getRegistry(SimpleRegistry.class, RegistryType.BLOCK) != null) {
    //        List<Block> blockRegistry = this.registryEntry.getRegistry(SimpleRegistry.class, RegistryType.BLOCK).getEntries();
    //        event.getRegistry().registerAll(blockRegistry.toArray(new Block[0]));
    //    }
    }
    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerItems(RegistryEvent.Register<Item> event) {
    //    if (registryEntry.getRegistry(SimpleRegistry.class, RegistryType.ITEM) != null) {
    //        List<Item> itemRegistry = this.registryEntry.getRegistry(SimpleRegistry.class, RegistryType.ITEM).getEntries();
   //         event.getRegistry().registerAll(itemRegistry.toArray(new Item[0]));
   //     }

        List<Item> itemRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.ITEM), LoadingStage.REGISTER).getEntries();//this.registryEntry.getRegistry(RegistryType.ITEM).getEntries();
        event.getRegistry().registerAll(itemRegistry.toArray(new Item[0]));

        List<Block> blockRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.BLOCK), LoadingStage.REGISTER).getEntries();//this.registryEntry.getRegistry(RegistryType.BLOCK).getEntries();
        event.getRegistry().registerAll(blockRegistry.stream()
                .filter(IItemBlock.class::isInstance)
                .map(IItemBlock.class::cast)
                .map(IItemBlock::getItemBlock)
                .toArray(ItemBlock[]::new));

        event.getRegistry().registerAll(blockRegistry.stream()
                .filter(((Predicate<Block>) IItemBlock.class::isInstance).negate())
                .map(ItemBlockBase::new)
                .toArray(ItemBlock[]::new));
        /*
        if (registryEntry.getRegistry(SimpleRegistry.class, RegistryType.BLOCK) != null) {
            List<Block> blockRegistry = this.registryEntry.getRegistry(SimpleRegistry.class, RegistryType.BLOCK).getEntries();
            event.getRegistry().registerAll(blockRegistry.stream()
                    .filter(IItemBlock.class::isInstance)
                    .map(IItemBlock.class::cast)
                    .map(IItemBlock::getItemBlock)
                    .toArray(ItemBlock[]::new));

            event.getRegistry().registerAll(blockRegistry.stream()
                    .filter(((Predicate<Block>) IItemBlock.class::isInstance).negate())
                    .map(ItemBlockBase::new)
                    .toArray(ItemBlock[]::new));
        }
        */
    }
    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerPotions(RegistryEvent.Register<Potion> event) {
        List<Potion> potionRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.POTION), LoadingStage.REGISTER).getEntries();//this.registryEntry.getRegistry(RegistryType.POTION).getEntries();
        event.getRegistry().registerAll(potionRegistry.toArray(new Potion[0]));
    //    if (registryEntry.getRegistry(SimpleRegistry.class, RegistryType.POTION) != null) {
    //        List<Potion> potionRegistry = this.registryEntry.getRegistry(SimpleRegistry.class, RegistryType.POTION).getEntries();
    //        event.getRegistry().registerAll(potionRegistry.toArray(new Potion[0]));
    //    }
    }
    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerBiomes(RegistryEvent.Register<Biome> event) {
        List<Biome> biomeRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.BIOME), LoadingStage.REGISTER).getEntries();//this.registryEntry.getRegistry(RegistryType.BIOME).getEntries();
        event.getRegistry().registerAll(biomeRegistry.toArray(new Biome[0]));
     //   if (registryEntry.getRegistry(SimpleRegistry.class, RegistryType.BIOME) != null) {
     //       List<Biome> biomeRegistry = this.registryEntry.getRegistry(SimpleRegistry.class, RegistryType.BIOME).getEntries();
     //       event.getRegistry().registerAll(biomeRegistry.toArray(new Biome[0]));
     //   }
    }
    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        List<SoundEvent> soundRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.SOUND_EVENT), LoadingStage.REGISTER).getEntries();//this.registryEntry.getRegistry(RegistryType.SOUND_EVENT).getEntries();
        event.getRegistry().registerAll(soundRegistry.toArray(new SoundEvent[0]));
      //  if (registryEntry.getRegistry(SimpleRegistry.class, RegistryType.SOUND_EVENT) != null) {
     //       List<SoundEvent> soundRegistry = this.registryEntry.getRegistry(SimpleRegistry.class, RegistryType.SOUND_EVENT).getEntries();
    //        event.getRegistry().registerAll(soundRegistry.toArray(new SoundEvent[0]));
     //   }
    }
    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        List<Enchantment> enchantmentRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.ENCHANTMENT), LoadingStage.REGISTER).getEntries();//this.registryEntry.getRegistry(RegistryType.ENCHANTMENT).getEntries();
        event.getRegistry().registerAll(enchantmentRegistry.toArray(new Enchantment[0]));
     //   if (registryEntry.getRegistry(SimpleRegistry.class, RegistryType.ENCHANTMENT) != null) {
    //        List<Enchantment> enchantmentRegistry = this.registryEntry.getRegistry(SimpleRegistry.class, RegistryType.ENCHANTMENT).getEntries();
     //       event.getRegistry().registerAll(enchantmentRegistry.toArray(new Enchantment[0]));
     //   }
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerModels(ModelRegistryEvent event) {
     //   if (registryEntry.getRegistry(SimpleRegistry.class, RegistryType.BLOCK) != null) {
      //      List<Block> blockRegistry = this.registryEntry.getRegistry(SimpleRegistry.class, RegistryType.BLOCK).getEntries();
        List<Block> blockRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.BLOCK), LoadingStage.REGISTER).getEntries();//this.registryEntry.getRegistry(RegistryType.BLOCK).getEntries();
            blockRegistry.stream()
                    .filter(ICustomModel.class::isInstance)
                    .map(ICustomModel.class::cast)
                    .forEach(ICustomModel::initModel);

            blockRegistry.stream()
                    .filter(((Predicate<Block>) ICustomModel.class::isInstance).negate())
                    .forEach(RegistryEventHandler::registerModel);
      //  }

      //  if (registryEntry.getRegistry(SimpleRegistry.class, RegistryType.ITEM) != null) {
     //       List<Item> itemRegistry = this.registryEntry.getRegistry(SimpleRegistry.class, RegistryType.ITEM).getEntries();
        List<Item> itemRegistry = registryEntry.register(registryEntry.getRegistry(RegistryType.ITEM), LoadingStage.REGISTER).getEntries();//this.registryEntry.getRegistry(RegistryType.ITEM).getEntries();
            itemRegistry.stream()
                    .filter(ICustomModel.class::isInstance)
                    .map(ICustomModel.class::cast)
                    .forEach(ICustomModel::initModel);

            itemRegistry.stream()
                    .filter(((Predicate<Item>) ICustomModel.class::isInstance).negate())
                    .forEach(RegistryEventHandler::registerModel);
     //   }
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
