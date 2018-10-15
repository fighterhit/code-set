package design_pattern.Singleton.impl1;

/**
 * 懒汉式-线程不安全
 *
 * @author Fighter Created on 2018/10/11.
 */
public class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            return new Singleton();
        }
        return instance;
    }
}
