package structure.SetMap.Set;

import structure.LinkedList.DummyHeadLinkedList;
import structure.SetMap.FileOperation;

import java.util.ArrayList;

/**
 * 链表实现的集合 Set
 *
 * @author Fighter Created on 2018/10/2.
 */
public class LinkedListSet<E> implements Set<E> {

    private DummyHeadLinkedList<E> list = new DummyHeadLinkedList<>();

    @Override
    public void add(E e) {
        if (!list.contains(e)){
            //复杂度O(1)
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        if (FileOperation.readFile("G:\\IdeaProjects\\code-set\\src\\main\\java\\structure\\SetMap\\pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            LinkedListSet<String> set1 = new LinkedListSet<>();
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

            LinkedListSet<String> set2 = new LinkedListSet<>();
            for (String word : words2) {
                set2.add(word);
            }
            System.out.println("Total different words: " + set2.getSize());
        }
    }

}
