package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 查看自己石头里的宝石数，J代表宝石，大小写敏感，利用哈希表， O(s+j)
 *
 * @author Fighter Created on 2018/5/7.
 */
public class E771_JewelsandStones {
    public int numJewelsInStones(String J, String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < J.length(); i++) {
            map.put(J.charAt(i), 1);
        }
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if (map.containsKey(S.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}
