package terrails.terracore.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class PotionTC extends Potion {

    private ResourceLocation potionTexture;

    public PotionTC(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
    }

    protected void setTextureLocation(ResourceLocation location) {
        this.potionTexture = location;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasStatusIcon() {
        if (potionTexture != null) {
            Minecraft.getInstance().getTextureManager().bindTexture(this.potionTexture);
        }
        return super.hasStatusIcon();
    }
}
