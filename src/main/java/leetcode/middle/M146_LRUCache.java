package leetcode.middle;

import java.util.HashMap;

/**
 * LRU 是 Least Recently Used 的简写，就是最近最少使用的意思。
 * 缓存器主要有两个成员函数，get 和 put.
 * get 函数是通过输入 key 来获得 value，如果成功获得后，这对 (key, value) 升至缓存器中最常用的位置（顶部），如果 key 不存在，则返回 -1。
 * put 函数是插入一对新的 (key, value)，如果原缓存器中有该 key，则需要先删除掉原有的，将新的插入到缓存器的顶部。如果不存在，则直接插入到顶部。若加入新的值后缓存器超过了容量，则需要删掉一个最不常用的值，也就是底部的值。
 */
public class M146_LRUCache {
}

class LRUCache {
    class DLinkedNode {
        int key;
        int val;
        DLinkedNode pre;
        DLinkedNode post;

        public DLinkedNode() {
        }

        public DLinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private HashMap<Integer, DLinkedNode> cache = new HashMap<>();
    private int count;
    private int capacity;
    private DLinkedNode head = new DLinkedNode();
    private DLinkedNode tail = new DLinkedNode();

    public LRUCache(int cap) {
        count = 0;
        capacity = cap;
        head.post = tail;
        tail.pre = head;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode(key, value);

            cache.put(key, newNode);
            //直接添加
            addNode(newNode);
            count++;

            //判断是否超过容量
            if (count > capacity) {
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                count--;
            }
        } else {
            //节点若存在则赋新值，并挪到虚拟头节点后
            node.val = value;
            move2Head(node);
        }
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        move2Head(node);
        return node.val;
    }


    //挪到虚拟头节点后
    private void move2Head(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    //添加节点，直接添加到虚拟头节点后面
    private void addNode(DLinkedNode node) {
        node.post = head.post;
        node.pre = head;
        head.post.pre = node;
        head.post = node;
    }

    //删除并返回最后一个节点
    private DLinkedNode popTail() {
        DLinkedNode res = tail.pre;
        removeNode(res);
        return res;
    }

    //删除链表中的节点，将前驱节点post指针指向孙子节点，孙子节点前驱指针指向前驱节点的前驱节点
    private void removeNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;

        pre.post = post;
        post.pre = pre;
    }
}
