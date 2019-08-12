package structure;

/**
 * 汉诺塔中盘的移动次数与个数的问题
 * https://zhidao.baidu.com/question/297878096.html
 * 如果有n个盘的话，那么移动次数为 2的n次方-1
 * 具体证明如下
 * 对于一个单独的塔，可以进行以下操作：
 * 1：将最下方的塔的上方的所有塔移动到过渡柱子
 * 2：将底塔移动到目标柱子
 * 3：将过渡柱子上的其他塔移动到目标柱子
 * 可以归纳出第一步与第三步的步数是一样的，设为a
 * 则总步数为2a+1
 * 可以得到数列
 * An=2A(n-1)+1
 * 最后可算得An是
 * 2的n次方-1
 */
public class Hanoi {

    //n: 表示剩几个圆盘
    private static void move(int n, String src, String buffer, String dst) {
        if (n == 1) {
            System.out.println("from " + src + " to " + dst);
            return;
        }
        move(n - 1, src, dst, buffer);
        move(1, src, buffer, dst);
        move(n - 1, buffer, src, dst);
    }

    public static void main(String[] args) {
        move(3, "H1", "H2", "H3");
    }
}
