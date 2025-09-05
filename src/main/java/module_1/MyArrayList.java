package module_1;

import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(Collection<? extends E> collection) {
        if (!collection.isEmpty()) {
            copyCollection(collection);
        } else {
            this.elementData = new Object[0];
        }
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.elementData = new Object[capacity];
        } else if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        } else {
            this.elementData = new Object[0];
        }
    }
    
    private void copyCollection(Collection<? extends E> collection) {
        Object[] src = collection.toArray();
        this.elementData = Arrays.copyOf(src, src.length, Object[].class);
        this.size = src.length;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index [" + index + "] Is Out Of Bounds");
        }
    }

    public int size() {
        return this.size;
    }

    private int newLength(int oldLength, int minGrowth, int prefGrowth) {
        int minLength = oldLength + minGrowth;

        if (minLength < 0) {
            throw new OutOfMemoryError("Required array size is Out Of Memory");
        }

        int prefLength = oldLength + Math.max(minGrowth, prefGrowth);
        int newLength = (prefLength > minLength) ? prefLength : minLength;

        return newLength;
    }

    private Object[] ensureCapacity(Object[] objects, int minCapacity) {
        if (minCapacity > objects.length) {
            int newCapacity = newLength(objects.length, minCapacity - objects.length, objects.length / 2);
            objects = Arrays.copyOf(objects, newCapacity);
        }

        return objects;
    }

    private Object[] grow(int minCapacity) {
        if (this.elementData.length == 0) {
            int newCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);

            return this.elementData = new Object[newCapacity];
        } else {
            return this.ensureCapacity(this.elementData, minCapacity);
        }
    }
   
    private Object[] grow() {
        return this.grow(this.size + 1);
    }

    public boolean add(E element) {
        if (this.size == elementData.length) {
            this.elementData = this.grow();
        }

        this.elementData[size] = element;
        ++this.size;

        return true;
    }

    public boolean addAll(Collection<? extends E> collection) {
        Object[] objects = collection.toArray();
        int length = objects.length;

        if (length == 0) {
            return false;
        } else {
            if (length > this.elementData.length - size) {
                this.elementData = ensureCapacity(this.elementData, size + length);
            }

            System.arraycopy(objects, 0, this.elementData, this.size, length);
            this.size += length;

            return true;
        }
    }

    public boolean addAll(int index, Collection<? extends E> collection) {
        checkIndex(index);

        Object[] objects = collection.toArray();
        int length = objects.length;

        if (length == 0) {
            return false;
        } else {
            int size = this.size();

            if (length > this.elementData.length - size) {
                this.elementData = ensureCapacity(this.elementData, size + length);
            }

            int count = size - index;

            if (count > 0) {
                int distPos = index + length;
                System.arraycopy(elementData, index, elementData, distPos, count);
            }

            System.arraycopy(objects, 0, elementData, index, length);
            this.size = size + length;

            return true;
        }
   }

    public E get(int index) {
        checkIndex(index);

        return (E) this.elementData[index];
    }

    public E remove(int index) {
        checkIndex(index);

        Object[] objects = this.elementData;
        E oldValue = (E) objects[index];

        fastRemove(objects, index);

        return oldValue;
    }

    public boolean remove(Object obj) {
        Object[] objects = this.elementData;
        
        for (int i = 0; i < size; i++) {
            if (obj.equals(objects[i])) {
                fastRemove(objects, i);
        
                return true;
            }
        }
        
        return false;
    }

    private void fastRemove(Object[] src, int index) {
        int length = size - index - 1;

        if (length > 0) {
            System.arraycopy(src, index + 1, this.elementData, index, length);
        }
        
        this.elementData[--size] = null;
    }
}