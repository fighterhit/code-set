package structure.AVL;

import structure.SetMap.Set.Set;

public class AVLSet<K extends Comparable<K>> implements Set<K> {

    private AVLTree<K, Object> avl;

    public AVLSet() {
        avl = new AVLTree<>();
    }

    @Override
    public void add(K k) {
        avl.add(k, null);
    }

    @Override
    public void remove(K k) {
        avl.remove(k);
    }

    @Override
    public boolean contains(K k) {
        return avl.contains(k);
    }

    @Override
    public int getSize() {
        return avl.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }
}
