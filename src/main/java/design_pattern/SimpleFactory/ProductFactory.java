package design_pattern.SimpleFactory;

/**
 * @author Fighter Created on 2018/10/13.
 */
public class ProductFactory {
    public static Product createProduct(int productType) {
        switch (productType) {
            case 1:
                return new Product1();
            case 2:
                return new Product2();
            case 3:
                return new Product3();
            default:
                return null;
        }
    }
}
