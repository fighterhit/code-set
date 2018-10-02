package structure.SetMap.Set;

/**
 * @author Fighter Created on 2018/10/2.
 */
public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}
