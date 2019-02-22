package sword.ch2;

/**
 * 单例模式
 * 定义：指实现了特殊模式的类，该类仅能被实例化一次，产生唯一的一个对象
 * 应用举例：windows的任务管理器，回收站，web应用的配置对象，spring中的bean默认也是单例
 * 分类：饿汉式，懒汉式，双检锁，静态内部类，枚举
 * 评价指标有：单例（必须），线程安全，延迟加载，防止反序列化产生新对象，防止反射攻击
 * 实现方法的选择：一般情况下直接使用饿汉式就好了，要求延迟加载时倾向于用静态内部类，涉及到反序列化创建对象或反射问题最好选择枚举
 */


/**
 * 懒汉式-线程不安全，只适用单线程
 */
class Singleton1 {
    private Singleton1() {
    }

    private static Singleton1 instance;

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}

/**
 * 懒汉式-线程安全（synchronized同步方法，支持多线程）
 * 当一个线程进入该方法之后，其它线程试图进入该方法都必须等待，因此性能上有一定的损耗
 */
class Singleton2 {
    private Singleton2() {
    }

    private static Singleton2 instance;

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

/**
 * 懒汉式-线程安全（synchronized同步块，支持多线程）
 * 当一个线程进入该方法之后，其它线程试图进入该方法都必须等待，因此性能上有一定的损耗
 */

class Singleton3 {
    private Singleton3() {
    }

    private static Singleton3 instance;

    public static Singleton3 getInstance() {
        synchronized (Singleton3.class) {
            if (instance == null) {
                instance = new Singleton3();
            }
        }
        return instance;
    }
}


/**
 * 饿汉式-线程安全
 * 在类初始化执行到静态属性时就分配了资源，有资源浪费问题，丢失了延迟实例化带来的节约资源的优势。
 */
class Singleton4 {
    private Singleton4() {
    }

    private static Singleton4 instance = new Singleton4();

    public static Singleton4 getInstance() {
        return instance;
    }
}

/**
 * 双重校验锁-线程安全（优点是只在第一次实例化时加锁，之后不会加锁，提升了效率，缺点写法复杂）
 * <p>
 * instance = new Singleton5(); 这段代码其实是分为三步执行：
 * 1. 分配内存空间。
 * 2. 初始化对象。
 * 3. 将 uniqueInstance 指向分配的内存地址。
 * <p>
 * 但是由于 JVM 具有指令重排的特性，有可能执行顺序变为了 1>3>2，这在单线程情况下自然是没有问题。但如果是多线程就有可能 B 线程获得是一个还没有被初始化的对象以致于程序出错。
 * 所以使用 volatile 修饰的目的是禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
 */
class Singleton5 {
    private Singleton5() {
    }

    // volatile 很关键
    private volatile static Singleton5 instance;

    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}

/**
 * 静态内部类，支持多线程-懒汉式
 * 特点：利用静态内部类（只有在出现它的引用时才被加载），完成懒加载；final保证线程安全;
 * final的作用:
        * //1. 在构造函数内对一个final域的写入，与随后把这个被构造对象的引用赋值给一个引用变量，这两个操作之间不能重排序。
        * //2. 初次读一个包含final域的对象的引用，与随后读这个final域，这两个操作之间不能重排序。
 *
        * //扩展：static变量初始化遵循以下规则:
        * //1.静态变量会按照声明的顺序先依次声明并设置为该类型的默认值，但不赋值为初始化的值。
        * //2.声明完毕后,再按声明的顺序依次设置为初始化的值，如果没有初始化的值就跳过。
        * //static变量初始化参考：http://www.jb51.net/article/86629.htm
 */
class Singleton6 {
    private Singleton6() {
    }

    public static Singleton6 getInstance() {
        return Singleton6Holder.instance;
    }

    private static class Singleton6Holder {
        // 注意 final
        public static final Singleton6 instance = new Singleton6();
    }

}

/**
 * 通过枚举实现
 * 一个完美的单例需要做到：单例，懒加载，线程安全，防止反序列化产生新对象，防止反射攻击
 * 枚举的特性保证了以上除了懒加载以外的所有要求，而且实现代码及其简单
 */

enum Singleton7{
    instance;
    private String attribute;
    void setAttribute(String attribute){
        this.attribute = attribute;
    }
    String getAttribute(){
        return this.attribute;
    }
}

public class P32_Singleton {

    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getInstance();
        Singleton2 singleton2 = Singleton2.getInstance();
        Singleton3 singleton3 = Singleton3.getInstance();
        Singleton4 singleton4 = Singleton4.getInstance();
        Singleton5 singleton5 = Singleton5.getInstance();
        Singleton6 singleton6 = Singleton6.getInstance();
        Singleton7 singleton7 = Singleton7.instance;
        Singleton7 singleton77 = Singleton7.instance;
        singleton7.setAttribute("aaa");
        singleton77.setAttribute("bbb");
        System.out.println(singleton7 == singleton77);
        System.out.println(singleton7.getAttribute());
    }
}

