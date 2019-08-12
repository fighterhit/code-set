package leetcode.middle;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 * <p>
 * Example:
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * Note:
 * <p>
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 * <p>
 * 字符集、大小写敏感
 */
public class M49_GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null) {
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
//            if (map.containsKey(key)) {
//                map.get(key).add(str);
//            } else {
//                List<String> list = new ArrayList<>();
//                list.add(str);
//                map.put(key, list);
//            }
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    //慢
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null) {
            return res;
        }
        Map<Integer, List<String>> map = new HashMap<>();
        int[] arr;
        for (String str : strs) {
            arr = new int[26];
            for (int i = 0; i < str.length(); i++) {
                arr[str.charAt(i) - 'a']++;
            }
            int key = Arrays.hashCode(arr);
            List<String> ls = map.getOrDefault(key, new ArrayList<>());
            ls.add(str);
            map.put(key, ls);
        }
        return new ArrayList<>(map.values());
    }
}
