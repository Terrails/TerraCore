package terrails.terracore.item.tool;

import com.google.common.collect.Sets;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import terrails.terracore.item.ItemBase;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class AxeBase extends ItemTool {

    protected String name;
    protected String modid;
    protected boolean displayHarvestLevel;
    protected ToolMaterial material;
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);

    public AxeBase(ToolMaterial material, String name) {
        super(material, EFFECTIVE_ON);
        this.material = material;
        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(name);
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
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        int harvestLevel = getHarvestLevel(stack, "axe", playerIn, null);

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
