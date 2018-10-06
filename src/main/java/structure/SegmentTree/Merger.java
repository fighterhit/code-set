package structure.SegmentTree;

/**
 * @author Fighter Created on 2018/10/6.
 */
public interface Merger<E> {
    E merge(E a, E b);
}
