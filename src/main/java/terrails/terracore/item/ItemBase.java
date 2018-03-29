package terrails.terracore.item;

import net.minecraft.item.Item;

public class ItemBase extends Item {

    private final String modId;

    public ItemBase(String modId) {
        this.modId = modId;
    }

    @Override
    public Item setUnlocalizedName(String name) {
        return super.setUnlocalizedName(modId + "." + name);
    }

}