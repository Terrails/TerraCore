package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import terrails.terracore.api.forgeentry.IUnlocalizedName;

@SuppressWarnings("ConstantConditions")
public class HoeBase extends ItemHoe implements IUnlocalizedName<Item> {

    private final String modId;

    public HoeBase(ToolMaterial material, String modId) {
        super(material);
        this.modId = modId;
        setCreativeTab(null);
    }

    @Override
    public Item setEntryName(String name) {
        return this.setUnlocalizedName(modId + "." + name);
    }

    @Override
    public String getEntryName() {
        return this.getUnlocalizedName();
    }
}
