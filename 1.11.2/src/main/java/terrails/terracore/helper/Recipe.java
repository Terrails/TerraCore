package terrails.terracore.helper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipe {

    public static ShapedOreRecipe ShapedOre(Item output, Object... recipe){
        return new ShapedOreRecipe(new ItemStack(output), recipe);
    }
    public static ShapedOreRecipe ShapedOre(Block output, Object... recipe){
        return new ShapedOreRecipe(new ItemStack(output), recipe);
    }
    public static ShapedOreRecipe ShapedOre(ItemStack output, Object... recipe){
        return new ShapedOreRecipe(output, recipe);
    }
    public static ShapelessOreRecipe ShapelessOre(Item input, Object... output){
        return new ShapelessOreRecipe(new ItemStack(input), output);
    }
    public static ShapelessOreRecipe ShapelessOre(Block input, Object... output){
        return new ShapelessOreRecipe(new ItemStack(input), output);
    }
    public static ShapelessOreRecipe ShapelessOre(ItemStack input, Object... output){
        return new ShapelessOreRecipe(input, output);
    }
    public static void addSmelting(boolean condition, Item input, ItemStack output, float xp){
        if(condition) {
            GameRegistry.addSmelting(input, output, xp);
        }
    }
    public static void addSmelting(boolean condition, Block input, ItemStack output, float xp){
        if(condition) {
            GameRegistry.addSmelting(input, output, xp);
        }
    }
    public static void addSmelting(boolean condition, ItemStack input, ItemStack output, float xp){
        if(condition) {
            GameRegistry.addSmelting(input, output, xp);
        }
    }
    public static void addSmelting(Block input, ItemStack output, float xp) {
        FurnaceRecipes.instance().addSmeltingRecipeForBlock(input, output, xp);
    }
    public static void addSmelting(Item input, ItemStack output, float xp) {
        FurnaceRecipes.instance().addSmelting(input, output, xp);
    }
    public static void addSmelting(ItemStack input, ItemStack output, float xp) {
        FurnaceRecipes.instance().addSmeltingRecipe(input, output, xp);
    }
}
