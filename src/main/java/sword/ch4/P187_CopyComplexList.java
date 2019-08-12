package sword.ch4;


import java.util.HashMap;
import java.util.Map;

/**
 * M138_CopyListwithRandomPointer
 */
public class P187_CopyComplexList {
    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    //空间换时间：O(n)
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode p = pHead;

        RandomListNode tmpHead = new RandomListNode(-1);
        RandomListNode q = tmpHead;
        //复制next线路
        while (p != null) {
            RandomListNode node = new RandomListNode(p.label);
            node.next = p.next;
            map.put(p, node);
            q.next = node;

            p = p.next;
            q = node;
        }
        p = pHead;
        //复制random
        while (p != null) {
            if (p.random != null) {
                RandomListNode newNode = map.get(p);
                //注意
                newNode.random = map.get(p.random);
            }
            p = p.next;
        }
        return tmpHead.next;
    }

    //将每个节点复制后放其后面
    public RandomListNode Clone2(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode p = pHead;
        while (p != null) {
            RandomListNode copyNode = new RandomListNode(p.label);
            copyNode.next = p.next;
            p.next = copyNode;
            p = copyNode.next;
        }
        p = pHead;
        //复制random指针
        while (p != null) {
            if (p.random != null) {
                RandomListNode pRandom = p.random;
                RandomListNode copyNode = p.next;
                copyNode.random = pRandom.next;
            }
            p = p.next.next;
        }
        p = pHead;
        RandomListNode newHead = pHead.next, q = pHead.next;
        //分离新旧链表，注意用q.next判断，将链表尾设为null
        while (q.next != null) {
            //旧链表
            p.next = q.next;
            p = p.next;
            //新链表
            q.next = q.next.next;
            q = q.next;
        }
        //旧链表置为null
        p.next = null;
        return newHead;
        /*RandomListNode head = pHead.next;
        RandomListNode q = head;
        p = pHead;
        //这个部分需要注意一下
        while (q.next != null) {
            p.next = q.next;
            p = p.next;

            q.next = p.next;
            q = q.next;
        }
        p.next = null;  //最后将原来链表的尾部设置为null
        return head;*/
    }
}
