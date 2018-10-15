package design_pattern.FactoryMethod;

/**
 * @author Fighter Created on 2018/10/13.
 */
public class Client {
    public static void main(String[] args) {
        Product product1 = new Product1Factory().createProduct();
        Product product2 = new Product2Factory().createProduct();
    }
}
