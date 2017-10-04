package terrails.terracore.item.tool;

import net.minecraft.item.ItemSword;

public class SwordBase extends ItemSword {

    protected String name;
    protected String modid;

    public SwordBase(String modid, ToolMaterial material, String name) {
        super(material);
        this.name = name;
        this.modid = modid;
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(null);
    }
}
