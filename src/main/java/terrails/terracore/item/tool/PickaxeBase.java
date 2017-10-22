package terrails.terracore.item.tool;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PickaxeBase extends ItemPickaxe {

    protected String name;
    protected String modid;
    protected boolean displayHarvestLevel;

    public PickaxeBase(ToolMaterial material, String name) {
        super(material);
        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(null);
    }

    public PickaxeBase(ToolMaterial material, String name, boolean displayLevel){
        this(material, name);
        this.displayHarvestLevel = displayLevel;
    }

    public void setModid(String modid) {
        this.modid = modid;
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
