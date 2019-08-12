package concurrence.JUC;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumer {

    private static BlockingQueue<String> queue = new LinkedBlockingDeque<>();

    public static class Producer extends Thread {

        @Override
        public void run() {
            try {
                queue.put("sth");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("produce...");
        }
    }

    public static class Consumer extends Thread{

        @Override
        public void run() {
            try {
                String res = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("consume...");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            new Producer().start();
        }
        for (int i = 0; i < 10; i++) {
            new Consumer().start();
        }
    }
}
