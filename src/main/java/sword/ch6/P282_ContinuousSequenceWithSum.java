package sword.ch6;

import java.util.ArrayList;
import java.util.List;


public class P282_ContinuousSequenceWithSum {
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        int start = 1, end = 2;
        int curSum = 3;
        while (end < sum) {
            //当前和小于 sum，增大 end，求新 curSum，注意顺序
            if (curSum < sum) {
                end++;
                curSum += end;
            }
            //当前和大于 sum，删除 start，求新 curSum，注意顺序
            else if (curSum > sum) {
                curSum -= start;
                start++;
            }
            //当前和等于 sum
            else {
                ArrayList<Integer> ls = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    ls.add(i);
                }
                res.add(ls);
                //注意：更新start,end,curSum
                curSum -= start;
                start++;
                //最后这两行不必要，可删去
                end++;
                curSum += end;
            }
        }
        return res;
    }

    /**
     * 参考 H829_ConsecutiveNumbersSum，有问题
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence2(int N) {
        if (N <= 1) {
            return res;
        }
        int sum = 0;
        for (int i = 1; sum < N; i++) {
            sum += i;
            if ((N - sum) % i == 0) {
                List<Integer> ls = new ArrayList<>();
                int k = (N - sum) / i;
                for (int j = k; j <= k + i - 1; j++) {
                    ls.add(j);
                }
                res.add(new ArrayList<>(ls));
            }
        }
        return res;
    }

    /**
     * https://zhanghuimeng.github.io/post/leetcode-829-consecutive-numbers-sum/
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence3(int N) {
        if (N <= 1) {
            return res;
        }
        for (int m = 1; ; m++) {
            int mx = N - m * (m - 1) / 2;
            if (mx <= 0) {
                break;
            }
            if (mx % m == 0) {
                int x = mx / m;
                ArrayList<Integer> ls = new ArrayList<>();
                for (int i = x; i <= x + (m - 1); i++) {
                    ls.add(i);
                }
                //和 H829_ConsecutiveNumbersSum 区别，这里需要除去自身
                if (ls.size() > 1) {
                    res.add(ls);
                }
            }
        }
        //根据题意：序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序。因此这里需要排序
        res.sort((o1, o2) -> (o1.get(0) - o2.get(0)));
        return res;
    }

    public static void main(String[] args) {
        new P282_ContinuousSequenceWithSum().FindContinuousSequence2(5);
    }
}
