package design_pattern.TemplateMethod;

/**
 * @author Fighter.
 */
public abstract class TemplateMethod {
    public void takeNumber() {
        System.out.println("排队取号！");
    }

    public abstract void transact();

    public void evalute() {
        System.out.println("反馈评价！");
    }

    public final void process() {
        takeNumber();
        transact();
        evalute();
    }
}
