package terrails.terracore.item;

import net.minecraft.item.Item;

public class ItemBase extends Item {

    protected String name;
    protected String modid;

    public ItemBase(String name) {
        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(name);
    }

    public void setModid(String modid) {
        this.modid = modid;
    }
}
