package NewCoderProblemSet.Other.XIAOMI;

import java.util.*;

/**
 * 给一个长度为n的数组，有q组询问，q远大于n，每组询问是一个区间，现允许重排这n个数字，要求q组询问的区间和的总和最大，求这个最大值
 * 举例：比如数组 {2,0,1,0,3,4,5,6}，两组询问 [2,4] 和 [4,6]，最优的重排方式为 {0,0,2,3,6,4,5,1}，这样重排后 2~4 区间的和就是 a[2]+a[3]+a[4]=11，4~6区间的和就是a[4]+a[5]+a[6]=15，总和为11+15=26，由于没有别的排列方式可以得到比26更大的数字，所以答案是26
 */
public class Yimian {
    double getMaxSumInRequest(int[] array, List<int[]> queryList) {
        int n = array.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, 0);
        }
        for (int[] query : queryList) {
            for (int i = query[0]; i <= query[1]; i++) {
                map.put(i, map.get(i) + 1);

            }
        }
        List<Map.Entry<Integer, Integer>> ls = new ArrayList<>(map.entrySet());
        ls.sort((o1, o2) -> o2.getValue() - o1.getValue());
        Arrays.sort(array);
        int i = n - 1;
        int[] res = new int[n];
        for (Map.Entry<Integer, Integer> m : ls) {
            int index = m.getKey();
            res[index] = array[i--];
        }
        double sum = 0;
        for (int[] ints : queryList) {
            for (int j = ints[0]; j <= ints[1]; j++) {
                sum += res[j];
            }
        }
        System.out.println(sum);
        return sum;
    }

    public static void main(String[] args) {
        List<int[]> queryList = new ArrayList<>();
        queryList.add(new int[]{2, 4});
        queryList.add(new int[]{4, 6});
        new Yimian().getMaxSumInRequest(new int[]{2, 0, 1, 0, 3, 4, 5, 6}, queryList);
    }
}
