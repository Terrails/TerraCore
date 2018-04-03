package terrails.terracore.registry;

import com.google.common.collect.Lists;
import terrails.terracore.base.IModEntry;
import java.util.List;

public abstract class Registry<T> {

    protected final IModEntry modEntry;
    protected final RegistryType type;

    protected List<T> entries = Lists.newArrayList();

    @SuppressWarnings("unchecked")
    public Registry(RegistryType type, IModEntry modEntry) {
        this.modEntry = modEntry;
        this.type = type;
    }

    public abstract T register(T entry);

    public List<T> getEntries() {
        return this.entries;
    }
    public RegistryType getType() {
        return this.type;
    }
}