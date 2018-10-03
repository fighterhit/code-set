package structure.SetMap.Map;

/**
 * 二分搜索树实现的映射 Map
 *
 * @author Fighter Created on 2018/10/3.
 */
//K 要是可比较的
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        this.root = null;
        this.size = size;
    }

    @Override
    public void add(K key, V value) {

    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public void set(K key, V newValue) {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
