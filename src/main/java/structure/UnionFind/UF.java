package structure.UnionFind;

/**
 * @author Fighter Created on 2018/10/8.
 */
public interface UF {
    void unionElements(int p, int q);

    boolean isConnected(int p, int q);

    int getSize();
}
