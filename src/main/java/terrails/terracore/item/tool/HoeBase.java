package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;

@SuppressWarnings("ConstantConditions")
public class HoeBase extends ItemHoe {

    private final String modId;

    public HoeBase(ToolMaterial material, String modId) {
        super(material);
        this.modId = modId;
        setCreativeTab(null);
    }

    @Override
    public Item setUnlocalizedName(String name) {
        return super.setUnlocalizedName(modId + "." + name);
    }
}
