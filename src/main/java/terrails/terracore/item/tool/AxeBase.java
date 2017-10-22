package terrails.terracore.item.tool;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import terrails.terracore.item.ItemBase;

import javax.annotation.Nullable;
import java.util.List;

public class AxeBase extends ItemBase {

    protected String name;
    protected String modid;
    protected boolean displayHarvestLevel;
    protected ToolMaterial material;

    public AxeBase(ToolMaterial material, String name) {
        super(name);
        this.material = material;
        this.name = name;
        setCreativeTab(null);
    }

    public AxeBase(ToolMaterial material, String name, boolean displayLevel){
        this(material, name);
        this.displayHarvestLevel = displayLevel;
    }

    public void setModid(String modid) {
        this.modid = modid;
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getStrVsBlock(stack, state) : this.material.getEfficiencyOnProperMaterial();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int harvestLevel = getHarvestLevel(stack, "axe", null, null);

        if (displayHarvestLevel && harvestLevel >= 0) {
            switch (harvestLevel) {
                case 0: tooltip.add(ChatFormatting.BLUE + "Mining Level: " + ChatFormatting.GOLD + "Wood (" + harvestLevel + ")");
                case 1: tooltip.add(ChatFormatting.BLUE + "Mining Level: " + ChatFormatting.GOLD + "Stone (" + harvestLevel + ")");
                case 2: tooltip.add(ChatFormatting.BLUE + "Mining Level: " + ChatFormatting.GOLD + "Iron (" + harvestLevel + ")");
                case 3: tooltip.add(ChatFormatting.BLUE + "Mining Level: " + ChatFormatting.GOLD + "Diamond (" + harvestLevel + ")");
                default: tooltip.add(ChatFormatting.BLUE + "Mining Level: " + ChatFormatting.GOLD + ">Diamond (" + harvestLevel + ")");
            }
        }
    }
}
