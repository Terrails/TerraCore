package terrails.terracore.creativetab;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public abstract class CreativeTabsBase extends CreativeTabs {

    protected NonNullList<ItemStack> list = NonNullList.create();

    public CreativeTabsBase(String label) {
        super(label);
    }

    protected void add(ItemStack stack) {
        if (stack != null) {
            stack.getItem().getSubItems(this, this.list);
        }
    }
    protected void add(Item item, int meta) {
        if(item != null){
            add(new ItemStack(item, 1, meta));
        }
    }
    protected void add(Block block, int meta) {
        if (block != null) {
            add(new ItemStack(block, 1, meta));
        }
    }
    protected void add(Item item) {
        if(item != null){
            item.getSubItems(this, this.list);
        }
    }
    protected void add(Block block) {
        if(block != null){
            block.getSubBlocks(this, this.list);
        }
    }
}
