package terrails.terracore.item;

import net.minecraft.item.Item;
import terrails.terracore.api.forgeentry.IUnlocalizedName;

public class ItemBase extends Item implements IUnlocalizedName<Item> {

    private final String modId;

    public ItemBase(String modId) {
        this.modId = modId;
    }

    @Override
    public Item setEntryName(String name) {
        return this.setUnlocalizedName(modId + "." + name);
    }

    @Override
    public String getEntryName() {
        return this.getUnlocalizedName();
    }
}