package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import terrails.terracore.registry.IUnlocalizedName;

@SuppressWarnings("ConstantConditions")
public class SwordBase extends ItemSword implements IUnlocalizedName<Item> {

    public SwordBase(ToolMaterial material) {
        super(material);
        this.setCreativeTab(null);
    }

    @Override
    public Item setEntryName(String name) {
        return this.setUnlocalizedName(name);
    }

    @Override
    public String getEntryName() {
        return this.getUnlocalizedName();
    }
}
