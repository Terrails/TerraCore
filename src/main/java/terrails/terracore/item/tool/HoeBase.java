package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import terrails.terracore.registry.IUnlocalizedName;

@SuppressWarnings("ConstantConditions")
public class HoeBase extends ItemHoe implements IUnlocalizedName<Item> {

    public HoeBase(ToolMaterial material) {
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
