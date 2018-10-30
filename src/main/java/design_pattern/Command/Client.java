package design_pattern.Command;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        Command c = new ConcreteCommand(new Receiver());
        Invoke invoke = new Invoke(c);
        invoke.call();
    }
}
