package terrails.terracore.helper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryHelper {

    public static void registerWorldGenerator(IWorldGenerator generator, int modGenerationWeight){
        GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
    }

    public static void registerRender(String modid, Object item, int meta, String fileName) {
        if (item instanceof Item) {
            ModelLoader.setCustomModelResourceLocation((Item) item, meta, new ModelResourceLocation(new ResourceLocation(modid, fileName), "inventory"));
        }
        if (item instanceof Block) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock((Block) item), meta, new ModelResourceLocation(new ResourceLocation(modid, fileName), "inventory"));
        }
    }
    public static void registerModelBakery(String modid, Object item, String resourcePath) {

        if (item instanceof Item) {
            ModelBakery.registerItemVariants((Item) item, new ResourceLocation(modid, resourcePath));
        }
        if (item instanceof Block) {
            ModelBakery.registerItemVariants(Item.getItemFromBlock((Block) item), new ResourceLocation(modid, resourcePath));
        }
    }
}
