package terrails.terracore.base.block;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nullable;

public class BlockOreBase extends BlockBase {

    public BlockOreBase(String modid, String name) {
        super(Material.ROCK, modid, name);
    }

    @Override
    public BlockOreBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    public BlockOreBase setHarvestLevel(int level) {
        super.setHarvestLevel("pickaxe", level);
        return this;
    }

    public BlockOreBase setLightLevel(float value) {
        super.setLightLevel(value);
        return this;
    }
}
