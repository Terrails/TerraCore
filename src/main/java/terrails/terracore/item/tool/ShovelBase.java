package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import terrails.terracore.registry.IUnlocalizedName;

@SuppressWarnings("ConstantConditions")
public class ShovelBase extends ItemSpade implements IUnlocalizedName<Item> {

    public ShovelBase(Item.ToolMaterial material) {
        super(material);
        setCreativeTab(null);
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
