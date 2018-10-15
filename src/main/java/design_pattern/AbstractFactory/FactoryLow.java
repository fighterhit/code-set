package design_pattern.AbstractFactory;

/**
 * 生产低端产品工厂
 *
 * @author Fighter Created on 2018/10/13.
 */
public class FactoryLow implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ProductALow();
    }

    @Override
    public ProductB createProductB() {
        return new ProductBLow();
    }
}
