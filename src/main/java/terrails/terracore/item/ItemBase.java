package terrails.terracore.item;

import net.minecraft.item.Item;
import terrails.terracore.api.forgeentry.IUnlocalizedName;

public class ItemBase extends Item implements IUnlocalizedName<Item> {

    private final String modId;

    public ItemBase(String modId) {
        this.modId = modId;
    }

    @Override
    public Item setUnlocalizedName(String name) {
        return super.setUnlocalizedName(modId + "." + name);
    }

    @Override
    public String getUnlocalizedName() {
        return super.getUnlocalizedName();
    }
}