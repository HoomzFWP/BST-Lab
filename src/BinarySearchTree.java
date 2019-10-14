import java.util.ArrayList;

/**
 * Binary search tree stores values indexed by keys. Keys must be Comparable and
 * are organized based on their natural ordering (i.e. the ordering given by
 * their compareTo). Values can be of any object type. This tree implementation
 * is not balanced, i.e. it may behave as a linked list in the worst case. Keys
 * may be repeated. Somewhat modeled after Map<K,V> classes in the Java
 * Collections Framework
 *
 * Author: Elena Machkasova For UMM CSci 2101 class.
 **/

public class BinarySearchTree<K extends Comparable<K>, V> {
    private BSTNode root = null;
    private int size = 0;
    /**
     * @return true if the tree is empty, false otherwise
     **/
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * @return the number of elements in the tree
     **/
    public int size() {
        return size;
    }

    /**
     * Adds a given value indexed with a given key to the tree according to the
     * binary search structure
     *
     * @param key
     *            - the key of the given element
     * @param value
     *            - the value of the given element
     */
    public void put(K key, V value) {
        BSTNode current = root;
        BSTNode newNode = new BSTNode(key, value);
        boolean running = true;

        if(root == null){
            root = newNode;
            running = false;
            size++;
        }
        while(running){
            if(current.key.compareTo(key) >= 0){
                if(current.left == null){
                    current.left = newNode;
                    size++;
                    running = false;
                }else{
                    current = current.left;
                }
            }
            if(current.key.compareTo(key) < 0){
                if(current.right == null){
                    current.right = newNode;
                    size++;
                    running = false;
                }else{
                    current = current.right;
                }
            }
        }

    }

    /**
     * returns a value associated with the given key in this binary search tree.
     * If multiple values are associated with this key, any one may be returned.
     * If there is no element associated with this key, null is returned.
     *
     * @param key
     *            - the key to search for.
     * @return - a value to which the specified key is mapped, or null if this
     *         tree contains no mapping for the key
     * @throws NullPointerException if the key is null
     */
    public V get(K key) {

        Boolean running = true;
        BSTNode current = root;
        V value = null;

        if (current == null) {
            return null;
        } else {
            while (running) {
                if (key.compareTo(current.key) == 0) {
                    value = current.value;
                    running = false;
                } else if (key.compareTo(current.key) < 0 && current.left != null) {
                    current = current.left;
                } else if (key.compareTo(current.key) > 0 && current.right != null) {
                    current = current.right;
                } else {
                    running = false;
                }
            }
        }
        return value;
    }


    /**
     * Removes an element with the given key. The resulting tree is a binary
     * search tree. If there is no such key, the tree is unchanged. If there are
     * multiple values associated with this key, only one is removed. Returns
     * the value associated with this key or null if there is no such value.
     *
     * @param key
     *            - the key
     * @return - a value to which the specified key is mapped, or null if this
     *         tree contains no mapping for the key
     * @throws NullPointerException if the key is null
     */

    /**
     *
     * @param key
     * @return
     * The remove method is adapted from the textbook
     */
    public V remove(K key) {

        V temp = findHelp(root, key);
        if (temp != null) {
            root = removeHelp(root, key);
            size--;
        }
        return temp;
        }

     public V findHelp(BSTNode rt, K key) {
        if (rt == null){
            return null;
        }
        if (rt.key.compareTo(key) > 0){
            return findHelp(rt.left, key);
        } else if (rt.key == key){
            return rt.value;
        } else {
            return findHelp(rt.right, key);
        }
     }

     public BSTNode removeHelp (BSTNode rt, K key){
        if (rt == null){
            return null;
        }
        if (rt.key.compareTo(key) > 0){
            rt.left = removeHelp((rt.left), key);
        } else if (rt.key.compareTo(key) < 0){
            rt.right = removeHelp(rt.right, key);
        } else {
            if (rt.left == null){
                return rt.right;
            } else if (rt.right == null){
                return rt.left;
            } else {
                BSTNode temp = getMax(rt.left);
                rt.value = temp.value;
                rt.left = deleteMax(rt.left);
            }
        }
        return rt;
     }

     public BSTNode getMax(BSTNode rt){
        if (rt.right == null){
            return rt;
        }
        return getMax(rt.right);
     }

     public BSTNode deleteMax(BSTNode rt){
        if (rt.right == null){
            return rt.left;
        }
        rt.right = deleteMax(rt.right);
        return rt;
     }

    /**
     * Clears all elements from a given tree.
     * The resulting tree is empty.
     */
    public void clear() {
        size = 0;
        root = null;
    }

    /**
    // to-do: tests for remove, tree traversals
}
     *
     * @return
     */
    public ArrayList<KVPair<K,V>> inOrder() {
        ArrayList<KVPair<K,V>> result = new ArrayList<>();

        if (root != null) {
            root.inOrder(result);
        }

        return result;
    }

    private class BSTNode {
        public K key;
        public V value;
        public BSTNode left = null;
        public BSTNode right = null;

        // null key will generate a null pointer exception when
        // a method (such as compareTo) is called on it.
        // This is fine, according to the JCF specification.
        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        //
        public void inOrder(ArrayList<KVPair<K,V>> result) {
            if(left != null){
                left.inOrder(result);
            }
            result.add(new KVPair<K, V> (key, value));
            if(right != null) {
                right.inOrder(result);
            }
        }
    }
}