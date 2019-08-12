package leetcode.middle;


import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * Example:
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 * <p>
 * 一是只要遇到字符串的 子序列或匹配问题 首先考虑 动态规划DP，二是只要遇到需要求出 所有可能情况 首先考虑用递归
 * https://www.cnblogs.com/grandyang/p/4305572.html
 */
public class M93_RestoreIPAddresses {
    List<String> res = new ArrayList<>();

    //暴力枚举IP每一部分结束位置
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4) {
            return res;
        }
        //i,j,k 是前三部分每部分的结束位置
        int len = s.length();
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < i + 4; j++) {
                for (int k = j + 1; k < j + 4; k++) {
                    if (j < len && k < len) {
                        String s1 = s.substring(0, i + 1);
                        String s2 = s.substring(i + 1, j + 1);
                        String s3 = s.substring(j + 1, k + 1);
                        String s4 = s.substring(k + 1);
                        if (check(s1) && check(s2) && check(s3) && check(s4)) {
                            res.add(s1 + '.' + s2 + '.' + s3 + '.' + s4);
                        }
                    }
                }
            }
        }
        return res;
    }

    private boolean check(String s) {
        //s.length == 0: 输入 1.1.11.""  s4 为空
        if (s == null || s.length() == 0 || s.length() > 3 || (s.charAt(0) == '0' && s.length() > 1) || Integer.parseInt(s) > 255) {
            return false;
        }
        return true;
    }

    //暴力枚举IP每一部分长度
    public List<String> restoreIpAddresses2(String s) {
        if (s == null || s.length() < 4) {
            return res;
        }
        //i,j,k 是前三部分每部分的长度
        int len = s.length();
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                for (int k = 1; k < 4; k++) {
                    for (int l = 1; l < 4; l++) {
                        //每部分长度合法
                        if (i + j + k + l == len) {
                            //另一种检测每部分数字是否合法的方法：除去带前导 0 的不合法 IP
                            //先转数字，这样会去掉每部分的前导0，然后每部分再转成字符串跟源字符串 s 比较长度，相等则说明合法
                            int v1 = Integer.valueOf(s.substring(0, i));
                            int v2 = Integer.valueOf(s.substring(i, i + j));
                            int v3 = Integer.valueOf(s.substring(i + j, i + j + k));
                            int v4 = Integer.valueOf(s.substring(i + j + k));
                            if (v1 <= 255 && v2 <= 255 && v3 <= 255 && v4 <= 255) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(v1).append('.').append(v2).append('.').append(v3).append('.').append(v4);
                                if (sb.length() == len + 3) {
                                    res.add(sb.toString());
                                }
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    public List<String> restoreIpAddresses3(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) {
            return res;
        }
        helper(s, 0, 0, "");
        return res;
    }

    private void helper(String s, int index, int cnt, String tmp) {
        if (cnt == 4 && index == s.length()) {
            res.add(tmp);
            return;
        }
        //注意 index + i 可以和 s 长度相等，因为 index + i 作为 substring() 结尾，左闭右开不包括有边界[)
        for (int i = 1; i <= 3 && index + i <= s.length(); i++) {
            String t = s.substring(index, index + i);
            if (t.length() > 1 && t.charAt(0) == '0' || Integer.valueOf(t) > 255) {
                break;
            }
            helper(s, index + i, cnt + 1, cnt == 0 ? t : tmp + '.' + t);
        }
    }

    StringBuilder sb = new StringBuilder();

    //思路同 restoreIpAddresses5，只是循环条件改为 1 ~ 3
    public List<String> restoreIpAddresses4(String s) {
        if (s.length() < 4 || s.length() > 12) {
            return res;
        }
        helper(s, 0);
        return res;
    }

    private void helper(String s, int cnt) {
        if (cnt == 4 || s.length() == 0) {
            if (cnt == 4 && s.length() == 0) {
                res.add(sb.toString());
            }
            return;
        }
        //循环条件改为 1 ~ 3
        for (int i = 1; i < s.length() && i <= 3; i++) {
            if (i > 1 && s.charAt(0) == '0') {
                break;
            }
            String part = s.substring(0, i);
            if (Integer.valueOf(part) <= 255) {
                if (cnt != 0) {
                    part = "." + part;
                }
                sb.append(part);
                helper(s.substring(i), cnt + 1);
                sb.delete(sb.length() - part.length(), sb.length());
            }
        }
    }


    //回溯
    public List<String> restoreIpAddresses5(String s) {
        if (s == null || s.length() == 0) {
            return res;
        }
        helper2(new StringBuilder(), s, 0);
        return res;
    }

    private void helper2(StringBuilder sb, String s, int groupCnt) {
        //判断字符串是否已经划完 且 分组已经到达4个
        if (groupCnt == 4 || s.length() == 0) {
            if (groupCnt == 4 && s.length() == 0) {
                res.add(sb.toString());
            }
            return;
        }
        for (int i = 0; i < s.length() && i <= 2; i++) {
            //该字符串以0开始，只有当 0 为 1 位时 ip 有效
            if (i > 0 && s.charAt(i) == '0') {
                break;
            }
            String part = s.substring(0, i + 1);
            if (Integer.valueOf(part) <= 255) {
                //注意这里要判断是不是 ip 开头
                if (sb.length() != 0) {
                    part = "." + part;
                }
                sb.append(part);
                helper2(sb, s.substring(i + 1), groupCnt + 1);
                sb.delete(sb.length() - part.length(), sb.length());
            }
        }
    }
}
