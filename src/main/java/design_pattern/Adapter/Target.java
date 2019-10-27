package design_pattern.Adapter;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Fighter.
 */
public interface Target {
    void handleReq();
}

/**
 * 被适配的类
 *
 * @author Fighter.
 */
class Adaptee {
    public void request() {
        System.out.println("被适配对象");
    }
}

/**
 * 适配器-类适配器实现方式
 * （相当于usb和ps/2转接器）
 *
 * @author Fighter.
 */
class Adapter extends Adaptee implements Target {

    @Override
    public void handleReq() {
        System.out.println("类适配器实现方式");
        super.request();
    }
}

/**
 * 对象适配方式-使用组合方式跟被适配对象整合
 */
class Adapter2 implements Target {

    private Adaptee adaptee;

    public Adapter2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void handleReq() {
        System.out.println("对象适配方式");
        adaptee.request();
    }
}

class Main {
    public static void main(String[] args) {
        Thread boy = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("吃鸡");
                LockSupport.park();
                System.out.println("boy park1");
                LockSupport.park();
                System.out.println("boy park2");
                System.out.println("boy 开始吃鸡");
            }
        });
        boy.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(14000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("girl: 允许");
                LockSupport.unpark(boy);
                try {
                    Thread.sleep(14000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.unpark(boy);

            }
        }).start();
    }
}