package terrails.terracore.block.tile.fluid;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidTank;

public class FluidTankCustom extends FluidTank {

    public FluidTankCustom(TileEntity tileEntity, int capacity) {
        super(capacity);
        tile = tileEntity;
    }
}
