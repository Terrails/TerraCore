package terrails.terracore.base.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import terrails.terracore.TerraCore;

import javax.annotation.Nullable;

public class ItemBase extends Item {

    protected String name;
    protected String modid;

    public ItemBase(String modid, String name) {
        this.name = name;
        this.modid = modid;
        setRegistryName(name);
        setUnlocalizedName(name);
    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() {
        return null;
    }

    public void registerItemModel() {
        TerraCore.proxy.registerItemRenderer(modid,this, 0, name);
    }

    @Override
    public ItemBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
