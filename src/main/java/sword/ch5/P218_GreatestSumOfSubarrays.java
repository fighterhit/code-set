package sword.ch5;

public class P218_GreatestSumOfSubarrays {
    public int FindGreatestSumOfSubArray(int[] array) {
        int curSum = 0, res = Integer.MIN_VALUE, len = array.length;
        //sum 存每位的和
        for (int i = 0; i < len; i++) {
            if (curSum < 0) {
                curSum = array[i];
            } else {
                curSum = curSum + array[i];
            }
            res = Math.max(curSum, res);
        }
        return res;
    }
}
