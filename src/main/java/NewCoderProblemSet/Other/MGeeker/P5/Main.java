package NewCoderProblemSet.Other.MGeeker.P5;

import java.util.Scanner;

/**
 * 树上队列
 * 有颗 n 个节点的树，还有一个大小为 m 的队列。初始队列有 m 个元素，每个元素是一个节点，接下来需要 n-m 次操作，每次操作可将队尾节点出队，并将一个未入过队的节点放队列头。
 * 要求所有时刻（包含起始时刻）时队列中所有节点在树上连通，求合法方案数。
 * 两种方案不同 当且仅当 初始队列不同，或者每一步加入队列的节点不同。
 * 需要对 m = 2，3，... n / 2 都求答案，输出对 998244353 取模
 * <p>
 * 第一行是树的节点数，接下来每行两个数表示边
 * 8
 * 1 2
 * 2 3
 * 2 4
 * 4 5
 * 5 6
 * 6 7
 * 7 8
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}