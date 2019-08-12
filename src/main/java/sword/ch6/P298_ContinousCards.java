package sword.ch6;

import java.util.Arrays;

public class P298_ContinousCards {
    public boolean isContinuous(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        //注意先排序，将大小王(0)排到前面方便计数，且方便统计后面两张牌间隔数
        Arrays.sort(arr);
        int n = arr.length, n0 = 0, ngap = 0;
        for (int i = 0; i < n && arr[i] == 0; i++) {
            n0++;
        }
        //从 n0 开始到 n-1
        for (int i = n0; i < n - 1; i++) {
            //连着两张牌相同直接为false
            if (arr[i] == arr[i + 1]) {
                return false;
            }
            //注意减 1
            ngap += arr[i + 1] - arr[i] - 1;
        }
        //注意是>=  如[3,0,0,0,0]
        return n0 >= ngap;
    }
}
