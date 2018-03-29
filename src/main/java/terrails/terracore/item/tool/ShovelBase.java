package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

@SuppressWarnings("ConstantConditions")
public class ShovelBase extends ItemSpade {

    private final String modId;

    public ShovelBase(Item.ToolMaterial material, String modId) {
        super(material);
        this.modId = modId;
        setCreativeTab(null);
    }

    @Override
    public Item setUnlocalizedName(String name) {
        return super.setUnlocalizedName(modId + "." + name);
    }
}
