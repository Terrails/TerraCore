package terrails.terracore.helper;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class Comparables implements Comparable {

    public Entity entity;
    public World world;

    public Comparables(World world) {
        this.world = world;
    }

    public Comparables(Entity entity) {
        this.entity = entity;
    }

    public Comparables(World world, Entity entity) {
        this.world = world;
        this.entity = entity;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
