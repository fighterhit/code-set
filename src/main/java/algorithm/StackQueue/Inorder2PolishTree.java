package algorithm.StackQueue;

import java.util.Objects;
import java.util.Stack;

/**
 * 中缀表达式转后缀表达式
 * 中缀表达式a + b*c + (d * e + f) * g，其转换成后缀表达式则为a b c * + d e * f  + g * +
 * <p>
 * 转换过程需要用到栈，具体过程如下：
 * 1）如果遇到操作数，我们就直接将其输出。
 * 2）如果遇到操作符，则我们将其放入到栈中，遇到左括号时我们也将其放入栈中。
 * 3）如果遇到一个右括号，则将栈元素弹出，将弹出的操作符输出直到遇到左括号为止。注意，左括号只弹出并不输出。
 * 4）如果遇到任何其他的操作符，如（“+”， “*”，“（”）等，从栈中弹出元素直到遇到发现更低优先级的元素(或者栈为空)为止。弹出完这些元素后，才将遇到的操作符压入到栈中。有一点需要注意，只有在遇到" ) "的情况下我们才弹出" ( "，其他情况我们都不会弹出" ( "。
 * 5）如果我们读到了输入的末尾，则将栈中所有元素依次弹出。
 * <p>
 * <p>
 * 方法一：利用表达式树
 * 首先将中缀表达式转换为表达式树，然后后序遍历表达式树，所得结果就是后缀表达式。
 * <p>
 * 将中缀表达式转化为表达式树方法：表达式树的树叶是操作数，而其他的节点为操作符，根节点为优先级最低且靠右的操作符（如上述表达式优先级最低的是- 和+，但 + 更靠右，所以根为+），圆括号不包括。如上述中缀表达式转换后的表达式树如下：
 * <p>
 * <p>
 * 遇到操作数                        入栈
 * 遇到左括号 (                      入栈
 * 遇到 + - * / 操作符且栈为空        入栈
 * <p>
 * 遇到右括号                        出栈，输出出栈元素直到出栈元素是左括号（左括号不输出）
 * 遇到其它运算符 + -* /              弹出所有优先级大于等于当前运算符的栈顶元素，然后将该元素入栈
 * <p>
 * 最终依次输出栈中元素
 * <p>
 * https://www.cnblogs.com/hantalk/p/8734511.html
 * https://blog.csdn.net/wypblog/article/details/7270276
 * https://blog.51cto.com/11290909/2085571
 * https://www.cnblogs.com/Agent-YRBlogs/p/6030764.html
 */
public class Inorder2PolishTree {

    private String testString = null;
    private Stack<Character> stack = null;

    /**
     * @author 397090770
     */
    public Inorder2PolishTree(String testString) {
        this.testString = testString;
        this.stack = new Stack<>();
    }

    private void analysisString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < testString.length(); i++) {
            char c = testString.charAt(i);
            //操作数
            if (Character.isLetter(c)) {
                ret.append(c);
            }
            //+ - 同优先级
            else if (c == '+' || c == '-') {
                //栈为空 或者 栈内为 '('，注意 '(' 遇到 ')' 才弹出，其它情况都不弹出
                if (stack.isEmpty() || stack.peek() == '(') {
                    stack.push(c);
                }
                //弹出栈内优先级大于等于当前运算符的符号
                else {
                    while (!stack.isEmpty() && (stack.peek() == '+' || stack.peek() == '-' || stack.peek() == '*' || stack.peek() == '/')) {
                        ret.append(stack.pop());
                    }
                    stack.push(c);
                }
            }
            //* / 同优先级
            else if (c == '*' || c == '/') {
                if (stack.isEmpty() || stack.peek() == '(') {
                    stack.push(c);
                } else {
                    while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                        ret.append(stack.pop());
                    }
                    stack.push(c);
                }
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (stack.peek() != '(') {
                    ret.append(stack.pop());
                }
                //弹出(
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            ret.append(stack.pop());
        }
        System.out.println(ret.toString());
    }

    public static void main(String[] args) {
//        Inorder2PolishTree testStacknew = new Inorder2PolishTree("A+B*(C-D)/E+F/H");// ABCD-*E/+FH/+
        Inorder2PolishTree testStacknew = new Inorder2PolishTree("A+(B-D)*C");//ABD-C*+
        testStacknew.analysisString();
    }
}

/**
 * https://blog.csdn.net/wow_bc/article/details/71440471
 */
class Node {
    char val;
    Node left;
    Node right;

    public Node(char val) {
        this.val = val;
    }
}

class Main {
    public static Node buildTree(String str) {
        Node node = null;
        if (str.length() == 1) {
            node = new Node(str.charAt(0));
            return node;
        }
        int p = 0, c1 = -1, c2 = -1;            //p,c1,c2分别存放未匹配的括号数量，最右出现的+-号下标，最右出现的*/号下标
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '(':
                    p++;
                    break;
                case ')':
                    p--;
                    break;
                case '+':
                case '-':
                    if (p == 0)
                        c1 = i;
                    break;
                case '*':
                case '/':
                    if (p == 0)
                        c2 = i;
                    break;
            }
        }
        if (c1 < 0 && c2 < 0)            //整个表达式是被一对括号括起来的
            node = buildTree(str.substring(1, str.length() - 1));
        else if (c1 > 0) {
            node = new Node(str.charAt(c1));
            node.left = buildTree(str.substring(0, c1));
            node.right = buildTree(str.substring(c1 + 1, str.length()));
        } else {
            node = new Node(str.charAt(c2));
            node.left = buildTree(str.substring(0, c2));
            node.right = buildTree(str.substring(c2 + 1, str.length()));
        }
        return node;
    }

    public static void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val);
    }

    public static void main(String[] args) {
        Node root = buildTree("A+(B-D)*C");
        postOrder(root);
        /*Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            Node root = buildTree(str);
            postOrder(root);
            System.out.println();
        }
        scanner.close();*/
    }
}

