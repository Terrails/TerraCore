package terrails.terracore.base.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import terrails.terracore.TerraCore;

import javax.annotation.Nullable;

public class BlockBase extends Block {

    private String name;
    private String modid;

    public BlockBase(Material materialIn, String modid, String name) {
        super(materialIn);
        this.name = name;
        this.modid = modid;
        setRegistryName(name);
        setUnlocalizedName(name);
    }

    public void registerItemModel(ItemBlock itemBlock) {
        TerraCore.proxy.registerItemRenderer(modid, itemBlock, 0, name);
    }

    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    public BlockBase setHarvestLevel(int level) {
        super.setHarvestLevel("pickaxe", level);
        return this;
    }
}
