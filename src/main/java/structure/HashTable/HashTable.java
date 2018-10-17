package structure.HashTable;

import java.util.TreeMap;

public class HashTable<K, V> {

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;

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

            //扩容
            if (size > upperTol * M) {
                resize(2 * M);
            }
        }
    }

    private void resize(int newCap) {

        TreeMap<K, V>[] newHashTable = new TreeMap[newCap];
        for (int i = 0; i < newCap; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        int oldM = M;
        this.M = newCap;

        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashTable[i];
            for (K k : map.keySet()) {
                newHashTable[hash(k)].put(k, map.get(k));
            }
        }
        this.hashTable = newHashTable;
    }

    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    public V remove(K key) {
        V ret = null;
        if (contains(key)) {
            ret = hashTable[hash(key)].remove(key);
            size--;
            if (size < lowerTol * M && M / 2 > 0) {
                resize(M / 2);
            }
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
