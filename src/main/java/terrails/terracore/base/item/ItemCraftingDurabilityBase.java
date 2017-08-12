package terrails.terracore.base.item;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCraftingDurabilityBase extends ItemBase{

    private String information;
    private boolean infoDamage;
    private int maxDamage;

    public ItemCraftingDurabilityBase(String modid, String name, int damage) {
        super(modid, name);
        this.setMaxDamage(damage-1);
        this.setMaxStackSize(1);
        this.setContainerItem(this);
        this.setNoRepair();
        this.maxDamage = damage;
    }
    public ItemCraftingDurabilityBase(String modid, String name, int damage, String information){
        this(modid, name, damage);
        this.information = information;
    }
    public ItemCraftingDurabilityBase(String modid, String name, int damage, boolean displayDurability){
        this(modid, name, damage);
        this.infoDamage = displayDurability;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        ItemStack leStack = stack.copy();
        leStack.setItemDamage(leStack.getItemDamage()+1);
        return setStackSize(leStack, 1);
    }

    public static ItemStack setStackSize(ItemStack stack, int size){
        if(size <= 0){
            if (stack != null && !stack.isEmpty()){
                return stack.getItem().getContainerItem(stack);
            }
            else{
                return ItemStack.EMPTY;
            }
        }
        stack.setCount(size);
        return stack;
    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() {
        return null;
    }
/*
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        int currentDamage = maxDamage - getDamage(stack);
        if(information != null) {
            tooltip.add(information);
        }
        if(infoDamage && currentDamage != 0){
            tooltip.add(ChatFormatting.BLUE + "Durability: " + ChatFormatting.GOLD + currentDamage + "/" + maxDamage);
        }
    }*/
}