package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import terrails.terracore.api.forgeentry.IUnlocalizedName;

@SuppressWarnings("ConstantConditions")
public class AxeBase extends ItemAxe implements IUnlocalizedName<Item> {

    private final String modId;

    public AxeBase(ToolMaterial material, String modId, float attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed);
        this.modId = modId;
        this.setCreativeTab(null);
    }

    public AxeBase(ToolMaterial material, String modId) {
        this(material, modId, material.getAttackDamage(), -3.2F);
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
