package design_pattern.Mediator;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();

        Development development = new Development(mediator);
        Finacial finacial = new Finacial(mediator);

        development.selfAction();
        development.outAction();
    }
}
