package design_pattern.Singleton;

import design_pattern.Singleton.impl5.Singleton;

import java.util.concurrent.CountDownLatch;

/**
 * 多线程环境测试
 *
 * @author Fighter Created on 2018/10/13.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        int threadNum = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    Object o = Singleton.getInstance();
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start));
    }
}
