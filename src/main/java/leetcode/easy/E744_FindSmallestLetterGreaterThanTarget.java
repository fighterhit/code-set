package leetcode.easy;

/**
 * 给定一个只包含小写字母的有序数组letters 和一个目标字母 target，数组里字母的顺序是循环的，寻找有序数组里面比目标字母大的最小字母。
 * 举个例子，如果目标字母target = 'z' 并且有序数组为 letters = ['a', 'b']，则答案返回 'a'。
 * 示例:
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "a"
 * 输出: "c"
 * <p>
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "c"
 * 输出: "f"
 * <p>
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "d"
 * 输出: "f"
 * <p>
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "g"
 * 输出: "j"
 * <p>
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "j"
 * 输出: "c"
 * <p>
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "k"
 * 输出: "c"
 * 注:
 * <p>
 * letters长度范围在[2, 10000]区间内。
 * letters 仅由小写字母组成，最少包含两个不同的字母。
 * 目标字母target 是一个小写字母。
 */
public class E744_FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, h = letters.length - 1;
        //注意这里边界条件 <=
        //在循环条件为 l <= h 并且循环退出时，h 总是比 l 小 1，也就是说 h = 2，l = 3，由于这里要找比 target 大的字符，因此最后的返回值应该为 l 而不是 h
        while (l <= h) {
            int m = l + (h - l >> 1);
            if (letters[m] <= target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return l < letters.length ? letters[l] : letters[0];
    }

    //这里通过二分搜索 target 的下一个字符 (char) (target + 1)，直接得到比目标字母大的最小字母数组下标，二分搜索代码与普通情况统一
    //参考 M34_FindFirstandLastPositionofElementinSortedArray
    public char nextGreatestLetter2(char[] letters, char target) {
        int pos = search(letters, (char) (target + 1));
        if (pos == letters.length) {
            return letters[0];
        }
        return letters[pos];
    }

    public int search(char[] letters, char target) {
        int n = letters.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = l + (r - l >> 1);
            if (letters[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        new E744_FindSmallestLetterGreaterThanTarget().nextGreatestLetter(new char[]{'a', 'b'}, 'z');
    }
}
