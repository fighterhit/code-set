package concurrence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如何实现一个线程池
 * https://blog.csdn.net/z55887/article/details/79060070
 */
public class MyThreadPool {

    class MyThread extends Thread {
        private Runnable task;

        public MyThread(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //该线程一直启动着，不断从任务队列取出任务执行
            while (true) {
                //如果初始化任务不为空，则执行初始化任务
                if (task != null) {
                    task.run();
                    task = null;
                }
                //否则去任务队列取任务并执行
                else {
                    Runnable task = taskQueue.poll();
                    if (task != null) {
                        task.run();
                    }
                }
            }
        }
    }

    //存放线程的集合
    private List<MyThread> threads;
    //任务队列
    private BlockingQueue<Runnable> taskQueue;
    //先测池初始大小
    private int threadNum;
    private int workThreadNum;
    private ReentrantLock lock = new ReentrantLock();

    public MyThreadPool(int initPoolNum) {
        threadNum = initPoolNum;
        workThreadNum = 0;

        threads = new ArrayList<>(initPoolNum);
        //任务队列初始化为线程池线程数的四倍
        taskQueue = new ArrayBlockingQueue<>(4 * initPoolNum);
    }

    public void execute(Runnable runnable) {
        try {
            lock.lock();
            if (workThreadNum < threadNum) {
                MyThread t = new MyThread(runnable);
                t.start();
                threads.add(t);
                workThreadNum++;
            } else {
                if (!taskQueue.offer(runnable)) {
                    rejectTask();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private void rejectTask() {
        System.out.println("任务队列满，无法继续添加，请扩大线程数量!");
    }

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(5);
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName() + " 执行中... ");
        for (int i = 0; i < 20; i++) {
            myThreadPool.execute(runnable);
        }

    }
}
