package module_1;

import java.util.Collection;
import java.util.HashMap;

public class MyHashSet<E> {
    private final HashMap<E, Object> map;
    private static final Object PRESENT = new Object();

    public MyHashSet() {
        map = new HashMap<>();
    }

    public MyHashSet(int capacity) {
        map = new HashMap<>(capacity);
    }

    public boolean insert(E element) {
        return map.put(element, PRESENT) == null;
    }

    public boolean addAll(Collection<? extends E> collection) {
        boolean status = false;

        for (E obj : collection) {
            status = this.insert(obj);
        }

        return status;
    }

    public boolean remove(E element) {
        return map.remove(element) == PRESENT;
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean contains(E element) {
        return map.containsKey(element);
    }

    public void clear() {
        map.clear();
    }

    public int size() {
        return map.size();
    }
}