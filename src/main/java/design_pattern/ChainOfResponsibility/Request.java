package design_pattern.ChainOfResponsibility;

/**
 * @author Fighter.
 */
enum RequestType {
    TYPE1, TYPE2
}

public class Request {
    private RequestType type;
    private String name;

    public Request(RequestType type, String name) {
        this.type = type;
        this.name = name;
    }

    public RequestType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

