package terrails.terracore.block.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import java.util.Objects;

public class ItemBlockBase extends ItemBlock {

    public ItemBlockBase(Block block) {
        super(block);
        this.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }
}
