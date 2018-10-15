package design_pattern.AbstractFactory;

/**
 * @author Fighter Created on 2018/10/13.
 */
public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = new FactoryHigh();
        ProductA productA = factory.createProductA();
        ProductB productB = factory.createProductB();
        //do something wiht productA and productB
    }
}
