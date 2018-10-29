package design_pattern.Facade;

/**
 * @author Fighter.
 */
public class SubSystem {

    public void turnOnTV() {
        System.out.println("turnOnTV()");
    }

    public void setCD(String cd) {
        System.out.println("setCD( " + cd + " )");
    }

    public void starWatching() {
        System.out.println("starWatching()");
    }
}