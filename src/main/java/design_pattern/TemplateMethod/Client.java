package design_pattern.TemplateMethod;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        TemplateMethod templateMethod = new ConcreteTemplate();
        templateMethod.process();

        TemplateMethod templateMethod2 = new ConcreteTemplate();
        templateMethod2.process();
    }
}
