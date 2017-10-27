package terrails.terracore.block.tile.fluid;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import org.apache.commons.lang3.ArrayUtils;
import terrails.terracore.block.tile.TileEntityBase;

import javax.annotation.Nullable;

public class TileEntityTank extends TileEntityBase implements ITickable {

    private static int CAPACITY;
    private FluidTank tank = new FluidTankCustom(this, CAPACITY);
    private EnumFacing[] inputArray;
    private EnumFacing[] outputArray;
    private boolean allSides;
    private int oldFluidAmount;
    private FluidStack oldFluidStack;

    public TileEntityTank(int capacity, boolean allSides) {
        CAPACITY = capacity;
        this.allSides = allSides;
    }
    public TileEntityTank(int capacity, EnumFacing[] inputSides, EnumFacing[] outputSides) {
        this(capacity, false);
        inputArray = inputSides;
        outputArray = outputSides;
    }

    @Override
    public void update() {
        if (getTank().getFluid() != null) {
            if (getTank().getFluidAmount() != oldFluidAmount || getTank().getFluid() != oldFluidStack) {
                sendUpdates();
                oldFluidAmount = getTank().getFluidAmount();
                oldFluidStack = getTank().getFluid();
            }
        }
    }
    private void sendUpdates() {
        world.markBlockRangeForRenderUpdate(pos, pos);
        world.notifyBlockUpdate(pos, getState(), getState(), 3);
        world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
        markDirty();
    }
    private IBlockState getState() {
        return world.getBlockState(pos);
    }

    public int getLightLevel() {
        if (getTank().getFluid() != null && getTank().getFluidAmount() > 0) {
            return getTank().getFluid().getFluid().getLuminosity();
        } else return 0;
    }
    public int comparatorStrength() {
        return 15 * getTank().getFluidAmount() / getTank().getCapacity();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        readTankData(compound);
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        writeTankData(compound);
        return super.writeToNBT(compound);
    }

    public void readTankData(NBTTagCompound compound) {
        tank = loadTank(compound, this);
    }
    public void writeTankData(NBTTagCompound compund) {
        saveTank(compund, tank);
    }
    public static FluidTank loadTank(NBTTagCompound tagCompound, TileEntity tileEntity) {
        final FluidTank tank = new FluidTankCustom(tileEntity, CAPACITY);
        tank.readFromNBT(tagCompound);
        return tank;
    }
    public static void saveTank(NBTTagCompound tagCompound, FluidTank tank) {
        tank.writeToNBT(tagCompound);
    }


    public FluidTank getTank() {
        return tank;
    }
    public EnumFacing[] getInputSides() {
        return inputArray;
    }
    public EnumFacing[] getOutputSides() {
        return outputArray;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return this.getCapability(capability, facing) != null;
    }
    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        IFluidHandler fluidHandler = new IFluidHandler() {
            @Override
            public int fill(FluidStack resource, boolean doFill) {
                int amount = getTank().fill(resource, doFill);
                if (resource == null) return 0;
                return amount;
            }

            @Nullable
            @Override
            public FluidStack drain(FluidStack resource, boolean doDrain) {
                if (resource == null || !resource.isFluidEqual(getTank().getFluid())) {
                    return null;
                }
                return getTank().drain(resource.amount, doDrain);
            }

            @Nullable
            @Override
            public FluidStack drain(int maxDrain, boolean doDrain) {
                return getTank().drain(maxDrain, doDrain);
            }

            @Override
            public IFluidTankProperties[] getTankProperties() {
                return getTank().getTankProperties();
            }
        };

        EnumFacing[] enumFacings = ArrayUtils.addAll(inputArray, outputArray);
        if (allSides && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidHandler);
        else if (enumFacings != null && inputArray != null && outputArray != null)
            for (EnumFacing enumFacing : enumFacings)
                if (facing == enumFacing)
                    if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
                        return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidHandler);
        return super.getCapability(capability, facing);
    }
}
