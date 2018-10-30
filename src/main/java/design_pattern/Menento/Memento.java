package design_pattern.Menento;

/**
 * @author Fighter.
 */
public class Memento {
    private String name;
    private int age;
    private double salary;

    public Memento(Originator o) {
        this.name = o.getName();
        this.age = o.getAge();
        this.salary = o.getSalary();
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
}
