package terrails.terracore.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import terrails.terracore.api.forgeentry.IUnlocalizedName;

public class PotionBase extends Potion implements IUnlocalizedName<Potion> {

    private ResourceLocation texture_location;
    private final String modId;

    public PotionBase(String modId, boolean isBadEffectIn, int liquidColorIn, int iconIndexX, int iconIndexY) {
        super(isBadEffectIn, liquidColorIn);
        this.modId = modId;
        this.setIconIndex(iconIndexX, iconIndexY);
    }

    protected void setTextureLocation(ResourceLocation location) {
        texture_location = location;
    }
    protected void setDefaultTextureLocation(String modid) {
        texture_location = new ResourceLocation(modid, "textures/misc/potions.png");
    }
    protected ResourceLocation getTextureLocation() {
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
    public Potion setPotionName(String name) {
        return super.setPotionName(modId + "." + name);
    }

    @Override
    public String getName() {
        return "potion." + super.getName();
    }

    @Override
    public String getEntryName() {
        return this.getName();
    }

    @Override
    public Potion setEntryName(String name) {
        return this.setPotionName(modId + "." + name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture_location);
        return super.hasStatusIcon();
    }
}
