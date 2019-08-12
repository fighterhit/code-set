package leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M119_PascalsTriangleII {
    //用 old 保存上次的旧值
    public List<Integer> getRow(int rowIndex) {
        Integer[] dp = new Integer[rowIndex + 1];
        int tmp = 0, old = 0;
        for (int i = 0; i < rowIndex + 1; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    dp[j] = 1;
                    old = dp[j];
                } else {
                    tmp = dp[j];
                    dp[j] = dp[j] + old;
                    old = tmp;
                }
            }
        }
        return Arrays.asList(dp);
    }

    public List<Integer> getRow2(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; i++) {
            //每次先在末尾添加一个 1
            res.add(1);
            //倒序计算，防止覆盖上次 j - 1 位置的旧值
            //for (int j = res.size() - 2; j > 0; j--) {
            for (int j = i - 1; j > 0; j--) {
                res.set(j, res.get(j) + res.get(j - 1));
            }
        }
        return res;
    }
}
