package terrails.terracore.registry;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;
import terrails.terracore.base.IModEntry;

import java.util.List;
import java.util.Objects;

public class SimpleRegistry<T extends IForgeRegistryEntry> {

    protected IModEntry modEntry;
    protected RegistryType type;
    protected List<T> registryEntries = Lists.newArrayList();

    public SimpleRegistry(RegistryType type, IModEntry modEntry) {
        this.modEntry = modEntry;
        this.type = type;
    }

    public T register(T entry) {
        return this.register(entry, false);
    }
    public T register(T entry, boolean override) {
        ResourceLocation registryName = entry.getRegistryName();
        if (registryName == null) {
            throw new RuntimeException(entry + ", doesn't have a registry name!");
        }
        if (registryName.getResourceDomain().equalsIgnoreCase("minecraft") && !override) {
            entry.setRegistryName(new ResourceLocation(modEntry.getId(), registryName.getResourcePath()));
        }

        checkNames(entry, override);
        registryEntries.add(entry);
        return entry;
    }

    private void checkNames(T entry, boolean override) {
        String name = getName(entry);
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
                name = modEntry.getId() + "." + name;

                switch (type) {
                    case BLOCK:
                        Block block = (Block) entry;
                        block.setUnlocalizedName(name);
                        break;
                    case ITEM:
                        Item item = (Item) entry;
                        item.setUnlocalizedName(name);
                        break;
                    case POTION:
                        Potion potion = (Potion) entry;
                        potion.setPotionName("potion." + name);
                    case ENCHANTMENT:
                        Enchantment enchantment = (Enchantment) entry;
                        enchantment.setName(name);
                }
            }
        }
    }
    private String getName(T entry) {
        String name = "";
        switch (type) {
            case BLOCK:
                name = ((Block) entry).getUnlocalizedName();
                break;
            case ITEM:
                name = ((Item) entry).getUnlocalizedName();
                break;
            case POTION:
                name = ((Potion) entry).getName();
                break;
            case ENCHANTMENT:
                name = ((Enchantment) entry).getName();
                break;
            case SOUND_EVENT:
            case BIOME:
                name = "client-only";
                break;
        }
        return name;
    }

    public T get(String string) {
        if (string.contains(":")) {
            ResourceLocation location = new ResourceLocation(string);
            return registryEntries.stream()
                    .filter(value -> value.getRegistryName() != null)
                    .filter(value -> value.getRegistryName().equals(location))
                    .findFirst().orElse(null);
        }
        return registryEntries.stream()
                .filter(value -> value.getRegistryName() != null)
                .filter(value -> value.getRegistryName().getResourcePath().equals(string))
                .findFirst().orElse(null);
    }
    public T get(ResourceLocation location) {
        return get(location.toString());
    }

    public List<T> getEntries() {
        return this.registryEntries;
    }
    public RegistryType getType() {
        return this.type;
    }
}
