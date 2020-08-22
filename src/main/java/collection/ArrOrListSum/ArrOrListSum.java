package collection.ArrOrListSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrOrListSum {

    //整形数组求和
    public static int sumOfArr(int[] arr) {
        return Arrays.stream(arr).sum();
    }

    //整形列表求和，http://www.mamicode.com/info-detail-2174683.html
    public static int sumOfList(List<Integer> ls) {
        return ls.stream().mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        int sum;
        int[] intArr = new int[]{1, 2, 3, 4, 5};
        sum = sumOfArr(intArr);
        System.out.println(sum);

//        List<Integer> integerList = Arrays.stream(intArr).boxed().collect(Collectors.toList());
        List<Integer> integerList = new ArrayList<>();
        integerList.add(100);
        integerList.add(200);
        integerList.add(300);
        integerList.add(400);
        integerList.add(500);
        sum = sumOfList(integerList);
        System.out.println(sum);
    }
}
