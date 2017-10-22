package terrails.terracore.item.tool;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ShovelBase extends ItemSpade {

    protected String name;
    protected String modid;
    protected boolean displayHarvestLevel;

    public ShovelBase(Item.ToolMaterial material, String name) {
        super(material);
        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(null);
    }

    public ShovelBase(ToolMaterial material, String name, boolean displayLevel){
        this(material, name);
        this.displayHarvestLevel = displayLevel;
    }

    public void setModid(String modid) {
        this.modid = modid;
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
