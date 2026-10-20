package repository;

import model.annotation.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Repository<T> {
    private final HashMap<String, T> data = new HashMap<>();

    public synchronized T save(T entity) {
        try {
            for (Field field : entity.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    String id = String.valueOf(field.get(entity));
                    data.put(id, entity);
                    return entity;
                }
            }
            throw new IllegalArgumentException("No @Id field found");
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public synchronized T findById(String id) {
        return data.get(id);
    }

    public synchronized ArrayList<T> findAll() {
        return new ArrayList<>(data.values());
    }

    public synchronized boolean delete(String id) {
        return data.remove(id) != null;
    }

    @SafeVarargs
    public final void saveAll(T... items) {
        for (T item : items) {
            save(item);
        }
    }

    public synchronized void replaceAll(ArrayList<T> items) {
        data.clear();
        for (T item : items) {
            save(item);
        }
    }
}
