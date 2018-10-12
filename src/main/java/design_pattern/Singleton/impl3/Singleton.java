package design_pattern.Singleton.impl3;

/**
 * 懒汉式-线程安全
 *
 * @author Fighter Created on 2018/10/12.
 */
public class Singleton {

    private static Singleton instance;

    private Singleton() {
    }

    //方法同步，调用效率低
    private static synchronized Singleton getInstance() {
        if (instance == null) {
            return new Singleton();
        }
        return instance;
    }
}
