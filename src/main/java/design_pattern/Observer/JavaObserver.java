package design_pattern.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Fighter.
 */
public class JavaObserver {
    static class MySubject extends Observable {
        private int MyState;

        public int getMyState() {
            return MyState;
        }

        public void setMyState(int myState) {
            MyState = myState;
            setChanged();
            notifyObservers();
        }
    }

    static class MyObserver implements Observer {
        private int myState;

        public int getMyState() {
            return myState;
        }

        @Override
        public void update(Observable observable, Object o) {
            myState = ((MySubject) observable).getMyState();
        }

    }

    public static void main(String[] args) {
        MyObserver observer1 = new MyObserver();
        MyObserver observer2 = new MyObserver();
        MyObserver observer3 = new MyObserver();

        MySubject subject = new MySubject();
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.addObserver(observer3);

        subject.setMyState(233);
        System.out.println(observer1.getMyState());
        System.out.println(observer2.getMyState());
        System.out.println(observer3.getMyState());

        subject.setMyState(666);
        System.out.println(observer1.getMyState());
        System.out.println(observer2.getMyState());
        System.out.println(observer3.getMyState());

    }
}


