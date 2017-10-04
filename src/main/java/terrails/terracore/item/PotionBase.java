package terrails.terracore.item;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionBase extends Potion {

    private static ResourceLocation texture_location;

    public PotionBase(String name, boolean isBadEffectIn, int liquidColorIn, int iconIndexX, int iconIndexY, String modid) {
        super(isBadEffectIn, liquidColorIn);
        this.setRegistryName(new ResourceLocation(modid, name));
        this.setPotionName("potion." + name);
        this.setIconIndex(iconIndexX, iconIndexY);
        texture_location = new ResourceLocation(modid, "textures/misc/potions.png");
    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect) {
        return true;
    }
    @Override
    public boolean shouldRender(PotionEffect effect) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture_location);
        return super.hasStatusIcon();
    }
}
