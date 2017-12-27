package terrails.terracore.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilitySerializable<CAP> implements ICapabilitySerializable<NBTBase> {

    private final Capability<CAP> CAPABILITY;
    private final CAP INSTANCE;

    public CapabilitySerializable(Capability<CAP> capability) {
        this.CAPABILITY = capability;
        this.INSTANCE = CAPABILITY.getDefaultInstance();
    }
    public CapabilitySerializable(final Capability<CAP> capability, final CAP instance) {
        this.CAPABILITY = capability;
        this.INSTANCE = instance;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == this.CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == this.CAPABILITY ? this.CAPABILITY.cast(this.INSTANCE) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return this.CAPABILITY.writeNBT(this.INSTANCE, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        this.CAPABILITY.readNBT(this.INSTANCE, null, nbt);
    }
}
