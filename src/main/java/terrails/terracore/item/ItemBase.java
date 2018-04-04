package terrails.terracore.item;

import net.minecraft.item.Item;
import terrails.terracore.registry.IUnlocalizedName;

public class ItemBase extends Item implements IUnlocalizedName<Item> {

    @Override
    public Item setEntryName(String name) {
        return this.setUnlocalizedName(name);
    }

    @Override
    public String getEntryName() {
        return this.getUnlocalizedName();
    }
}