package design_pattern.Singleton.impl3;

import java.io.Serializable;

/**
 * 懒汉式-线程安全
 *
 * @author Fighter Created on 2018/10/12.
 */
public class Singleton implements Serializable {

    private static Singleton instance;

    private Singleton() {
        //通过手动抛出异常，防止反射构造新实例
        if (instance != null) {
            throw new RuntimeException();
        }
    }

    //方法同步，调用效率低
    private static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    //反序列化时，若定义此函数（实际是一种回调），定义返回哪个对象
    public Singleton readResolve() {
        return instance;
    }
}
