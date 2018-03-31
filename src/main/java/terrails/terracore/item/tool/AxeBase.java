package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

@SuppressWarnings("ConstantConditions")
public class AxeBase extends ItemAxe {

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
    public Item setUnlocalizedName(String name) {
        return super.setUnlocalizedName(modId + "." + name);
    }
}
