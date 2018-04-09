package terrails.terracore.item;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCraftingDurabilityBase extends ItemBase {

    protected String information;
    protected boolean displayDurability;

    private final int maxDamage;

    public ItemCraftingDurabilityBase(int damage) {
        this.setMaxDamage(damage - 1);
        this.setMaxStackSize(1);
        this.setContainerItem(this);
        this.setNoRepair();
        this.maxDamage = damage;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        ItemStack leStack = stack.copy();
        leStack.setItemDamage(leStack.getItemDamage() + 1);
        return setStackSize(leStack, 1);
    }

    public static ItemStack setStackSize(ItemStack stack, int size) {
        if (size <= 0) {
            if (stack != null && !stack.isEmpty()) {
                return stack.getItem().getContainerItem(stack);
            } else {
                return ItemStack.EMPTY;
            }
        }
        stack.setCount(size);
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int currentDamage = maxDamage - getDamage(stack);
        if (information != null) {
            tooltip.add(information);
        }
        if (displayDurability && currentDamage != 0) {
            tooltip.add(ChatFormatting.BLUE + "Durability: " + ChatFormatting.GOLD + currentDamage + "/" + maxDamage);
        }
    }
}