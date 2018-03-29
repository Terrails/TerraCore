package terrails.terracore.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {

    private final String modId;

    public BlockBase(Material materialIn, String modId) {
        super(materialIn);
        this.modId = modId;
    }

    public BlockBase(Material blockMaterialIn, MapColor blockMapColorIn, String modId) {
        super(blockMaterialIn, blockMapColorIn);
        this.modId = modId;
    }

    @Override
    public Block setUnlocalizedName(String name) {
        return super.setUnlocalizedName(modId + "." + name);
    }
}
