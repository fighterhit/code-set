package design_pattern.AbstractFactory;

/**
 * 生产高端产品工厂
 *
 * @author Fighter Created on 2018/10/13.
 */
public class FactoryHigh implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ProductAHigh();
    }

    @Override
    public ProductB createProductB() {
        return new ProductBHigh();
    }
}
