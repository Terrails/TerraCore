package terrails.terracore.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import terrails.terracore.registry.IUnlocalizedName;

public class BlockBase extends Block implements IUnlocalizedName<Block> {

    public BlockBase(Material materialIn) {
        super(materialIn);
    }

    @Override
    public Block setEntryName(String name) {
        return this.setUnlocalizedName(name);
    }

    @Override
    public String getEntryName() {
        return this.getUnlocalizedName();
    }
}
