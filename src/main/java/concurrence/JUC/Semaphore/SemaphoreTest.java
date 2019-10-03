package concurrence.JUC.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 下面例子中主线程中开启两个子线程让它们执行，等所有子线程执行完毕后主线程再继续向下运行
 */
public class SemaphoreTest {

    //初始化信号量计数器的值为 0
    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //线程A添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " over");
                //release()方法相当于让计数器值递增1
                semaphore.release();
            }
        });

        //线程B加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " over");
                semaphore.release();
            }
        });

        /**
         * 等子线程执行完毕，返回
         * 传参为 2 说明调用 acquire 方法的线程会一直阻塞直到信号量计数为 2 才会返回
         */
        semaphore.acquire(2);
        System.out.println("all child thread over!");
        //关闭线程池
        executorService.shutdown();
    }
}
