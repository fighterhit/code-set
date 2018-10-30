package design_pattern.Observer;

/**
 * @author Fighter.
 */
public interface Observer {
    void update(Subject subject);
}

class ConcreteObserver implements Observer {

    private int observerState;

    public int getObserverState() {
        return observerState;
    }

    //更新方法 使observer的state与subject state保持一致
    @Override
    public void update(Subject subject) {
        observerState = ((ConcreteSubject) subject).getSubjectState();
    }
}
