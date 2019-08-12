package sword.ch6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class P280_TwoNumbersWithSum {
    //类比 E1_TwoSum
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        int mulRes = Integer.MAX_VALUE;
        int one = Integer.MAX_VALUE, two = Integer.MAX_VALUE;
        ArrayList<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(sum - array[i])) {
                if ((sum - array[i]) * array[i] < mulRes) {
                    mulRes = (sum - array[i]) * array[i];
                    one = sum - array[i];
                    two = array[i];
                }
            }
            map.put(array[i], i);
        }
        //不存在的情况
        if (one == Integer.MAX_VALUE && two == Integer.MAX_VALUE) {
            return res;
        }
        res.add(one);
        res.add(two);
        return res;
    }

    public ArrayList<Integer> FindNumbersWithSum2(int[] array, int sum) {
        int mulRes = Integer.MAX_VALUE;
        int one = Integer.MAX_VALUE, two = Integer.MAX_VALUE;
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0, j = array.length - 1;
        while (i < j) {
            if (array[i] + array[j] > sum) {
                j--;
            } else if (array[i] + array[j] < sum) {
                i++;
            } else {
                if (array[i] * array[j] < mulRes) {
                    mulRes = array[i] * array[j];
                    one = array[i];
                    two = array[j];
                }
                i++;
                j--;
            }
        }
        //不存在的情况
        if (one == Integer.MAX_VALUE && two == Integer.MAX_VALUE) {
            return res;
        }
        res.add(one);
        res.add(two);
        return res;
    }

}
