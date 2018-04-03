package terrails.terracore.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import terrails.terracore.api.forgeentry.IUnlocalizedName;

public class BlockBase extends Block implements IUnlocalizedName<Block> {

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

    @Override
    public String getUnlocalizedName() {
        return super.getUnlocalizedName();
    }
}
