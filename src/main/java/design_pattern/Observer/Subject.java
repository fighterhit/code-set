package design_pattern.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fighter.
 */

public interface Subject {
    List<Observer> list = new ArrayList<>();

    void registerObserver(Observer obs);

    void removeObserver(Observer obs);

    void notifyAllObserver();
}

class ConcreteSubject implements Subject {
    private int subjectState;

    public int getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(int subjectState) {
        this.subjectState = subjectState;
        notifyAllObserver();
    }

    @Override
    public void registerObserver(Observer obs) {
        list.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        list.remove(obs);
    }

    @Override
    public void notifyAllObserver() {
        for (Observer o : list) {
            o.update(this);
        }
    }
}