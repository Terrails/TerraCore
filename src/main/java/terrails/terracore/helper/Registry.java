package terrails.terracore.helper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import terrails.terracore.base.ArmorBase;
import terrails.terracore.base.block.BlockBase;
import terrails.terracore.base.item.ItemBase;
import terrails.terracore.base.tool.*;

public class Registry {

    public static void register(Object object) {
        if (object instanceof Item) {
       //     GameRegistry.register((Item) object);

            if (object instanceof ItemBase) {
                ((ItemBase) object).registerItemModel();
            }

            if (object instanceof SwordBase) {
                ((SwordBase) object).registerItemModel();
            }

            if (object instanceof AxeBase) {
                ((AxeBase) object).registerItemModel();
            }

            if (object instanceof HoeBase) {
                ((HoeBase) object).registerItemModel();
            }

            if (object instanceof PickaxeBase) {
                ((PickaxeBase) object).registerItemModel();
            }

            if (object instanceof ShovelBase) {
                ((ShovelBase) object).registerItemModel();
            }

            if (object instanceof ArmorBase) {
                ((ArmorBase) object).registerItemModel();
            }
        }

        if (object instanceof Block) {
       //     GameRegistry.register((Block) object);
        }

    }
    public static void registerItemBlock(Block object, ItemBlock itemBlock) {
      //  GameRegistry.register(object);

        if (itemBlock != null) {
      //      GameRegistry.register(itemBlock.setRegistryName(object.getRegistryName()));
        }
    }

    public static Potion registerPotion(String name, Potion potion)
    {
        ForgeRegistries.POTIONS.register(potion);
        //    GameRegistry.register(potion, new ResourceLocation(Constants.MOD_ID, name));
        return potion;
    }

    public static PotionType registerPotionType(String name, PotionType potionType)
    {
        ForgeRegistries.POTION_TYPES.register(potionType);
        //   GameRegistry.register(potionType, new ResourceLocation(Constants.MOD_ID, name));
        return potionType;
    }

    public static void registerWorldGenerator(IWorldGenerator generator, int modGenerationWeight){
        GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
    }
    public static void registerEvent(EventBus eventBus, Object target) {
        if (eventBus == MinecraftForge.EVENT_BUS) {
            MinecraftForge.EVENT_BUS.register(target);
        }
        else if (eventBus == MinecraftForge.ORE_GEN_BUS) {
            MinecraftForge.ORE_GEN_BUS.register(target);
        }
        else if (eventBus == MinecraftForge.TERRAIN_GEN_BUS) {
            MinecraftForge.TERRAIN_GEN_BUS.register(target);
        }
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
