package leetcode.easy;

import java.util.concurrent.Semaphore;

/**
 * 我们提供了一个类：
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 * <p>
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: "onetwothree"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
 * 正确的输出是 "onetwothree"。
 * <p>
 * 示例 2:
 * 输入: [1,3,2]
 * 输出: "onetwothree"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
 * 正确的输出是 "onetwothree"。
 * 注意:
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
 * 你看到的输入格式主要是为了确保测试的全面性。
 * <p>
 * 注意：三个不同的线程将会共用一个 Foo 实例！！！
 * <p>
 * 参考 NewCoderProblemSet.Other.alibaba.P1
 * 多个线程顺序循环打印递增的自然数，例如 3 个线程：t-0，t-1，t-2，程序输出如下：
 * t-0 0
 * t-1 1
 * t-2 2
 * t-0 3
 * t-1 4
 * t-2 5
 * N个线程循环打印 1-100，
 * https://blog.csdn.net/wz_TXWY/article/details/93518654
 * https://leetcode-cn.com/problems/print-in-order/
 */
public class E1114_PrintinOrder {
    class Foo {
        Semaphore run2, run3;

        public Foo() {
            run2 = new Semaphore(0);
            run3 = new Semaphore(0);
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            run2.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            run2.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            run3.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            run3.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    class Foo2 {
        boolean flag2, flag3;

        public Foo2() {
        }

        public synchronized void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            flag2 = true;
            notifyAll();
        }

        public synchronized void second(Runnable printSecond) throws InterruptedException {
            while (!flag2) {
                wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            flag3 = true;
            notifyAll();
        }

        public synchronized void third(Runnable printThird) throws InterruptedException {
            while (!flag3) {
                wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
