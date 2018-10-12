package design_pattern.Singleton.impl4;

/**
 * 双重校验锁-线程安全
 *
 * @author Fighter Created on 2018/10/12.
 */
public class Singleton {
    private Singleton instance;

    private Singleton() {
    }

    private Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    return new Singleton();
                }
            }
        }
        return instance;
    }
}
