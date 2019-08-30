package dev.leswilson.petclinic.services.map;

import dev.leswilson.petclinic.exceptions.DataException;
import dev.leswilson.petclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, I extends Long> {

    private Map<Long, T> map = new HashMap<>();

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    public T findById(I id) {
        return map.get(id);
    }

    public T save(T t) {
        if(t != null) {
            if (t.isNew()) {
                t.setId(this.getNextId());
            }
            map.put(t.getId(), t);
        } else {
            throw new DataException("Object passed in cannot be null");
        }
        return t;
    }

    public void delete(T t) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(t));
    }

    public void deleteById(I id) {
        map.remove(id);
    }

    private Long getNextId() {

        return map.size()==0 ? 1L : Collections.max(map.keySet()) + 1;
    }
}
