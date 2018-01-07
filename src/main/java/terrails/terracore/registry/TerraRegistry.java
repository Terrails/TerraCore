package terrails.terracore.registry;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TerraRegistry {

    public static <T extends ICriterionInstance> ICriterionTrigger<T> registerAdvancementTrigger(ICriterionTrigger<T> trigger) {
        Method method;
        try {
            method = ReflectionHelper.findMethod(CriteriaTriggers.class, "register", "func_192118_a", ICriterionTrigger.class);
            method.setAccessible(true);
            trigger = (ICriterionTrigger<T>) method.invoke(null, trigger);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            System.out.println("Failed to register trigger " + trigger.getId() + "!");
            e.printStackTrace();
        }
        return trigger;
    }
}
