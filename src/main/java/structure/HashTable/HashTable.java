package structure.HashTable;

import java.util.TreeMap;

public class HashTable<K, V> {

    private TreeMap<K, V>[] hashTable;
    private int M;
    private int size;

    private HashTable(int M) {
        this.M = M;
        size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashTable[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(97);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V val) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, val);
        } else {
            map.put(key, val);
            size++;
        }
    }

    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    public V remove(K key) {
        V ret = null;
        if (contains(key)) {
            ret = hashTable[hash(key)].remove(key);
            size--;
            return ret;
        }
        return ret;
    }

    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }

    public void set(K key, V val) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        map.put(key, val);
    }
}
