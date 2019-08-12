package NewCoderProblemSet.Other.MGeeker.P6;

/**
 * 小招的练习
 * T本练习册，编号 1 到 T，接下来的 N 分钟计划表，初始为空，现要对计划进行 M 次操作
 * 第一种操作：1 L R v，表示将计划上 L 分钟到 R 分钟都加上一项任务：做 v 号练习册
 * 第二种操作：2 L R v，表示将计划上 L 分钟到 R 分钟都删掉做 v 号练习册的计划
 * 第三种操作：0 L R，表示询问计划的 L 分钟到 R 分钟中，共做了多少本不同练习册。
 * <p>
 * 第一行 N M T
 * 接下来 M 行，每行第一个数 opt
 * 若 opt = 1 或者 2,则再输入3个数 L R v，否则 opt = 0，输入两个数 L R
 * 输出包含若干行，对于每个 opt = 0 的询问
 * 3 4 2
 * 1 1 2 2
 * 2 2 3 2
 * 0 1 2
 * 0 2 3
 */
public class Main {
    public static void main(String[] args) {

    }
}