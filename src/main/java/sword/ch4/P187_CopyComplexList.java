package sword.ch4;


import java.util.HashMap;
import java.util.Map;

public class P187_CopyComplexList {
    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

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
}
