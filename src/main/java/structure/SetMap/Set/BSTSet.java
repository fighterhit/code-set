package structure.SetMap.Set;

import structure.BinarySearchTree.BST;
import structure.SetMap.FileOperation;

import java.util.ArrayList;

/**
 * 二分搜索树实现的集合 Set
 *
 * @author Fighter Created on 2018/10/2.
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    BST<E> bst = new BST<>();

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        if (FileOperation.readFile("G:\\IdeaProjects\\code-set\\src\\main\\java\\structure\\SetMap\\pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            BSTSet<String> set1 = new BSTSet<>();
            for (String word : words1) {
                set1.add(word);
            }
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();


        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        if (FileOperation.readFile("G:\\IdeaProjects\\code-set\\src\\main\\java\\structure\\SetMap\\a-tale-of-two-cities.txt", words2)) {
            System.out.println("Total words: " + words2.size());

            BSTSet<String> set2 = new BSTSet<>();
            for (String word : words2) {
                set2.add(word);
            }
            System.out.println("Total different words: " + set2.getSize());
        }
    }
}
