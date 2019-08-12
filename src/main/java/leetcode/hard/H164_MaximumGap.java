package leetcode.hard;

/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Return 0 if the array contains less than 2 elements.
 * Example 1:
 * Input: [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either
 * (3,6) or (6,9) has the maximum difference 3.
 * <p>
 * Example 2:
 * Input: [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 * Note:
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * Try to solve it in linear time/space.
 * 要求线性复杂度：桶排序
 */
public class H164_MaximumGap {

    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    int len = -1;

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        len = nums.length;
        //每个桶是否有值
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        //bucketNum 保存桶号，res 保存返回结果
        int bucketNum = -1, res = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        if (min == max) {
            return 0;
        }
        //遍历数组元素
        for (int i = 0; i < len; i++) {
            bucketNum = getBucketNum(nums[i]);
            //遍历数组，更新每个桶最大、最小值
            mins[bucketNum] = hasNum[bucketNum] ? Math.min(mins[bucketNum], nums[i]) : nums[i];
            maxs[bucketNum] = hasNum[bucketNum] ? Math.max(maxs[bucketNum], nums[i]) : nums[i];
            hasNum[bucketNum] = true;
        }
        //遍历每个桶，求后面桶最小值和前面桶最大值之差
        for (int i = 1, lastMax = maxs[0]; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    //注意用 long 类型防止相乘溢出
    int getBucketNum(long num) {
        return (int) ((num - min) * len / (max - min));
        //注意下面写法会产生数组越界错误，因为乘积可能会超过 2147483647 从而产生负值！
        //Java 不具有‘目标确定类型’的特性（指存储结果的变量的类型会影响到计算所使用的变量类型），就是说java不会主动把你要计算的24*60*60*1000*1000变成你要存入的x变量的long类型，而是先以int类型进行计算，得到的应该是86400000000L，但是java把它当做int，存不下，溢出了，截取得到500654080再保存到int中， 最后int转成long赋值给x，最后的结果就不准确了；
        //避免这种情况的常用方法是给 第一个因子添加类型L；
        //建议看《java解惑》之迷题3：长整除部分有讲解
        //怎么截取的，我举个简单例子，java short类型的取值范围-32768~32767，当你是32768，那么超过，给你存入-32768，是32769，存入-32767.....以此类推。
        // https://zhidao.baidu.com/question/1958523459848530180.html
        //return (int) (num - min) * len / (max - min);
    }
}
