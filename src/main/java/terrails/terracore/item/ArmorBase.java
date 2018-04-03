package terrails.terracore.item;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import terrails.terracore.api.forgeentry.IUnlocalizedName;

@SuppressWarnings("ConstantConditions")
public class ArmorBase extends ItemArmor implements IUnlocalizedName<Item> {

    private final String modId;

    public ArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String modId) {
        super(material, 0, slot);
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
