package collection.ListAndArrayConversion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListAndArrayConversion {

    //基本类型数组转列表，只支持int, long, double三种类型
    public static List<Integer> basicArr2List(int[] basicArr) {
        return Arrays.stream(basicArr).boxed().collect(Collectors.toList());
    }

    //列表转基本类型数组
    public static int[] list2BasicArr(List<Integer> intList) {
        return intList.stream().mapToInt(Integer::intValue).toArray();
    }

    //===================================================

    //包装类型数组转列表
    public static List<Integer> boxedArr2List(Integer[] boxedArr) {
        //Arrays.asList只是返回列表视图，不能增删
        //return Arrays.asList(boxedArr);
        return Arrays.stream(boxedArr).collect(Collectors.toList());
    }

    //列表转包装类型数组
    public static Integer[] list2BoxedArr(List<Integer> integerList) {
        return integerList.toArray(new Integer[0]);
    }

    public static void main(String[] args) {
        int[] intArr = new int[]{1, 2, 3, 4, 5};
        List<Integer> intList = basicArr2List(intArr);
        for (int i = 0; i < intList.size(); i++) {
            int val = intList.get(i);
            System.out.println(val);
            intList.set(i, val + 100);
        }

        System.out.println("===========================");

        intArr = list2BasicArr(intList);
        for (int n : intArr) {
            System.out.println(n);
        }

        System.out.println("===========================");

        Integer[] integerArr = new Integer[]{20, 21, 22, 23, 24, 25, 233};
        List<Integer> integerList = boxedArr2List(integerArr);
        System.out.println(integerList);

        System.out.println("===========================");
        integerArr = list2BoxedArr(integerList);
        for (Integer integer : integerArr) {
            System.out.println(integer);
        }

        /*
        //注意：https://www.cnblogs.com/DreamDrive/p/5641065.html
        int[] data = {1, 2, 3, 4, 5};
        List list = Arrays.asList(data);
        //返回 1
        System.out.println(list.size());
        */
    }
}
