package terrails.terracore.item;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionBase extends Potion {

    private static ResourceLocation texture_location;

    public PotionBase(String name, boolean isBadEffectIn, int liquidColorIn, int iconIndexX, int iconIndexY) {
        super(isBadEffectIn, liquidColorIn);
        this.setRegistryName(name);
        this.setPotionName("potion." + name);
        this.setIconIndex(iconIndexX, iconIndexY);
    }

    public void setTextureLocation(ResourceLocation location) {
        texture_location = location;
    }
    public void setDefaultTextureLocation(String modid) {
        texture_location = new ResourceLocation(modid, "textures/misc/potions.png");
    }
    public ResourceLocation getTextureLocation() {
        return texture_location;
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
