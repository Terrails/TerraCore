package terrails.terracore.registry;

import com.google.common.collect.Lists;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;
import terrails.terracore.api.forgeentry.IUnlocalizedName;
import terrails.terracore.base.IModEntry;

import java.util.List;
import java.util.Objects;

public class RegistryForgeEntry<T extends IForgeRegistryEntry> {

    protected final IModEntry modEntry;
    protected final RegistryType type;
    protected List<T> entries = Lists.newArrayList();

    public RegistryForgeEntry(RegistryType type, IModEntry modEntry) {
        this.modEntry = modEntry;
        this.type = type;
    }

    public T register(T entry) {
        return this.register(entry, false);
    }
    public T register(T entry, boolean override) {
        if (entries.contains(entry)) return entry;

        ResourceLocation registryName = entry.getRegistryName();
        if (registryName == null) {
            throw new RuntimeException(entry + ", doesn't have a registry name!");
        }
        if (registryName.getResourceDomain().equalsIgnoreCase("minecraft") && !override) {
            entry.setRegistryName(new ResourceLocation(modEntry.getId(), registryName.getResourcePath()));
        }

        checkNames(entry, override);
        entries.add(entry);
        return entry;
    }
    private void checkNames(T entry, boolean override) {
        IUnlocalizedName unlocalizedName = entry instanceof IUnlocalizedName ? (IUnlocalizedName) entry : null;
        if (unlocalizedName == null)
            return;


        String name = unlocalizedName.getEntryName();
        if (name.isEmpty()) {
            throw new NullPointerException("'" + Objects.requireNonNull(entry.getRegistryName()).toString() + "' does not have a unlocalized name!");
        }

        if (!name.equalsIgnoreCase("client-only") && !override) {
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

    public T get(String string) {
        if (string.contains(":")) {
            ResourceLocation location = new ResourceLocation(string);
            return entries.stream()
                    .filter(value -> value.getRegistryName() != null)
                    .filter(value -> value.getRegistryName().equals(location))
                    .findFirst().orElse(null);
        }
        return entries.stream()
                .filter(value -> value.getRegistryName() != null)
                .filter(value -> value.getRegistryName().getResourcePath().equals(string))
                .findFirst().orElse(null);
    }
    public T get(ResourceLocation location) {
        return get(location.toString());
    }

    @SuppressWarnings("unchecked")
    private <R extends IForgeRegistryEntry<R>> void register(RegistryEvent.Register<R> event) {
        entries.stream().map(value -> (R) value).forEach(event.getRegistry()::register);
    }

    public List<T> getEntries() {
        return this.entries;
    }
    public RegistryType getType() {
        return this.type;
    }
}
