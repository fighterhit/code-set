package design_pattern.Proxy.StaticProxy;

/**
 * @author Fighter.
 */
public class ProxyStar implements Star {

    private Star star;

    public ProxyStar(Star real) {
        star = real;
    }

    @Override
    public void confer() {
        System.out.println("Proxy Star confer");
    }

    @Override
    public void sing() {
        star.sing();
    }

    @Override
    public void getMoney() {
        System.out.println("Proxy Star getMoney");
    }
}
