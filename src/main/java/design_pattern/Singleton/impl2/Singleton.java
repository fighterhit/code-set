package design_pattern.Singleton.impl2;

/**
 * 饿汉式-线程安全
 *
 * @author Fighter Created on 2018/10/12.
 */
public class Singleton {

    //类初始化时，立即加载这个对象（没有延时加载的优势）。加载类时，天然是线程安全的
    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    //方法没有同步，调用效率高
    public static Singleton getInstance() {
        return instance;
    }
}
