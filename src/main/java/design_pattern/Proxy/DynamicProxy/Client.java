package design_pattern.Proxy.DynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        Star realStar = new RealStar();
        StarHandler starHandler = new StarHandler(realStar);

        Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Star.class}, starHandler);
        proxy.confer();
        proxy.sing();
        proxy.getMoney();
    }
}
