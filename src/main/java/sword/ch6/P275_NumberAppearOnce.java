package sword.ch6;

/**
 * 整形数组里除了 2 个数字外，其它数字都出现了2次
 * E136_SingleNumber
 * P278_NumberAppearOnce
 */
public class P275_NumberAppearOnce {
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int res = 0;
        for (int i = 0; i < array.length; i++) {
            res ^= array[i];
        }
        int r1Index = 0;
        for (int i = 0; i < 32; i++) {
            if ((res >> i & 1) == 1) {
                r1Index = i;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if ((array[i] >> r1Index & 1) == 1) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }

    public void FindNumsAppearOnce2(int[] array, int num1[], int num2[]) {
        int res = 0;
        for (int i = 0; i < array.length; i++) {
            res ^= array[i];
        }
        //res &= -res 得到出 res 最右侧不为 0 的位，也就是不存在重复的两个元素在位级表示上最右侧不同的那一位
        res &= -res;
        for (int i = 0; i < array.length; i++) {
            //array[i] & res 不一定为1，因为 res 最右侧的 1 不一定在个位
            if ((array[i] & res) == 0) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(10));
        System.out.println(Integer.toBinaryString(-10));
        System.out.println(10 & -10);
        System.out.println(Integer.toBinaryString(10 & -10));
    }
}
