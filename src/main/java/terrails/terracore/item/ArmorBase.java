package terrails.terracore.item;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorBase extends ItemArmor {

    protected String name;
    protected String modid;

    public ArmorBase(String modid, ArmorMaterial material, EntityEquipmentSlot slot, String name) {
        super(material, 0, slot);
        this.name = name;
        this.modid = modid;
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(null);
    }
}
