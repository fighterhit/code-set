package concurrence.JUC.ThreadBasicTheory;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadTest {
    //继承 Thread 类并重写 run 方法
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }

    public static class RunnableTask implements Runnable {
        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }

    public static class CallerTask implements Callable<String> {
        @Override
        public String call() {
            return "hello";
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();

        RunnableTask task = new RunnableTask();
        new Thread(task).start();
        new Thread(task).start();

        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();
        try {
            //等待任务执行完毕并返回结果
            String res = futureTask.get();
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}