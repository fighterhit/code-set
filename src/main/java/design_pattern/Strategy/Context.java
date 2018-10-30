package design_pattern.Strategy;

/**
 * 负责和具体策略类交互
 * 这样的话，具体策略（算法）和客户端分离，使算法可以独立于客户端变化
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execStrategy() {
        strategy.getStrategy();
    }
}
