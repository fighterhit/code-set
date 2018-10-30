package design_pattern.State;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        RoomContext context = new RoomContext();
        context.setState(new FreeState());
        context.setState(new BookedState());
        context.setState(new CheckedInState());
    }
}
