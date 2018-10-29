package design_pattern.Adapter;

/**
 * 客户端类（相当于案例的笔记本，USB接口）
 *
 * @author Fighter.
 */
public class Client {
    public void process(Target t) {
        t.handleReq();
    }

    public static void main(String[] args) {
        Client client = new Client();
        Target adapter = new Adapter();
        client.process(adapter);

        Adaptee adaptee = new Adaptee();
        Target adapter2 = new Adapter2(adaptee);
        client.process(adapter2);

    }
}
