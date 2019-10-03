package concurrence.JUC.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 在某种程度上实现了 CyclicBarrier 的复用功能
 */
public class SemaphoreTest2 {
    private static volatile Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " A task over");
                semaphore.release();
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " A task over");
                semaphore.release();
            }
        });
        //等待子线程执行任务A完毕，返回
        semaphore.acquire(2);

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " B task over");
                semaphore.release();
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " B task over");
                semaphore.release();
            }
        });
        //等待子线程执行B完毕，返回
        semaphore.acquire(2);
        System.out.println("task is over");
        //关闭线程池
        executorService.shutdown();
    }
}
