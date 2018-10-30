package design_pattern.ChainOfResponsibility;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1(null);
        Handler handler2 = new ConcreteHandler2(handler1);

        Request request1 = new Request(RequestType.TYPE1, "req1");
        Request request2 = new Request(RequestType.TYPE2, "req2");

        handler2.handleRequest(request1);
        handler2.handleRequest(request2);
    }
}
