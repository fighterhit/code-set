package design_pattern.Proxy.StaticProxy;

/**
 * @author Fighter.
 */
public class RealStar implements Star {
    @Override
    public void confer() {
        System.out.println("Real Star confer");
    }

    @Override
    public void sing() {
        System.out.println("Real Star sing");
    }

    @Override
    public void getMoney() {
        System.out.println("Real Star getMoney");
    }
}
