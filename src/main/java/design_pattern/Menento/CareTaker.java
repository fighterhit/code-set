package design_pattern.Menento;

/**
 * 负责管理备忘录
 * @author Fighter.
 */
public class CareTaker {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
