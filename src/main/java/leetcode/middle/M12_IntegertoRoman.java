package leetcode.middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Example 1:
 * Input: 3
 * Output: "III"
 * <p>
 * Example 2:
 * Input: 4
 * Output: "IV"
 * <p>
 * Example 3:
 * Input: 9
 * Output: "IX"
 * <p>
 * Example 4:
 * Input: 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * <p>
 * Example 5:
 * Input: 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * <p>
 * 1、相同的数字连写，所表示的数等于这些数字相加得到的数，如：Ⅲ = 3；
 * 2、小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数， 如：Ⅷ = 8；Ⅻ = 12；
 * 3、小的数字，（限于Ⅰ、X 和C）在大的数字的左边，所表示的数等于大数减小数得到的数，如：Ⅳ= 4；Ⅸ= 9；
 * 4、正常使用时，连写的数字重复不得超过三次。（表盘上的四点钟“IIII”例外）
 * 5、在一个数的上面画一条横线，表示这个数扩大1000倍。
 * <p>
 * 有几条须注意掌握：
 * 1、基本数字Ⅰ、X 、C 中的任何一个，自身连用构成数目，或者放在大数的右边连用构成数目，都不能超过三个；放在大数的左边只能用一个。
 * 2、不能把基本数字V 、L 、D 中的任何一个作为小数放在大数的左边采用相减的方法构成数目；放在大数的右边采用相加的方式构成数目，只能使用一个。
 * 3、V 和X 左边的小数字只能用Ⅰ。
 * 4、L 和C 左边的小数字只能用X。
 * 5、D 和M 左边的小数字只能用C。
 */
public class M12_IntegertoRoman {
    Map<Character, Integer> map = new HashMap<Character, Integer>() {
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

    //"" 1000 2000 3000
    String[] qian = {"", "M", "MM", "MMM"};
    //"" 100 200 300 400 500 600 700 800 900
    String[] bai = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    //"" 10 20 30 40 50 60 70 80 90
    String[] shi = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    //"" 1 2 3 4 5 6 7 8 9
    String[] ge = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public String intToRoman(int num) {
        return qian[num / 1000] + bai[(num / 100) % 10] + shi[(num / 10) % 10] + ge[num % 10];
    }

    public String intToRoman2(int num) {
        int[] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] str = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < val.length; i++) {
            while (num >= val[i]) {
                num -= val[i];
                sb.append(str[i]);
            }
        }
        return sb.toString();
    }
}
