package module_3;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

class MyHashSet<E> implements Collection<E> {
    private final HashMap<E, Object> map;
    private static final Object PRESENT = new Object();

    public MyHashSet() {
        map = new HashMap<>();
    }

    public MyHashSet(int capacity) {
        map = new HashMap<>(capacity);
    }

    @Override
    public boolean add(E element) {
        return map.put(element, PRESENT) == null;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        boolean modified = false;
        for (E e : collection) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean remove(Object element) {
        return map.remove(element) == PRESENT;
    }

    @Override
    public boolean contains(Object element) {
        return map.containsKey(element);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    public Object[] toArray() {
        return map.keySet().toArray();
    }

    public <T> T[] toArray(T[] a) {
        return map.keySet().toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return map.keySet().containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return map.keySet().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return map.keySet().retainAll(c);
    }

    @Override
    public String toString() {
        return map.keySet().toString();
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        MyHashSet<String> band = new MyHashSet<>();
        band.add("vocalist");
        band.add("rhythm-guitarist");
        band.add("lead-guitarist");
        band.add("basist");
        band.add("DJ");
        band.add("drummer");

        System.out.println("band: " + band);
        System.out.println(band.contains("DJ"));
        System.out.println(band.size());
        System.out.println(band.isEmpty());
    }
}