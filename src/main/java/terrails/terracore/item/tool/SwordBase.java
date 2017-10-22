package terrails.terracore.item.tool;

import net.minecraft.item.ItemSword;

public class SwordBase extends ItemSword {

    protected String name;
    protected String modid;

    public SwordBase(ToolMaterial material, String name) {
        super(material);
        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(null);
    }

    public void setModid(String modid) {
        this.modid = modid;
    }
}
