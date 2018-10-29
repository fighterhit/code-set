package design_pattern.Bridge;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        //销售联想笔记本电脑
        Computer c = new Pad(new Hasee());
        System.out.println(c.getName());
    }
}
