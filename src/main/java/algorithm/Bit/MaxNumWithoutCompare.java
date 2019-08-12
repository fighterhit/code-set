package algorithm.Bit;

public class MaxNumWithoutCompare {

    //0变1，1变0
    static int flip(int a) {
        return a ^ 1;
    }

    //大于等于 0 返回 1，小于 0 返回 0
    static int sign(int a) {
        return flip((a >> 31) & 1);
    }

    private static int getMax(int a, int b) {
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        //A、B 异号，即 diffAB = 1, sameAB = 0：
        //a >= 0 且 b < 0，sa = 1，sb = 0，返回 a
        //a < 0 且 b >= 0，sa = 0, sb = 1，返回 b
        int diffAB = sa ^ sb;
        //A、B 同号，即 diffAB = 0, sameAB = 1，此时 a - b 绝对不会溢出，可根据差值 c 的符号 sc 来判断 a 和 b 大小
        //c >= 0，sc = 1，返回 a
        //c < 0，sc = 0，返回 b
        int sameAB = flip(diffAB);
        return a * (diffAB * sa + sameAB * sc) + b * flip((diffAB * sa + sameAB * sc));
    }

    private static int getMax2(int a, int b) {
        int c = a - b;
        int sa = sign(a), sb = sign(b), sc = sign(c);
        if (sa == 1 && sb == 0) {
            return a;
        } else if (sa == 0 && sb == 1) {
            return b;
        } else if (sc == 1) {
            return a;
        } else {
            return b;
        }
    }

    public static void main(String[] args) {
        int a = 2, b = -21;
        System.out.println(getMax(a, b));
    }
}
