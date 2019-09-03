package leetcode.middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
 * 示例 1:
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2:
 * <p>
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * <p>
 * input 是 "2-1-1" 时，就有两种情况了，(2-1)-1 和 2-(1-1)，由于括号的不同，得到的结果也不同，但如果把括号里的东西当作一个黑箱的话，那么其就变为 ()-1  和 2-()，其最终的结果跟括号内可能得到的值是息息相关的。
 * 那么再 general 一点，实际上就可以变成 () ? () 这种形式，两个括号内分别是各自的表达式，最终会分别计算得到两个整型数组，中间的问号表示运算符，可以是+，-，*。
 * 那么问题就变成了从两个数组中任意选两个数字进行运算，瞬间变成我们会做的题目了有木有？而这种左右两个括号代表的黑盒子就交给递归去计算，像这种分成左右两坨的 pattern 就是大名鼎鼎的分治法 Divide and Conquer 了，是必须要掌握的一个神器。
 * 类似的题目还有之前的那道 M95_UniqueBinarySearchTreesII 用的方法一样，用递归来解，划分左右子树，递归构造。
 * 这道题不用新建递归函数，就用其本身来递归就行，先建立一个结果 res 数组，然后遍历 input 中的字符，根据上面的分析：
 * 希望在每个运算符的地方，将 input 分成左右两部分，从而扔到递归中去计算，从而可以得到两个整型数组 left 和 right，分别表示作用两部分各自添加不同的括号所能得到的所有不同的值。
 * 此时我们只要分别从两个数组中取数字进行当前的运算符计算，然后把结果存到 res 中即可。当然，若最终结果 res 中还是空的，那么只有一种情况，input 本身就是一个数字，直接转为整型存入结果 res 中即可
 * https://www.cnblogs.com/grandyang/p/4682458.html
 */
public class M241_DifferentWaystoAddParentheses {

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                res.add(l + r);
                                break;
                            case '-':
                                res.add(l - r);
                                break;
                            case '*':
                                res.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        //当 input 为纯数字时返回！！！
        if (res.size() == 0) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }

    //添加 map 保存计算过的子串结果
    Map<String, List<Integer>> map = new HashMap<>();

    public List<Integer> diffWaysToCompute2(String input) {
        if (map.containsKey(input)) {
            return map.get(input);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                //注意 substring 参数
                List<Integer> left = diffWaysToCompute2(input.substring(0, i));
                List<Integer> right = diffWaysToCompute2(input.substring(i + 1));
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                res.add(l + r);
                                break;
                            case '-':
                                res.add(l - r);
                                break;
                            case '*':
                                res.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        //当 input 为纯数字时返回！！！
        if (res.size() == 0) {
            res.add(Integer.parseInt(input));
        }
        map.put(input, res);
        return res;
    }
}
