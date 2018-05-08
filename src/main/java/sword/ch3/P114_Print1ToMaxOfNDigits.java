package sword.ch3;

/**
 * 打印从 1 到最大的 n 位数
 *
 * @author Fighter Created on 2018/5/8.
 */
public class P114_Print1ToMaxOfNDigits {
    static int cnt = 0;

    //字符串上模拟加法
    public static void print1ToMaxOfNDigits(int n) {
        if (n <= 0) {
            return;
        }
        char[] number = new char[n];
//        print1ToMaxOfNDigits(number, -1);
        print1ToMaxOfNDigits(number, 0);
        System.out.println("cnt" + cnt);

    }

    private static void print1ToMaxOfNDigits(char[] number, int digit) {
        if (digit == number.length) {
            printNumber(number);
            cnt++;
            return;
        }
        for (int i = 0; i < 10; i++) {
            //注意：因为 上面判断是 digit == number.length
            //所以初始传入索引是 0，并且这里是 digit，从第0为开始打印
            number[digit] = (char) (i + '0');
            //注意：因为 上面判断是 digit == number.length - 1
            //所以初始传入-1 ，并且这里是 digit + 1，否则打印少一位
            number[digit + 1] = (char) (i + '0');
            print1ToMaxOfNDigits(number, digit + 1);
        }
    }


    private static void printNumber(char[] number) {
        int index = 0;
        while (index < number.length && number[index] == '0') {
            index++;
        }
        //打印0
        /*if (index == number.length){
            System.out.println(0);
            return;
        }*/
        while (index < number.length) {
            System.out.print(number[index++]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        print1ToMaxOfNDigits(3);
    }

}
