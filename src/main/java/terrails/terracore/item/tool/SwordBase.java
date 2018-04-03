package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import terrails.terracore.api.forgeentry.IUnlocalizedName;

@SuppressWarnings("ConstantConditions")
public class SwordBase extends ItemSword implements IUnlocalizedName<Item> {

    protected String modId;

    public SwordBase(ToolMaterial material, String modId) {
        super(material);
        this.modId = modId;
        this.setCreativeTab(null);
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
