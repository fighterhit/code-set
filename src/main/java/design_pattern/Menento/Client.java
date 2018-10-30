package design_pattern.Menento;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        //原始状态
        Originator originator = new Originator();
        originator.setName("name");
        originator.setAge(18);
        originator.setSalary(1000);
        System.out.println(originator);

        //保存状态
        Memento memento = originator.save();
        CareTaker careTaker = new CareTaker();
        careTaker.setMemento(memento);

        //修改状态
        originator.setName("name2");
        originator.setAge(19);
        originator.setSalary(2000);
        System.out.println(originator);

        //恢复状态
        originator.recover(careTaker.getMemento());
        System.out.println(originator);
    }
}
