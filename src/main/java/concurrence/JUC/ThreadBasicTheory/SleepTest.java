package concurrence.JUC.ThreadBasicTheory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepTest {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("child threadA is in sleep");
                    Thread.sleep(10000);
                    System.out.println("child threadA is in awaked");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("child threadB is in sleep");
                    Thread.sleep(10000);
                    System.out.println("child threadB is in awaked");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        threadA.start();
        threadB.start();

        Thread.sleep(2000);
        threadA.interrupt();

    }
}