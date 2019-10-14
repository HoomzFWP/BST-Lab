public class KVPair<K extends Comparable<K>,V> {
    private K key;
    private V value;

    public KVPair(K key, V value) {
        this.key = key;
        this. value = value;
    }

    public String toString() {
        return "" + key + " " + value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public boolean equals(Object o) {
        if (!(o instanceof KVPair)) {
            return false;
        }
        KVPair<K,V> other = (KVPair<K,V>) o;
        return (key.equals(other.key) && value.equals(other.value));

    }

}