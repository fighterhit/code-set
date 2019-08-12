package sword.ch6;

public class P312_ExchangeTwoNumbers {
    public static void main(String[] args) {
        //基于加减法
        int a = 2, b = 5;
        a = a + b; // 7
        b = a - b; // 2
        a = a - b; // 5;
        System.out.println("a = " + a + " b = " + b);

        //基于异或法 a ^ b ^ a = b;
        a = 7;
        b = 8;
        a = a ^ b; // 此时 a = a ^ b
        b = a ^ b; // 相当于 b = a ^ b ^ b
        a = a ^ b; // 相当于 a = a ^ b ^ a ^ b ^ b
        System.out.println("a = " + a + " b = " + b);
    }
}
