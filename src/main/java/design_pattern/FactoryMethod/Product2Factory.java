package design_pattern.FactoryMethod;

/**
 * @author Fighter Created on 2018/10/13.
 */
public class Product2Factory implements Factory {
    @Override
    public Product createProduct() {
        return new Product2();
    }
}
