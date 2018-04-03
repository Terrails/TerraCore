package terrails.terracore.block.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import terrails.terracore.api.forgeentry.IUnlocalizedName;

import java.util.Objects;

public class ItemBlockBase extends ItemBlock implements IUnlocalizedName<Item> {

    public ItemBlockBase(Block block) {
        super(block);
        this.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
    }
}
