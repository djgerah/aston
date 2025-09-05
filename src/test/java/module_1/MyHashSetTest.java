package module_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyHashSetTest {
    private MyHashSet<String> mySet;

    @BeforeEach
    void mySetUp() {
        mySet = new MyHashSet<>();
    }

    @Test
    void testInsert() {
        assertTrue(mySet.insert("Monkey"));
        assertTrue(mySet.contains("Monkey"));
        assertEquals(1, mySet.size());

        assertFalse(mySet.insert("Monkey"));
        assertEquals(1, mySet.size());

        assertTrue(mySet.insert("Gorilla"));
        assertEquals(2, mySet.size());
    }

    @Test
    void testAddAll() {
        List<String> list = Arrays.asList("b", "u", "n", "g", "o");
        
        assertTrue(mySet.addAll(list));
        assertEquals(5, mySet.size());
        
        assertTrue(mySet.contains("b"));
        assertTrue(mySet.contains("u"));
        assertTrue(mySet.contains("n"));
        assertTrue(mySet.contains("g"));
        assertTrue(mySet.contains("o"));
    }

    @Test
    void testRemove() {
        assertTrue(mySet.insert("banana"));

        assertTrue(mySet.remove("banana"));
        assertFalse(mySet.contains("banana"));
        assertEquals(0, mySet.size());

        assertFalse(mySet.remove("banana"));
    }

    @Test
    void testIsEmpty() {
        assertTrue(mySet.isEmpty());
        mySet.insert("orange");
        assertFalse(mySet.isEmpty());
    }

    @Test
    void testClear() {
        assertTrue(mySet.insert("Monkey"));
        assertTrue(mySet.insert("Gorilla"));
        
        mySet.clear();
        
        assertTrue(mySet.isEmpty());
        assertEquals(0, mySet.size());
    }

    @Test
    void testSize() {
        assertEquals(0, mySet.size());

        assertTrue(mySet.insert("banana"));
        assertTrue(mySet.insert("orange"));

        assertEquals(2, mySet.size());
    }
}