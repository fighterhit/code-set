package sword.ch6;

/**
 * 整形数组里除了 1 个数字外，其它数字都出现了 3 次
 * P275_NumberAppearOnce，M137_SingleNumberII
 */
public class P278_NumberAppearOnce {
    public static int findNumberAppearOnce(int[] data) {
        int index = 1;
        int[] sum = new int[32];
        for (int i = 0; i < data.length; i++) {
            //对每个数每个位判断是否为 1，为 1 求和
            for (int j = 31; j >= 0; j--) {
                //表明该位为 1
                if ((data[i] & index) != 0) {
                    sum[j] += 1;
                }
                index <<= 1;
            }
        }
        int res = 0;
        for (int i = 0; i < sum.length; i++) {
            res <<= 1;
            res += sum[i] % 3;
        }
        return res;
        /*StringBuilder sb = new StringBuilder();
        for (int i : sum) {
            sb.append(i);
        }
        //注意是 parseUnsignedInt，parseInteger 会超范围
        return Integer.parseUnsignedInt(sb.toString(), 2);*/
    }

    public static void main(String[] args) {
        int[] data1 = new int[]{10, 4, 5, 3, 5, 4, 3, 3, 4, 5};
        int[] data2 = new int[]{0, -4, 5, 3, 5, -4, 3, 3, -4, 5};
        int[] data3 = new int[]{-10, -4, 5, 3, 5, -4, 3, 3, -4, 5};
        System.out.println(findNumberAppearOnce(data1));
        System.out.println(findNumberAppearOnce(data2));
        System.out.println(findNumberAppearOnce(data3));
        System.out.println(1 << 32);
    }
}

class P278_NumberAppearOnce2 {
    public static int findNumberAppearOnce(int[] data) {
        if (data == null || data.length < 3)
            return 0; //为了方便返回0，但其实并不能区分异常
        // 存储一个int(长度32bit)的每个bit的状态
        int[] bitSum = new int[32];
        int k = 3;
        for (int i = 0; i < data.length; i++) {
            int indexOfBit1 = 1;
            for (int j = 31; j >= 0; j--) {
                if ((data[i] & indexOfBit1) != 0)
                    bitSum[j] += 1;
                indexOfBit1 <<= 1;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1; //先移位
            result += bitSum[i] % k;
        }
        return result;
    }

    // 一般化的问题描述：有一个数组，其中有一个元素出现了M次，其余所有元素都出现了K次，找出这个出现了M次的元素
    // 更一般化的问题描述：一个数组，其中有一个元素出现次数大于0小于K，其余所有元素都出现了K次，找出这个出现次数不是K的元素
    // 可使用电路设计相关知识解决
    // 上述的数组为int数组，但其实每一位只需要记录0,1,2这几个状态就可以了，因此可以改为bit[32][2],效果一样。
    // 此时我们令bit[i][0](-1<i<33)为int a,bit[i][1](-1<i<33)为int b,因此可以通过a，b两个整形数完成出现次数的记录。
    // 更进一步地，a,b数值的变化可以用电路中的卡诺图表示，化简。
    // 对于data数组中的每一个数c，都会更新a，b，而a，b的每一个bit位的值得变化都遵循如下规则：
    //  a   b       c   =>  a   b
    //  0   0       0       0   0
    //  0   1       0       0   1
    //  1   0       0       1   0
    //  0   0       1       0   1
    //  0   1       1       1   0
    //  1   0       1       0   0
    //  可以根据卡诺图化简，或者由如上表格直接得到：
    //  a = a=a&~b&~c | ~a&b&c, b = ~a&b&~c | ~a&~b&c
    //  最终要求出现一次的数，所以计算 ~a&b(或b)即可
    // http://m.blog.csdn.net/smile_watermelon/article/details/47748227
    // https://discuss.leetcode.com/topic/22821/an-general-way-to-handle-all-this-sort-of-questions
    public static int findNumberAppearOnce2(int[] data) {
        int a = 0, b = 0;
        int aTemp = 0;
        for (int c : data) {
            aTemp = a & ~b & ~c | ~a & b & c;
            b = ~a & b & ~c | ~a & ~b & c;
            a = aTemp;
        }
        return b;
    }

    public static void main(String[] args) {
        int[] data1 = new int[]{10, 4, 5, 3, 5, 4, 3, 3, 4, 5};
        int[] data2 = new int[]{0, -4, 5, 3, 5, -4, 3, 3, -4, 5};
        int[] data3 = new int[]{-10, -4, 5, 3, 5, -4, 3, 3, -4, 5};
        System.out.println(findNumberAppearOnce(data1));
        System.out.println(findNumberAppearOnce(data2));
        System.out.println(findNumberAppearOnce(data3));

        System.out.println(findNumberAppearOnce2(data1));
        System.out.println(findNumberAppearOnce2(data2));
        System.out.println(findNumberAppearOnce2(data3));
    }
}