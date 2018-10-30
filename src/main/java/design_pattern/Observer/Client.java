package design_pattern.Observer;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        Observer observer1 = new ConcreteObserver();
        Observer observer2 = new ConcreteObserver();
        Observer observer3 = new ConcreteObserver();

        //state 改变前
        System.out.println(((ConcreteObserver) observer1).getObserverState());
        System.out.println(((ConcreteObserver) observer2).getObserverState());
        System.out.println(((ConcreteObserver) observer3).getObserverState());

        Subject subject = new ConcreteSubject();
        subject.registerObserver(observer1);
        subject.registerObserver(observer2);
        subject.registerObserver(observer3);

        ((ConcreteSubject) subject).setSubjectState(233);

        //state 改变后
        System.out.println(((ConcreteObserver) observer1).getObserverState());
        System.out.println(((ConcreteObserver) observer2).getObserverState());
        System.out.println(((ConcreteObserver) observer3).getObserverState());
    }
}
