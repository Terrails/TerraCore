package terrails.terracore.item.tool;

import net.minecraft.item.ItemPickaxe;

public class PickaxeBase extends ItemPickaxe {

    protected String name;
    protected String modid;
    protected boolean infoLevel;

    public PickaxeBase(String modid, ToolMaterial material, String name) {
        super(material);
        this.name = name;
        this.modid = modid;
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(null);
    }

    public PickaxeBase(String modid, ToolMaterial material, String name, boolean displayLevel){
        this(modid, material, name);
        this.infoLevel = displayLevel;
    }

/*
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        int harvestLevel = getHarvestLevel(stack, "pickaxe", playerIn, Block.getBlockFromItem(stack.getItem()).getDefaultState());

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
    */
}
