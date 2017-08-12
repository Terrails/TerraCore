package terrails.terracore.base;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import terrails.terracore.TerraCore;
import terrails.terracore.base.tool.HoeBase;

import javax.annotation.Nullable;

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

    public void registerItemModel() {
        TerraCore.proxy.registerItemRenderer(modid,this, 0, name);
    }

    @Override
    public ArmorBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
