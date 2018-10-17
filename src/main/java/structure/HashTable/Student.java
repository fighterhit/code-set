package structure.HashTable;

public class Student {

    int grade;
    int cls;
    String firstName;
    String lastName;

    public Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //虽然溢出变负数，但逻辑正确
    @Override
    public int hashCode() {
        int hash = 0, B = 31;
        hash = hash * B + ((Integer) grade).hashCode();
        hash = hash * B + ((Integer) cls).hashCode();
        hash = hash * B + firstName.toLowerCase().hashCode();
        hash = hash * B + lastName.toLowerCase().hashCode();
        return hash;
    }

    //重写 equals，在 hashCode 冲突情况下判断是否相同
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Student another = (Student) obj;
        return another.grade == grade &&
                another.cls == cls &&
                another.firstName.toLowerCase().equals(firstName.toLowerCase()) &&
                another.lastName.toLowerCase().equals(lastName.toLowerCase());
    }
}
