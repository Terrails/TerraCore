package terrails.terracore.base.registry;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;
import terrails.terracore.registry.IUnlocalizedName;
import terrails.terracore.base.IModEntry;

import java.util.*;

@SuppressWarnings("unchecked")
public class RegistryList<T extends IForgeRegistryEntry> extends ArrayList<T> {

    private final RegistryType type;
    private final IModEntry modEntry;

    public RegistryList(RegistryType type, IModEntry modEntry) {
        this.type = type;
        this.modEntry = modEntry;
    }

    public RegistryType getType() {
        return this.type;
    }

    @Override
    public boolean add(T entry) {
        if (this.contains(entry)) return true;
        check(false, entry);
        return super.add(entry);
    }
    @Override
    public void add(int index, T entry) {
        if (this.contains(entry)) return;
        check(false, entry);
        super.add(index, entry);
    }
    @Override
    public boolean addAll(Collection<? extends T> entries) {
        for (T entry : entries) {
            if (this.contains(entry)) { continue; }
            check(false, entry);
            super.add(entry);
        }
        return false;
    }
    @Override
    public boolean addAll(int index, Collection<? extends T> entries) {
        for (T entry : entries) {
            if (this.contains(entry)) { continue; }
            check(false, entry);
            super.add(index, entry);
        }
        return false;
    }

    public boolean add(T entry, boolean override) {
        if (this.contains(entry)) return true;
        check(override, entry);
        return super.add(entry);
    }
    public void add(int index, T entry, boolean override) {
        if (this.contains(entry)) return;
        check(override, entry);
        super.add(index, entry);
    }
    public boolean addAll(Collection<? extends T> entries, boolean override) {
        for (T entry : entries) {
            if (this.contains(entry)) { continue; }
            check(override, entry);
            super.add(entry);
        }
        return true;
    }
    public boolean addAll(int index, Collection<? extends T> entries, boolean override) {
        for (T entry : entries) {
            if (this.contains(entry)) { continue; }
            check(override, entry);
            super.add(index, entry);
        }
        return true;
    }

    private void check(boolean override, T entry) {
        if (this.contains(entry)) return;

        /* Check registry name... */
        ResourceLocation registryName = entry.getRegistryName();
        if (registryName == null) {
            throw new RuntimeException(entry + ", doesn't have a registry name!");
        }
        if (registryName.getResourceDomain().equalsIgnoreCase("minecraft") && !override) {
            entry.setRegistryName(new ResourceLocation(modEntry.getId(), registryName.getResourcePath()));
        }

        /* Check unlocalized name... only if its instance of the custom interface */
        if (!IUnlocalizedName.class.isInstance(entry))
            return;

        IUnlocalizedName unlocalizedName = (IUnlocalizedName) entry;
        String name = unlocalizedName.getEntryName();
        if (name.isEmpty()) {
            throw new NullPointerException("'" + Objects.requireNonNull(entry.getRegistryName()).toString() + "' does not have a unlocalized name!");
        }

        if (!override) {
            if (name.startsWith("tile.") || name.startsWith("item.")) {
                name = name.substring(5);
            } else if (name.startsWith("potion.")) {
                name = name.substring(7);
            } else if (name.startsWith("enchantment.")) {
                name = name.substring(12);
            }

            if (!name.contains(modEntry.getId())) {
                name = (entry instanceof Potion ? "potion." : "") + modEntry.getId() + "." + name;
                unlocalizedName.setEntryName(name);
            }
        }
    }

    public static RegistryList newInstance(RegistryType type, IModEntry modEntry) {
        return new RegistryList(type, modEntry);
    }
}
