package design_pattern.FlyWeight;

public class Client {
    public static void main(String[] args) {
        ConcreteFlyweight concreteFlyweight1 = FlyWeightFactory.getConcreteFlyweight("黑色");
        ConcreteFlyweight concreteFlyweight2 = FlyWeightFactory.getConcreteFlyweight("黑色");
        System.out.println(concreteFlyweight1);
        System.out.println(concreteFlyweight2);

        System.out.println("=====增加外部状态处理=====");
        concreteFlyweight1.display(new Point(10, 10));
        concreteFlyweight2.display(new Point(20, 20));
        System.out.println(concreteFlyweight1 == concreteFlyweight2);
    }
}
