package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import model.annotation.MaxLength;
import model.annotation.Positive;

public class AnnotationValidator {

    public static String[] validate(Object obj) {
        List<String> errors = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    Object val = field.get(obj);

                    if (field.isAnnotationPresent(Positive.class)) {
                        Positive pos = field.getAnnotation(Positive.class);
                        if (val instanceof Number num && num.doubleValue() <= 0) {
                            errors.add(field.getName() + " " + pos.message());
                        }
                    }

                    if (field.isAnnotationPresent(MaxLength.class)) {
                        MaxLength max = field.getAnnotation(MaxLength.class);
                        if (val instanceof String s && s.length() > max.value()) {
                            errors.add(field.getName() + " exceeds max length of " + max.value());
                        }
                    }
                } catch (IllegalAccessException e) {
                    errors.add("Cannot access " + field.getName());
                }
            }
            clazz = clazz.getSuperclass();
        }

        return errors.toArray(new String[0]);
    }
}
