package sword;


import java.util.HashMap;
import java.util.Map;

public class A2i {
    char flag = '+';

    public int a2i(String s, int radix) {
        if (s == null || s.length() < 1) {
            return 0;
        }

        //去除空格
        s = s.trim();
        int i = 0;

        //判断符号
        if (s.charAt(0) == '-') {
            flag = '-';
            i++;
        } else if (s.charAt(0) == '+') {
            i++;
        }

        //结果
        int res = 0;
        if (radix == 10) {
            while (s.length() > i && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                res = res * radix + (s.charAt(i) - '0');
                i++;
            }
        } else if (radix == 8) {
            while (s.length() > i && s.charAt(i) >= '0' && s.charAt(i) <= '7') {
                res = res * radix + (s.charAt(i) - '0');
                i++;
            }

        } else {
            Map<Character, Integer> map = new HashMap<>();
            map.put('a', 10);
            map.put('b', 11);
            map.put('c', 12);
            map.put('d', 13);
            map.put('e', 14);
            map.put('f', 15);
            s = s.toLowerCase();

            int tmp = 0;
            while (i < s.length() && ((s.charAt(i) >= '0' && s.charAt(i) <= '9') || (s.charAt(i) >= 'a' && s.charAt(i) <= 'f'))) {
                char c = s.charAt(i);
                if (c >= '0' && c <= '9') {
                    res = res * radix + (c - '0');

                } else {
                    tmp = map.get(c);
                    res = res * radix + tmp;
                }
                i++;
            }

        }
        if (flag == '-') {
            res = -res;
        }
        System.out.println(res);
        return (int) res;
    }

    public static void main(String[] args) {
        new A2i().a2i("16", 8);
    }
}
