package terrails.terracore.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import terrails.terracore.registry.IUnlocalizedName;

@SuppressWarnings("ConstantConditions")
public class AxeBase extends ItemAxe implements IUnlocalizedName<Item> {


    public AxeBase(ToolMaterial material, float attackDamage, float attackSpeed) {
        super(material, attackDamage, attackSpeed);
        this.setCreativeTab(null);
    }

    public AxeBase(ToolMaterial material) {
        this(material, material.getAttackDamage(), -3.2F);
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
