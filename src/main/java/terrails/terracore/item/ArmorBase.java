package terrails.terracore.item;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import terrails.terracore.registry.IUnlocalizedName;

@SuppressWarnings("ConstantConditions")
public class ArmorBase extends ItemArmor implements IUnlocalizedName<Item> {

    public ArmorBase(ArmorMaterial material, EntityEquipmentSlot slot) {
        super(material, 0, slot);
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
