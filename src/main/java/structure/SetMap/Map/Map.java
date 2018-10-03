package structure.SetMap.Map;

/**
 * @author Fighter Created on 2018/10/3.
 */
public interface Map<K, V> {

    void add(K key, V value);

    V remove(K key);

    V get(K key);

    boolean contains(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();

}
