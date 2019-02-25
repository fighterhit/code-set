package sword.ch3;

/**
 * 打印从 1 到最大的 n 位数
 *
 * @author Fighter Created on 2018/5/8.
 */
public class P114_Print1ToMaxOfNDigits {

    //字符串上模拟加法
    public static void print1ToMaxOfNDigits(int n) {
        if (n <= 0) {
            return;
        }
        char[] number = new char[n];
        print1ToMaxOfNDigits(number, 0);
    }

    private static void print1ToMaxOfNDigits(char[] number, int index) {
        if (index == number.length) {
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            //注意：因为 上面判断是 digit == number.length
            //所以初始传入索引是 0，并且这里是 digit，从第0为开始打印
            number[index] = (char) (i + '0');
            print1ToMaxOfNDigits(number, index + 1);
        }
    }


    private static void printNumber(char[] number) {
        int index = 0;
        while (index < number.length && number[index] == '0') {
            index++;
        }
        while (index < number.length) {
            System.out.print(number[index++]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        print1ToMaxOfNDigits(3);
    }
}

class P114_Print1ToMaxOfNDigits2 {

    //字符串上模拟加法
    void print1ToMaxOfNDigits(int n) {
        if (n <= 0) {
            return;
        }
        char[] number = new char[n];
        while (!increment(number)) {
            printNumber(number);
        }
    }

    boolean increment(char[] number) {
        boolean isOverFlow = false;
        int takeOver = 0;
        int sum;
        for (int i = number.length - 1; i >= 0; i--) {
            //每位的计算逻辑，本位数加上进位
            sum = number[i] - '0' + takeOver;
            if (i == number.length - 1) {
                sum++;
            }
            if (sum > 10) {
                if (i == 0) {
                    isOverFlow = true;
                } else {
                    takeOver = 1;
                    number[i] = (char) ('0' + (sum - 10));
                }
            } else {
                number[i] = (char) ('0' + sum);
                //没有进位，高位保持原数不需要再往下算可以返回
                break;
            }
        }
        return isOverFlow;
    }


    private static void printNumber(char[] number) {
        int index = 0;
        while (index < number.length && number[index] == '0') {
            index++;
        }
        while (index < number.length) {
            System.out.print(number[index++]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new P114_Print1ToMaxOfNDigits2().print1ToMaxOfNDigits(4);
    }
}
