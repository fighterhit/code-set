package design_pattern.Adapter;

/**
 * 适配器-类适配器实现方式
 * （相当于usb和ps/2转接器）
 * @author Fighter.
 */
public class Adapter extends Adaptee implements Target{

    @Override
    public void handleReq() {
        System.out.println("类适配器实现方式");
        super.request();
    }
}

/**
 * 对象适配方式-使用组合方式跟被适配对象整合
 */
class Adapter2 implements Target{

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