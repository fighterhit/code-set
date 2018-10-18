package structure.HashTable;

import java.util.TreeMap;

public class HashTable<K, V> {

    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private int capacityIndex = 0;

    private TreeMap<K, V>[] hashTable;
    private int M;
    private int size;

    private HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashTable[i] = new TreeMap<>();
        }
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
            if (size > upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacityIndex);
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
            if (size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacityIndex);
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
