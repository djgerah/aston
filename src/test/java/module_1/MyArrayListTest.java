package module_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private MyArrayList<String> myList;

    @Test
    void testConstructorWithCollection() {
        List<String> list = Arrays.asList("b", "a", "n", "g", "a");
        MyArrayList<String> myList = new MyArrayList<>(list);

        assertEquals(5, myList.size());
        assertEquals("b", myList.get(0));
        assertEquals("a", myList.get(1));
        assertEquals("n", myList.get(2));
        assertEquals("g", myList.get(3));
        assertEquals("a", myList.get(4));
    }

    @Test
    void testConstructorWithCapacity() {
        MyArrayList<String> myList = new MyArrayList<>(5);
        assertEquals(0, myList.size());

        assertThrows(IllegalArgumentException.class, () -> new MyArrayList<String>(-1));
    }

    @BeforeEach
    void setUp() {
        myList = new MyArrayList<>();
    }

    @Test
    void testAdd() {
        assertEquals(0, myList.size());

        myList.add("Monkey");
        myList.add("Gorilla");

        assertEquals(2, myList.size());
    }

    @Test
    void testGet() {
        assertEquals(0, myList.size());

        myList.add("Monkey");
        myList.add("Gorilla");

        assertEquals("Monkey", myList.get(0));
        assertEquals("Gorilla", myList.get(1));
    }

    @Test
    void testAddAll() {
        List<String> empty = List.of();
        assertFalse(myList.addAll(empty));

        List<String> list = Arrays.asList("b", "u", "n", "g", "o");

        assertTrue(myList.addAll(list));

        assertEquals(5, myList.size());
        assertEquals("b", myList.get(0));
        assertEquals("u", myList.get(1));
        assertEquals("n", myList.get(2));
        assertEquals("g", myList.get(3));
        assertEquals("o", myList.get(4));
    }

    @Test
    void testAddAllAtIndex() {
        assertTrue(myList.add("b"));
        assertTrue(myList.add("u"));
        assertTrue(myList.add("n"));
        assertTrue(myList.add("g"));
        assertTrue(myList.add("o"));
        assertTrue(myList.add("!"));

        List<String> list = Arrays.asList("-", "b", "u", "n", "g", "o");
        assertTrue(myList.addAll(5, list));

        assertEquals(12, myList.size());
        assertEquals("b", myList.get(0));
        assertEquals("u", myList.get(1));
        assertEquals("n", myList.get(2));
        assertEquals("g", myList.get(3));
        assertEquals("o", myList.get(4));
        assertEquals("-", myList.get(5));
        assertEquals("b", myList.get(6));
        assertEquals("u", myList.get(7));
        assertEquals("n", myList.get(8));
        assertEquals("g", myList.get(9));
        assertEquals("o", myList.get(10));
        assertEquals("!", myList.get(11));

        List<String> empty = List.of();
        assertFalse(myList.addAll(11, empty));
    }

    @Test
    void testRemoveByIndex() {
        myList.add("apple");
        myList.add("banana");
        myList.add("orange");

        String removed = myList.remove(1);

        assertEquals("banana", removed);
        assertEquals(2, myList.size());
        assertEquals("orange", myList.get(1));
    }

    @Test
    void testRemoveByObject() {
        assertTrue(myList.add("apple"));
        assertTrue(myList.add("banana"));
        assertTrue(myList.add("orange"));

        assertTrue(myList.remove("apple"));
        assertEquals(2, myList.size());
        assertEquals("banana", myList.get(0));

        assertFalse(myList.remove("apple"));
    }

    @Test
    void testGetByWrongIndex() {
        assertTrue(myList.add("apple"));

        assertThrows(IndexOutOfBoundsException.class, () -> myList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> myList.get(1));
    }
}