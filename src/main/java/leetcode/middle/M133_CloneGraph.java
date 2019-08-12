package leetcode.middle;

import java.util.*;

/**
 * 给定无向连通图中一个节点的引用，返回该图的深拷贝（克隆）。图中的每个节点都包含它的值 val（Int） 和其邻居的列表（list[Node]）。
 * https://www.cnblogs.com/grandyang/p/4267628.html
 * https://www.youtube.com/watch?v=kDN6d3Ldsu0
 */
public class M133_CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    Map<Node, Node> map = new HashMap<>();

    //先克隆所有节点，再连接边，比第二种方法慢
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (node.neighbors.size() == 0) {
            return new Node(node.val, new ArrayList<>());
        }
        //克隆所有节点
        dfs(node);
        for (Map.Entry<Node, Node> entry : map.entrySet()) {
            Node src = entry.getKey();
            Node dst = entry.getValue();
            List<Node> nbs = dst.neighbors;
            for (Node neighbor : src.neighbors) {
                nbs.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }

    private void dfs(Node node) {
        for (Node neighbor : node.neighbors) {
            if (!map.containsKey(neighbor)) {
                Node copy = new Node(neighbor.val, new ArrayList<>());
                map.put(neighbor, copy);
                dfs(neighbor);
            }
        }
    }

    public Node cloneGraph2(Node node) {
        if (node == null) {
            return null;
        }
        return dfs2(node);
    }

    private Node dfs2(Node node) {
        if (node == null) {
            return null;
        }
        //如果已被复制过，则直接返回
        if (map.containsKey(node)) {
            return map.get(node);
        }
        List<Node> ns = new ArrayList<>();
        Node copy = new Node(node.val, ns);
        //注意要先放入map，否则无法利用递归第二个边界条件，导致无法退出无限制递归
        map.put(node, copy);
        for (Node neighbor : node.neighbors) {
            //注意添加复制后(递归)的邻居节点
            ns.add(dfs2(neighbor));
        }
        return copy;
    }

    //BFS：一层一层克隆，需要借助队列，和法一类似，比法二慢
    public Node cloneGraph3(Node node) {
        if (node == null) {
            return null;
        }
        if (node.neighbors.size() == 0) {
            return new Node(node.val, new ArrayList<>());
        }

        Node clone = new Node(node.val, new ArrayList<>());
        map.put(node, clone);

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        while (queue.size() > 0) {
            Node cur = queue.poll();
            //遍历 cur 邻居节点，若 map 不存在此节点，则
            for (Node neighbor : cur.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.add(neighbor);
                }
                map.get(cur).neighbors.add(map.get(neighbor));
            }
        }
        return clone;
    }
}
