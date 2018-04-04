package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import terrails.terracore.registry.IUnlocalizedName;

@SuppressWarnings("ConstantConditions")
public class PickaxeBase extends ItemPickaxe implements IUnlocalizedName<Item> {

    public PickaxeBase(ToolMaterial material) {
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
