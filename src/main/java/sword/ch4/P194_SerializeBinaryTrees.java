package sword.ch4;

import sword.TreeNode;

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Fighter.
 */
public class P194_SerializeBinaryTrees {
    StringBuilder sb;
    StringTokenizer st;

    String Serialize(TreeNode root) {
        sb = new StringBuilder();
        if (root == null) {
            return sb.toString();
        }
        SerializeHelper(root, sb);
        return sb.toString();
    }

    private void SerializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("# ");
            return;
        }
        sb.append(root.val + " ");
        SerializeHelper(root.left, sb);
        SerializeHelper(root.right, sb);
    }

    TreeNode Deserialize(String str) {
        st = new StringTokenizer(sb.toString(), " ");
        if (str == null || str.isEmpty()) {
            return null;
        }
        return DeserializeHelper(st);
    }

    private TreeNode DeserializeHelper(StringTokenizer st) {
        if (!st.hasMoreTokens()) {
            return null;
        }
        String val = st.nextToken();
        if ("#".equals(val)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = DeserializeHelper(st);
        node.right = DeserializeHelper(st);
        return node;
    }
}

class SerializeTree {
/*
        String Serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(root.val);
        String left = Serialize(root.left);
        String right = Serialize(root.right);
        sb.append("{").append(left).append(",").append(right).append("}");
        return sb.toString();
    }

    TreeNode Deserialize(String str) {
        if (!str.equals("")) {
            int i = 0;
            while (str.charAt(i) != '{') i++;
            int x = Integer.parseInt(str.substring(0, i));//识别出数字
            TreeNode node = new TreeNode(x);
            if (str.charAt(i + 1) == ',') {//左子树为空
                node.left = null;
                node.right = Deserialize(str.substring(i + 2, str.length() - 1));
            } else {//左子树不空，需要找到左右子树分割点的位置
                int cursor = i + 1;
                while (str.charAt(cursor) != '{') cursor++;
                cursor++;
                int counter = 1;
                while (counter != 0) {//第一次括号实现了完全匹配时counter=0
                    if (str.charAt(cursor) == '{')
                        counter++;
                    if (str.charAt(cursor) == '}')
                        counter--;
                    cursor++;
                }
                node.left = Deserialize(str.substring(i + 1, cursor));
                node.right = Deserialize(str.substring(cursor + 1, str.length() - 1));
            }
            return node;
        }
        return null;
    }*/
    String Serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(root.val);
        String left = Serialize(root.left);
        String right = Serialize(root.right);
        sb.append("{").append(left).append(",").append(right).append("}");
        return sb.toString();
    }

    TreeNode Deserialize(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        int index = str.indexOf("{");
        int rootVal = Integer.valueOf(str.substring(0, index));
        //构建根节点
        TreeNode root = new TreeNode(rootVal);
        //左子树为空
        if (str.charAt(index + 1) == ',') {
            root.left = null;
            root.right = Deserialize(str.substring(index + 2, str.length() - 1));
        }
        //左子树非空，找左右子树分界点
        else {
            int index2 = str.indexOf("{", index + 1);
            index2++;
            int cnt = 1;
            //根据左子树括号匹配，后面的就是右子树
            while (cnt != 0) {
                if (str.charAt(index2) == '{') {
                    cnt++;
                }
                if (str.charAt(index2) == '}') {
                    cnt--;
                }
                index2++;
            }
            root.left = Deserialize(str.substring(index + 1, index2));
            root.right = Deserialize(str.substring(index2 + 1, str.length() - 1));
        }
        return root;
    }

    public static void main(String[] args) {


        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node3;
        node1.right = node2;

        node2.left = null;
        node2.right = null;

        node3.left = null;
        node3.right = node5;

        node4.left = null;
        node4.right = null;

        node5.left = null;
        node5.right = null;

        SerializeTree st = new SerializeTree();
        String s = st.Serialize(node1);
        System.out.println(s);
        String sds = st.Serialize(st.Deserialize(s));
        System.out.println(sds);
        System.out.println(Arrays.toString("a b ".split(" ")));
    }
}
