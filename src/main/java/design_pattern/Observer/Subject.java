package design_pattern.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fighter.
 */
public class Subject {
    private List<Observer> list = new ArrayList<>();

    public void registerObserver(Observer obs) {
        list.add(obs);
    }

    public void removeObserver(Observer obs) {
        list.remove(obs);
    }

    public void notifyAllObserver() {
        for (Observer observer : list) {
            observer.update(this);
        }
    }
}

class ConcreteSubject extends Subject {
    private int subjectState;

    public int getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(int subjectState) {
        this.subjectState = subjectState;
        notifyAllObserver();
    }
}