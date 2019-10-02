package NewCoderProblemSet.Other.alibaba.P1;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多个线程顺序循环打印递增的自然数，例如 3 个线程：t-0，t-1，t-2，程序输出如下：
 * t-0 0
 * t-1 1
 * t-2 2
 * t-0 3
 * t-1 4
 * t-2 5
 */
public class Main {
    static class PrintBySemaphore implements Runnable {
        private String threadName;
        private Semaphore pre, cur;
        private AtomicInteger cnt;

        public PrintBySemaphore(String threadName, Semaphore pre, Semaphore cur, AtomicInteger cnt) {
            this.threadName = threadName;
            this.pre = pre;
            this.cur = cur;
            this.cnt = cnt;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    pre.acquire();
                    System.out.println(threadName + " " + cnt.getAndIncrement());
                    cur.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            Semaphore[] semaphores = new Semaphore[n];
            AtomicInteger cnt = new AtomicInteger();
            for (int i = 0; i < n; i++) {
                semaphores[i] = i != n - 1 ? new Semaphore(0) : new Semaphore(1);
            }
            for (int i = 0; i < n; i++) {
                Semaphore pre = i == 0 ? semaphores[n - 1] : semaphores[i - 1], cur = semaphores[i];
                new Thread(new PrintBySemaphore("t-" + i, pre, cur, cnt)).start();
            }
        }
    }

    static class PrintByBoolean {

        private static Boolean[] cur;
        private static AtomicInteger cnt;
        private static int n;

        public PrintByBoolean(Boolean[] flag, AtomicInteger count) {
            cur = flag;
            cnt = count;
        }

        public synchronized void print(int i, String threadName) {
            try {
                while (true) {
                    if (i == 0) {
                        while (!cur[n - 1]) {
                            wait();
                        }
                    } else {
                        while (!cur[i - 1]) {
                            wait();
                        }
                    }
                    System.out.println(threadName + " " + cnt.getAndIncrement());
                    if (cnt.get() > 100) {
                        System.exit(0);
                    }
                    if (i == 0)
                        cur[n - 1] = false;
                    else
                        cur[i - 1] = false;
                    cur[i] = true;
                    notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        static class MyThread extends Thread {
            private String name;
            private int index;
            private PrintByBoolean p;

            public MyThread(String name, int index, PrintByBoolean p) {
                this.name = name;
                this.index = index;
                this.p = p;
            }

            @Override
            public void run() {
                p.print(index, name);
            }
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            cur = new Boolean[n];
            cnt = new AtomicInteger();
            for (int i = 0; i < n; i++) {
                cur[i] = i != n - 1 ? new Boolean(false) : new Boolean(true);
            }
            PrintByBoolean p = new PrintByBoolean(cur, cnt);
            for (int i = 0; i < n; i++) {
                new MyThread("t-" + i, i, p).start();
            }
        }
    }

}
