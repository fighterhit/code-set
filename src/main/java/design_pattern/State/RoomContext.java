package design_pattern.State;

/**
 * @author Fighter.
 */
public class RoomContext {

    private State state;

    public RoomContext() {
    }

    public RoomContext(State state) {
        this.state = state;
    }

    public void setState(State state) {
        System.out.println("修改状态ing...");
        this.state = state;
        state.handle();
    }
}
