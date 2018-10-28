package design_pattern.Prototype;

import java.util.Date;

/**
 * 浅复制
 */
public class Prototype {
    public static void main(String[] args) throws CloneNotSupportedException {
        Date date = new Date(1);
        Sheep s1 = new Sheep("s1", date);
        System.out.println(s1.name);
        System.out.println(s1.birthday);
        Sheep s2 = (Sheep) s1.clone();
        s1.setBirthday(new Date(2));
        System.out.println(s1.name);
        System.out.println(s1.birthday);
        System.out.println(s2.name);
        System.out.println(s2.birthday);
    }

    static class Sheep implements Cloneable {
        private String name;
        private Date birthday;

        public Sheep(String name, Date date) {
            this.name = name;
            this.birthday = date;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            //直接调用object对象的clone()方法实现浅复制
            return super.clone();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
    }
}