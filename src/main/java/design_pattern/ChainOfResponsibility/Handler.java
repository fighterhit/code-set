package design_pattern.ChainOfResponsibility;

/**
 * @author Fighter.
 */
public abstract class Handler {

    protected Handler successor;

    public Handler(Handler successor) {
        this.successor = successor;
    }

    protected abstract void handleRequest(Request request);
}

