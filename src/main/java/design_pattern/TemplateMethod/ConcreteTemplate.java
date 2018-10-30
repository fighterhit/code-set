package design_pattern.TemplateMethod;

/**
 * @author Fighter.
 */
public class ConcreteTemplate extends TemplateMethod {
    @Override
    public void transact() {
        System.out.println("取款！");
    }
}

class ConcreteTemplate2 extends TemplateMethod {
    @Override
    public void transact() {
        System.out.println("存款！");
    }
}
