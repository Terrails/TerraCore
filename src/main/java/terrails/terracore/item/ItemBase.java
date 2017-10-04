package terrails.terracore.item;

import net.minecraft.item.Item;

public class ItemBase extends Item {

    protected String name;
    protected String modid;

    public ItemBase(String modid, String name) {
        this.name = name;
        this.modid = modid;
        setRegistryName(name);
        setUnlocalizedName(name);
    }
}
