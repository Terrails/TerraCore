package terrails.terracore.base.tool;

import com.google.common.collect.Sets;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import terrails.terracore.TerraCore;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class AxeBase extends ItemTool {

    protected String name;
    protected String modid;
    protected boolean infoLevel;

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE});

    public AxeBase(String modid, ToolMaterial material, String name) {
        super(material, EFFECTIVE_ON);
        this.name = name;
        this.modid = modid;
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(null);
    }

    public AxeBase(String modid, ToolMaterial material, String name, boolean displayLevel){
        this(modid, material, name);
        this.infoLevel = displayLevel;
    }

    public void registerItemModel() {
        TerraCore.proxy.registerItemRenderer(modid,this, 0, name);
    }

    @Override
    public AxeBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }


    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        int harvestLevel = getHarvestLevel(stack, "axe", playerIn, Block.getBlockFromItem(stack.getItem()).getDefaultState());

        if(infoLevel){
            if (harvestLevel == 0) {
                tooltip.add(ChatFormatting.BLUE + "Mining Level: " + ChatFormatting.GOLD + "Wood (" + harvestLevel + ")");
            }
            else if (harvestLevel == 1) {
                tooltip.add(ChatFormatting.BLUE + "Mining Level: " + ChatFormatting.GOLD + "Stone (" + harvestLevel + ")");
            }
            else if (harvestLevel == 2) {
                tooltip.add(ChatFormatting.BLUE + "Mining Level: " + ChatFormatting.GOLD + "Iron (" + harvestLevel + ")");
            }
            else if (harvestLevel == 3) {
                tooltip.add(ChatFormatting.BLUE + "Mining Level: " + ChatFormatting.GOLD + "Diamond (" + harvestLevel + ")");
            }
            else if (harvestLevel > 3) {
                tooltip.add(ChatFormatting.BLUE + "Mining Level: " + ChatFormatting.GOLD + ">Diamond (" + harvestLevel + ")");
            }
        }
    }
}
