package design_pattern.Iterator;

/**
 * 抽象聚合类
 */
public interface Aggregate {
    Iterator createIterator();
}

/**
 * 具体聚合类
 */
class ConcreteAggregate implements Aggregate {

    private Integer[] items;

    public ConcreteAggregate() {
        items = new Integer[10];
        for (int i = 0; i < 10; i++) {
            items[i] = i;
        }
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator<>(items);
    }
}