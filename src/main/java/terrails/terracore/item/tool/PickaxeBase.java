package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

@SuppressWarnings("ConstantConditions")
public class PickaxeBase extends ItemPickaxe {

    private final String modId;

    public PickaxeBase(ToolMaterial material, String modId) {
        super(material);
        this.modId = modId;
        this.setCreativeTab(null);
    }

    @Override
    public Item setUnlocalizedName(String name) {
        return super.setUnlocalizedName(modId + "." + name);
    }
}
