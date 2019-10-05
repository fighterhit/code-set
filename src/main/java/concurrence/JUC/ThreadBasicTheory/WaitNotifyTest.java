package concurrence.JUC.ThreadBasicTheory;

public class WaitNotifyTest {
    private static volatile Object resA = new Object();

    public static void main(String[] args) throws InterruptedException {
        //创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取resA共享资源的监视器锁
                synchronized (resA) {
                    System.out.println("threadA get resA lock");
                    try {
                        System.out.println("threadA begin wait");
                        resA.wait(3000);
                        System.out.println("threadA end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //创建线程
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resA) {
                    System.out.println("threadB get resA lock");
                    try {
                        System.out.println("threadB begin wait");
                        resA.wait();
                        System.out.println("threadB end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resA) {
                    System.out.println("threadC begin notify");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    resA.notifyAll();
                    System.out.println("threadC end notify");
                }
            }
        });

        threadA.start();
        threadB.start();

        Thread.sleep(1000);
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main over");
    }
}
