package terrails.terracore.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMetadataBase extends Item {

    private int valuesLenght;
    private String modid;

    public ItemMetadataBase(String name, int valuesLenght) {
        setUnlocalizedName(name);
     //   setRegistryName(new ResourceLocation(modid, name));
        setRegistryName(name);
        setHasSubtypes(true);
        this.valuesLenght = valuesLenght;
    }

    public String getUnlocalizedName(ItemStack stack) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (int i = 0; i < valuesLenght; ++i)
        {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

    public void setModid(String modid) {
        this.modid = modid;
    }
}
