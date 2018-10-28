package design_pattern.Prototype;

import java.io.*;
import java.util.Date;

/**
 * 浅复制
 */
public class Prototype {
    public static void main(String[] args) throws CloneNotSupportedException {
        Date date = new Date(111111);
        Sheep s1 = new Sheep("s1", date);
        System.out.println(s1.name);
        System.out.println(s1.birthday);
        Sheep s2 = (Sheep) s1.clone();
        date.setTime(22222);
        System.out.println(s1.name);
        System.out.println(s1.birthday);
        System.out.println(s2.name);
        System.out.println(s2.birthday);
    }

    static class Sheep implements Cloneable {
        private String name;
        private Date birthday;

        public Sheep(String name, Date date) {
            super();
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

/**
 * 深复制
 */
class Prototype2 {

    public static void main(String[] args) throws CloneNotSupportedException {
        Date date = new Date(111111);
        Sheep s1 = new Sheep("s1", date);
        System.out.println(s1.name);
        System.out.println(s1.birthday);
        Sheep s2 = (Sheep) s1.clone();
        date.setTime(22222);
        System.out.println(s1.name);
        System.out.println(s1.birthday);
        System.out.println(s2.name);
        System.out.println(s2.birthday);

    }

    static class Sheep implements Cloneable {
        private String name;
        private Date birthday;

        public Sheep(String name, Date date) {
            super();
            this.name = name;
            this.birthday = date;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            //直接调用object对象的clone()方法实现浅复制
            Sheep sheep = (Sheep) super.clone();
            //调用对象clone()实现深复制
            sheep.birthday = (Date) this.birthday.clone();
            return sheep;
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


/**
 * 用序列化、反序列化实现深复制
 */
class Prototype3 {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Date date = new Date(111111);
        Sheep s1 = new Sheep("s1", date);
        System.out.println(s1.name);
        System.out.println(s1.birthday);

//        Sheep s2 = (Sheep) s1.clone();
        //用序列化、反序列化实现深复制
        //序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(s1);
        //反序列化
        byte[] objBytes = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(objBytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Sheep s2 = (Sheep) objectInputStream.readObject();

        date.setTime(22222);
        System.out.println(s1.name);
        System.out.println(s1.birthday);
        System.out.println(s2.name);
        System.out.println(s2.birthday);

    }

    static class Sheep implements Cloneable, Serializable {
        private String name;
        private Date birthday;

        public Sheep(String name, Date date) {
            super();
            this.name = name;
            this.birthday = date;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            //直接调用object对象的clone()方法实现浅复制
            Sheep sheep = (Sheep) super.clone();
            //调用对象clone()实现深复制
            sheep.birthday = (Date) this.birthday.clone();
            return sheep;
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

