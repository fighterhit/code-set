package design_pattern.Strategy;

/**
 * @author Fighter.
 */
public interface Strategy {
    void getStrategy();
}

class ConcreteStrategy1 implements Strategy {
    @Override
    public void getStrategy() {
        System.out.println("策略1");
    }
}

class ConcreteStrategy2 implements Strategy {
    @Override
    public void getStrategy() {
        System.out.println("策略2");
    }
}