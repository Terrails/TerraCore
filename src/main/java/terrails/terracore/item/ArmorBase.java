package terrails.terracore.item;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

@SuppressWarnings("ConstantConditions")
public class ArmorBase extends ItemArmor {

    private final String modId;

    public ArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String modId) {
        super(material, 0, slot);
        this.modId = modId;
        setCreativeTab(null);
    }

    @Override
    public Item setUnlocalizedName(String name) {
        return super.setUnlocalizedName(modId + "." + name);
    }
}
