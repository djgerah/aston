package module_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class MyHashSetTest {
    @Test
    void testDefaultConstructor() {
        MyHashSet<String> myDefaultHashSet = new MyHashSet<>();

        assertNotNull(myDefaultHashSet);
        assertEquals(0, myDefaultHashSet.size());
        assertTrue(myDefaultHashSet.isEmpty());
    }

    void testParamConstuctor() {
        MyHashSet<String> myParamHashSet = new MyHashSet<>(20);

        assertNotNull(myParamHashSet);
        assertEquals(0, myParamHashSet.size());
        assertTrue(myParamHashSet.isEmpty());
    }

    private MyHashSet<String> myHashSet;

    @BeforeEach
    void myHashSetUp() {
        myHashSet = new MyHashSet<>();
    }

    @Test
    void testInsert() {
        assertTrue(myHashSet.insert("Monkey"));
        assertTrue(myHashSet.contains("Monkey"));
        assertEquals(1, myHashSet.size());

        assertFalse(myHashSet.insert("Monkey"));
        assertEquals(1, myHashSet.size());

        assertTrue(myHashSet.insert("Gorilla"));
        assertEquals(2, myHashSet.size());
    }

    @Test
    void testAddAllAsList() {
        List<String> list = Arrays.asList("b", "u", "n", "g", "o");

        assertTrue(myHashSet.addAll(list));
        assertEquals(5, myHashSet.size());

        assertTrue(myHashSet.contains("b"));
        assertTrue(myHashSet.contains("u"));
        assertTrue(myHashSet.contains("n"));
        assertTrue(myHashSet.contains("g"));
        assertTrue(myHashSet.contains("o"));
    }

    @Test
    void testAddAllAsSet() {
        Set<String> set = Set.of("b", "u", "n", "g", "o");

        assertTrue(myHashSet.addAll(set));
        assertEquals(5, myHashSet.size());

        assertTrue(myHashSet.contains("b"));
        assertTrue(myHashSet.contains("u"));
        assertTrue(myHashSet.contains("n"));
        assertTrue(myHashSet.contains("g"));
        assertTrue(myHashSet.contains("o"));
    }

    @Test
    void testAddAllAsVector() {
        Vector<String> vector = new Vector<>(List.of("b", "u", "n", "g", "o"));

        assertTrue(myHashSet.addAll(vector));
        assertEquals(5, myHashSet.size());

        assertTrue(myHashSet.contains("b"));
        assertTrue(myHashSet.contains("u"));
        assertTrue(myHashSet.contains("n"));
        assertTrue(myHashSet.contains("g"));
        assertTrue(myHashSet.contains("o"));
    }

    @Test
    void testAddAllAsMap() {
        Map<Integer, String> map = Map.of(1, "b", 2, "u", 3, "n", 4, "g", 5, "o");

        assertTrue(myHashSet.addAll(map.values()));
        assertEquals(5, myHashSet.size());

        assertTrue(myHashSet.contains("b"));
        assertTrue(myHashSet.contains("u"));
        assertTrue(myHashSet.contains("n"));
        assertTrue(myHashSet.contains("g"));
        assertTrue(myHashSet.contains("o"));
    }

    @Test
    void testAddAllAsArray() {
        String[] arr = {"b", "u", "n", "g", "o"};

        assertTrue(myHashSet.addAll(Arrays.asList(arr)));
        assertEquals(5, myHashSet.size());

        assertTrue(myHashSet.contains("b"));
        assertTrue(myHashSet.contains("u"));
        assertTrue(myHashSet.contains("n"));
        assertTrue(myHashSet.contains("g"));
        assertTrue(myHashSet.contains("o"));
    }

    @Test
    void testRemove() {
        assertTrue(myHashSet.insert("banana"));

        assertTrue(myHashSet.remove("banana"));
        assertFalse(myHashSet.contains("banana"));
        assertEquals(0, myHashSet.size());

        assertFalse(myHashSet.remove("banana"));
    }

    @Test
    void testIsEmpty() {
        assertTrue(myHashSet.isEmpty());
        myHashSet.insert("orange");
        assertFalse(myHashSet.isEmpty());
    }

    @Test
    void testClear() {
        assertTrue(myHashSet.insert("Monkey"));
        assertTrue(myHashSet.insert("Gorilla"));

        myHashSet.clear();

        assertTrue(myHashSet.isEmpty());
        assertEquals(0, myHashSet.size());
    }

    @Test
    void testSize() {
        assertEquals(0, myHashSet.size());

        assertTrue(myHashSet.insert("banana"));
        assertTrue(myHashSet.insert("orange"));

        assertEquals(2, myHashSet.size());
    }
}