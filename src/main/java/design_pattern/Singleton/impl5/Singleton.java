package design_pattern.Singleton.impl5;

/**
 * 静态内部类-线程安全、调用效率高、懒加载
 *
 * 只有当调用 getInstance() 方法从而触发 SingletonHolder.INSTANCE 时 SingletonHolder 才会被加载，此时初始化 INSTANCE 实例，并且 JVM 能确保 INSTANCE 只被实例化一次。类加载天然线程安全。
 *
 * @author Fighter Created on 2018/10/13.
 */
public class Singleton {

    private Singleton() {
    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
