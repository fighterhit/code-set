package design_pattern.Composite;

/**
 * @author Fighter.
 */
public interface Compoment {
    void operation();
}

//叶子组件
interface Leaf extends Compoment {
}

interface Composite extends Compoment {
    void add(Compoment c);

    void remove(Compoment c);

    Compoment getChild(int index);
}