package sword.ch4;

import sword.TreeNode;

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
