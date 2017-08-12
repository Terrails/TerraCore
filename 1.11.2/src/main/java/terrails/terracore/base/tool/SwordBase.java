package terrails.terracore.base.tool;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import terrails.terracore.TerraCore;

import javax.annotation.Nullable;
import java.util.List;

public class SwordBase extends ItemSword {

    protected String name;
    protected String modid;

    public SwordBase(String modid, ToolMaterial material, String name) {
        super(material);
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
    public SwordBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
