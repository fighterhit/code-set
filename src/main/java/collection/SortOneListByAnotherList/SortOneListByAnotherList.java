package collection.SortOneListByAnotherList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortOneListByAnotherList {
}

class Posts {
    private String id;
    private String name;
    private String age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

class MyTest {
    public static void main(String[] args) {

        List<String> orderRegulation = Arrays.asList("G102", "G103", "G108", "G101", "G104", "G107", "G109", "G110", "G105", "G106");
        List<Posts> targetList = new ArrayList<>();

        Posts post1 = new Posts();
        post1.setId("G101");
        post1.setName("xiaoli1");
        post1.setAge("29");

        Posts post2 = new Posts();
        post2.setId("G102");
        post2.setName("xiaoli2");
        post2.setAge("22");

        Posts post3 = new Posts();
        post3.setId("G103");
        post3.setName("xiaoli3");
        post3.setAge("23");

        Posts post4 = new Posts();
        post4.setId("G104");
        post4.setName("xiaoli4");
        post4.setAge("25");

        Posts post5 = new Posts();
        post5.setId("G105");
        post5.setName("xiaoli5");
        post5.setAge("27");

        Posts post6 = new Posts();
        post6.setId("G106");
        post6.setName("xiaoli6");
        post6.setAge("23");

        Posts post7 = new Posts();
        post7.setId("G107");
        post7.setName("xiaoli7");
        post7.setAge("21");

        Posts post8 = new Posts();
        post8.setId("G108");
        post8.setName("xiaoli8");
        post8.setAge("26");

        Posts post9 = new Posts();
        post9.setId("G109");
        post9.setName("xiaoli9");
        post9.setAge("25");

        Posts post10 = new Posts();
        post10.setId("G110");
        post10.setName("xiaoli10");
        post10.setAge("28");

        Posts post11 = new Posts();
        post11.setId("G111");
        post11.setName("xiaoli10");
        post11.setAge("28");

        Posts post12 = new Posts();
        post12.setId("G112");
        post12.setName("xiaoli10");
        post12.setAge("28");

        Posts post13 = new Posts();
        post13.setId("G113");
        post13.setName("xiaoli10");
        post13.setAge("28");

        targetList.add(post11);
        targetList.add(post2);
        targetList.add(post5);
        targetList.add(post4);
        targetList.add(post3);
        targetList.add(post13);
        targetList.add(post1);
        targetList.add(post6);
        targetList.add(post9);
        targetList.add(post12);
        targetList.add(post8);
        targetList.add(post10);
        targetList.add(post7);
        System.out.println("排列前的数据：");
        targetList.forEach(t -> System.out.print(t.getId() + t.getName() + "~" + t.getAge() + "  "));
        System.out.println();

        List<Posts> tmp = new ArrayList<>(targetList);
        setListOrder(orderRegulation, targetList);
        System.out.println("排序的规则：");
        orderRegulation.forEach(t -> System.out.print(t + " "));
        System.out.println();
        System.out.println("排列后的数据：");
        targetList.forEach(t -> System.out.print(t.getId() + t.getName() + "~" + t.getAge() + " "));

        tmp.sort(new Comparator<Posts>() {
            @Override
            public int compare(Posts o1, Posts o2) {
                return Integer.compare(orderRegulation.indexOf(o1.getId()), orderRegulation.indexOf(o2.getId()));
            }
        });

        System.out.println();
        System.out.println("排列后的数据：");
        tmp.forEach(t -> System.out.print(t.getId() + t.getName() + "~" + t.getAge() + " "));
    }

    //平时排序可使用其中一种，下面是综合两个条件排序
    public static void setListOrder(List<String> orderRegulation, List<Posts> targetList) {
        //按照Posts的Id来排序
/*        Collections.sort(targetList, (o1, o2) -> {
            int io1 = orderRegulation.indexOf(o1.getId());
            int io2 = orderRegulation.indexOf(o2.getId());
            if (io1 != -1) {
                io1 = targetList.size() - io1;
            }
            if (io2 != -1) {
                io2 = targetList.size() - io2;
            }
            return io2 - io1;
        });*/
        targetList.sort((o1, o2) -> {
            int io1 = orderRegulation.indexOf(o1.getId());
            int io2 = orderRegulation.indexOf(o2.getId());
            if (io1 != -1) {
                io1 = targetList.size() - io1;
            }
            if (io2 != -1) {
                io2 = targetList.size() - io2;
            }
            //注意是 io2-io1
            return io2 - io1;
        });

/*        //按照Posts的age来排序
        Collections.sort(targetList, ((o1, o2) -> {
            int io1 = orderRegulation.indexOf(o1.getAge());
            int io2 = orderRegulation.indexOf(o2.getAge());

            if (io1 != -1) {
                io1 = targetList.size() - io1;
            }
            if (io2 != -1) {
                io2 = targetList.size() - io2;
            }

            return io2 - io1;
        }));*/
    }
}