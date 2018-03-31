package terrails.terracore.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import terrails.terracore.base.IRegistryEntry;
import terrails.terracore.block.item.IItemBlock;
import terrails.terracore.block.item.ItemBlockBase;

import java.util.List;
import java.util.function.Predicate;

public class RegistryEventHandler {

    private final IRegistryEntry registryEntry;

    public RegistryEventHandler(IRegistryEntry registryEntry) {
        this.registryEntry = registryEntry;
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        if (registryEntry.getRegistry(SimpleRegistry.class, RegistryType.BLOCKS) != null) {
            List<Block> blockRegistry = this.registryEntry.getRegistry(SimpleRegistry.class, RegistryType.BLOCKS).getRegistryEntries();
            event.getRegistry().registerAll(blockRegistry.toArray(new Block[0]));
        }
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void registerItems(RegistryEvent.Register<Item> event) {
        if (registryEntry.getRegistry(SimpleRegistry.class, RegistryType.ITEMS) != null) {
            List<Item> itemRegistry = this.registryEntry.getRegistry(SimpleRegistry.class, RegistryType.ITEMS).getRegistryEntries();
            event.getRegistry().registerAll(itemRegistry.toArray(new Item[0]));
        }

        if (registryEntry.getRegistry(SimpleRegistry.class, RegistryType.BLOCKS) != null) {
            List<Block> blockRegistry = this.registryEntry.getRegistry(SimpleRegistry.class, RegistryType.BLOCKS).getRegistryEntries();
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
    }
}
