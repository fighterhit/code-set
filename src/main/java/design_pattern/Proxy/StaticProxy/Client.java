package design_pattern.Proxy.StaticProxy;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        Star real = new RealStar();
        Star proxy = new ProxyStar(real);
        proxy.confer();
        proxy.sing();
        proxy.getMoney();
    }
}
