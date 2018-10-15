package design_pattern.SimpleFactory;

/**
 * @author Fighter Created on 2018/10/13.
 */
public class Client {
    public static void main(String[] args) {

        int type = 1;

        /*
        //以下的 Client 类包含了实例化的代码，这是一种错误的实现。如果在客户类中存在这种实例化代码，就需要考虑将代码放到简单工厂中。
        Product product;
        if (type == 1) {
            product = new Product1();
        } else if (type == 2) {
            product = new Product2();
        } else {
            product = new Product3();
        }*/

        Product product = ProductFactory.createProduct(type);
        // do something...

    }
}
