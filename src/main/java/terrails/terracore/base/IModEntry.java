package terrails.terracore.base;


public interface IModEntry<T> {

    T getInstance();

    String getId();
    String getName();
    String getVersion();
}
