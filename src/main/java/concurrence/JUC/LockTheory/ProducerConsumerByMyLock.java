package concurrence.JUC.LockTheory;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;

public class ProducerConsumerByMyLock {
    final static MyLockByAQS lock = new MyLockByAQS();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();

    final static Queue<String> queue = new LinkedBlockingQueue<>();
    final static int QUEUE_SIZE = 10;
    static AtomicInteger i = new AtomicInteger();

    public static void main(String[] args) {
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        while (queue.size() == QUEUE_SIZE) {
                            notEmpty.await();
                        }
                        queue.add("ele " + i.getAndIncrement());
                        notFull.signal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        while (0 == queue.size()) {
                            notFull.await();
                        }
                        String ele = queue.poll();
                        System.out.println(ele);
                        notEmpty.signal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });

        producer.start();
        consumer.start();
        try {
            producer.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}