/**
 * https://www.davex.pw/2016/03/21/How-to-make-a-Expression-Tree/
 */
class ExprTree {

    class ExprTreeNode {
        String key;
        ExprTreeNode left, right;

        ExprTreeNode(String pKey) {
            this(pKey, null, null);
        }

        ExprTreeNode(String pKey, ExprTreeNode pt1, ExprTreeNode pt2) {
            key = new String(pKey);
            left = pt1;
            right = pt2;
        }
    }

    protected ExprTreeNode root;

    /**
     * Init the expression and transform it into binary tree
     *
     * @param expr
     * @throws Exception 如果抛出异常则说明该输入有误
     */
    ExprTree(String expr) throws Exception {
        String[] tokens = expr.split(" ");
        // 用" "来分割字符串
        Stack<String> operStack = new Stack<String>();
        // 操作符(operator)的Stack，主要解决脱括号的问题
        Stack<ExprTreeNode> dataStack = new Stack<ExprTreeNode>();
        // 二叉树节点的Stack

        for (int i = 0; i < tokens.length; i++) {
            if (isNumber(tokens[i])) {
                // 如果是数字，则创建操作数节点压栈
                ExprTreeNode _node = new ExprTreeNode(tokens[i]);
                dataStack.push(_node);
            } else if (isOper(tokens[i])) {
                // 判断是否为操作符
                switch (tokens[i]) {
                    case "(": // 进栈
                        operStack.push(tokens[i]);
                        break;
                    case ")": // 脱括号
                        while (true) {
                            String c = operStack.pop();
                            if (Objects.equals(c, "(")) break;
                            // 一直创建节点，直到遇到最后插入的"("与之相匹配
                            ExprTreeNode _node = new ExprTreeNode(c);

                            if (dataStack.size() > 0) _node.right = dataStack.pop();
                            if (dataStack.size() > 0) _node.left = dataStack.pop();

                            dataStack.push(_node);
                        }
                        break;
                    default:
                        while (true) {
                            // while循环是要保证当前的操作符一定要入栈
                            if (operStack.size() == 0 || getOperPri(operStack.lastElement()) < getOperPri(tokens[i])) {
                                // 如果operStack为空，则说明没有操作符
                                // 或者当前的操作符的优先级高于栈顶的操作符
                                // 其中优先级：* = / > + = - > (
                                // 那么我们就要把当前的操作符压入栈
                                operStack.push(tokens[i]);
                                break;
                            } else {
                                // 如果栈顶操作符的优先级低于当前操作符
                                // 我们就要把它取出来做成一个二叉树节点压入栈中
                                String oper = operStack.pop();
                                ExprTreeNode _node = new ExprTreeNode(oper);

                                // 判断dataStack内是否有数据，有则pop出一个当右节点
                                if (dataStack.size() > 0) _node.right = dataStack.pop();
                                // 判断dataStack内是否有数据，有则pop出一个当左节点
                                if (dataStack.size() > 0) _node.left = dataStack.pop();
                                // 把这个做好的二叉树节点压回栈
                                dataStack.push(_node);
                            }
                        }
                        break;
                }
            } else {
                // 既不是数字也不是操作符，则为非法字符抛出异常格式错误
                if (tokens[i].trim().length() > 0) throw new Exception("Wrong Format: " + tokens[i]);
            }
        }

        while (!operStack.empty()) {
            // 进过上面的预处理，操作符栈内还是有可能有操作符的
            // 因此我们要把所有的操作符取出来完成一个完整的二叉树
            String oper = operStack.pop();
            ExprTreeNode _node = new ExprTreeNode(oper);

            if (dataStack.size() > 0) _node.right = dataStack.pop();
            if (dataStack.size() > 0) _node.left = dataStack.pop();
            dataStack.push(_node);
        }

        // dataStack所pop出来的第一个即为根节点(root node)
        root = dataStack.pop();
    }

    /**
     * to tell whether the op is an operator
     *
     * @param op
     * @return true when it is an operator
     */
    private Boolean isOper(String op) {
        String[] oper = {"(", ")", "+", "-", "*", "/"};
        for (String anOper : oper)
            if (Objects.equals(op, anOper)) return true;
        return false;
    }

    /**
     * to calculate the priority of the operator
     *
     * @param op
     * @return the priority of the operator
     */
    private int getOperPri(String op) {
        switch (op) {
            case "(":
                return 1;
            case "+":
            case "-":
                return 2;
            case "*":
            case "/":
                return 3;
            default:
                return 0;
        }
    }

    public static boolean isNumber(String str) {
        // 判断是否为数字
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}