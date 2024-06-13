package com.shivay.util;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Map;

public class RecordUtil {

    public static <R extends Record> R clone(R template, Map<String, Object> overrides) {
        try {
            var types = new ArrayList<Class<?>>();
            var values = new ArrayList<>();
            for (var component : template.getClass().getRecordComponents()) {
                types.add(component.getType());
                var name = component.getName();
                var overridden = overrides.containsKey(name);
                values.add(overridden ? overrides.get(name) : component.getAccessor().invoke(template));
            }
            Constructor<?> canonical = template.getClass().getDeclaredConstructor(types.toArray(new Class[0]));
            @SuppressWarnings("unchecked")
            var result = (R) canonical.newInstance(values.toArray());
            return result;
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Reflection failed: " + e, e);
        }
    }

}
