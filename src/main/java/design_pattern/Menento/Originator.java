package design_pattern.Menento;

/**
 * @author Fighter.
 */
public class Originator {
    private String name;
    private int age;
    private double salary;

    /**
     * 保存
     *
     * @return 返回备忘录对象
     */
    public Memento save() {
        return new Memento(this);
    }

    public void recover(Memento m) {
        this.age = m.getAge();
        this.name = m.getName();
        this.salary = m.getSalary();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return  getName() +" "
                + getAge() + " "
                + getSalary();
    }
}
