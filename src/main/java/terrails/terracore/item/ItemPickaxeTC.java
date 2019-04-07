package terrails.terracore.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemPickaxe;

public class ItemPickaxeTC extends ItemPickaxe {

    public ItemPickaxeTC(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }
}
