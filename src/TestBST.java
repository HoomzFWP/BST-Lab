
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestBST {
    private BinarySearchTree<Integer,String> emptyTree = new BinarySearchTree<>();
    private BinarySearchTree<Integer,String> eightNodesTree= new BinarySearchTree<>();
    private BinarySearchTree<Integer,String> testTree = new BinarySearchTree<>();

    @Before
    public void setUp() {
        eightNodesTree.put(4,"apple");
        eightNodesTree.put(6,"banana");
        eightNodesTree.put(1,"strawberry");
        eightNodesTree.put(3,"kiwi");
        eightNodesTree.put(7,"lemon");
        eightNodesTree.put(10,"lime");
        eightNodesTree.put(6,"mango");
        eightNodesTree.put(8,"pear");

        testTree.put(5,"apple");
        testTree.put(3,"banana");
        testTree.put(2,"strawberry");
        testTree.put(6,"kiwi");
        testTree.put(4,"lemon");
        testTree.put(10,"lime");
        testTree.put(7,"mango");
        testTree.put(8,"pear");
    }

    @Test
    public void TestEmpty() {
        assertTrue(emptyTree.isEmpty());
        assertEquals(0,emptyTree.size());
    }

    @Test
    public void TestNonEmpty() {
        emptyTree.put(4,"apple");
        assertFalse(emptyTree.isEmpty());
        assertEquals(1,emptyTree.size());
    }

    @Test
    public void TestPutGetRoot() {
        emptyTree.put(4,"apple");
        assertEquals("apple",emptyTree.get(4));
    }

    @Test
    public void TestGetFromEmpty() {
        assertNull(emptyTree.get(4));
    }

    @Test
    public void TestGet() {
        assertEquals("strawberry",eightNodesTree.get(1));
        assertEquals("lemon",eightNodesTree.get(7));
    }

    @Test
    public void TestGetNotThere() {
        assertNull(eightNodesTree.get(5));
    }

    @Test (expected=NullPointerException.class)
    public void TestNullKey() {
        assertNull(eightNodesTree.get(null));
    }

    @Test
    public void TestSize() {
        assertEquals(8,eightNodesTree.size());
    }

    @Test
    public void TestClear() {
        eightNodesTree.clear();
        assertTrue(eightNodesTree.isEmpty());
        assertEquals(0,eightNodesTree.size());
        assertNull(eightNodesTree.get(4));
    }

    // to-do: tests for remove, tree traversals

    @Test
    public void testInOrderSmallTree() {
        BinarySearchTree<Integer,String> threeElements = new BinarySearchTree<>();
        threeElements.put(5, "apple"); // the root
        threeElements.put(8, "pear"); // right of root
        threeElements.put(6, "lemon"); // right of root, left of "pear"
        ArrayList<KVPair<Integer,String>> expected = new ArrayList<>();
        expected.add(new KVPair(5, "apple"));
        expected.add(new KVPair(6, "lemon"));
        expected.add(new KVPair(8, "pear"));

        ArrayList<KVPair<Integer,String>> actual = threeElements.inOrder();

        assertEquals(expected, actual);
    }

    @Test
    public void testInOrderEmptyTree() {
        ArrayList<KVPair<Integer,String>> expected = new ArrayList<>();

        ArrayList<KVPair<Integer,String>> actual = emptyTree.inOrder();

        assertEquals(expected, actual);
    }

    @Test
    public void testRemove(){
        assertEquals("strawberry", eightNodesTree.remove(1));
        assertEquals("kiwi", eightNodesTree.remove(3));
        assertEquals("lemon", eightNodesTree.remove(7));

        assertEquals("mango",testTree.remove(7));
        assertEquals("banana",testTree.remove(3));
        assertEquals(null, testTree.remove(20));
        assertEquals("lemon", testTree.remove(4));
        assertEquals("kiwi", testTree.remove(6));
        assertEquals(null, testTree.remove(6));
    }
}