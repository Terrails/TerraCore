package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

@SuppressWarnings("ConstantConditions")
public class SwordBase extends ItemSword {

    protected String modId;

    public SwordBase(ToolMaterial material, String modId) {
        super(material);
        this.modId = modId;
        this.setCreativeTab(null);
    }

    @Override
    public Item setUnlocalizedName(String name) {
        return super.setUnlocalizedName(modId + "." + name);
    }
}
