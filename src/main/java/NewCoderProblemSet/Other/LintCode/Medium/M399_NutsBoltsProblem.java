package NewCoderProblemSet.Other.LintCode.Medium;

import java.util.Arrays;

/**
 * 给定一组 n 个不同大小的 nuts 和 n 个不同大小的 bolts. nuts 和 bolts 一一匹配.
 * 不允许将 nut 之间互相比较, 也不允许将 bolt 之间互相比较. 也就是说, 只许将 nut 与 bolt 进行比较, 或将 bolt 与 nut 进行比较. 我们会提供一个比较函数, 用于nut和bolt的比较.
 * 利用我们提供的函数, 你需要将 nuts 或者 bolts 重新排列, 使得它们按照顺序一一匹配.
 * <p>
 * Example
 * 给出 nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC']
 * 你的程序应该找出bolts和nuts的匹配.
 * 根据比较函数, 一组可能的返回结果是：
 * nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG']
 * 如果我们给你另外的比较函数，可能返回的结果是：
 * nuts = ['ab','bc','dd','gg'], bolts = ['BC','AB','DD','GG']
 * 因此的结果完全取决于比较函数，而不是字符串本身。因为你必须使用比较函数来进行排序。
 * 各自的排序当中nuts和bolts的顺序是无关紧要的，只要他们一一匹配就可以。
 */
public class M399_NutsBoltsProblem {
    /**
     * public class NBCompare {
     *     public int cmp(String a, String b);
     * }
     * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
     * if "a" is bigger than "b", it will return 1, else if they are equal,
     * it will return 0, else if "a" is smaller than "b", it will return -1.
     * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
     */
    /**
     * @param nuts:    an array of integers
     * @param bolts:   an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        if (nuts == null || bolts == null) return;
        if (nuts.length != bolts.length) return;
        quickSort(nuts, bolts, compare, 0, nuts.length - 1);
    }

    private void quickSort(String[] nuts, String[] bolts, NBComparator compare, int l, int r) {
        if (l >= r) {
            return;
        }
        //用 nuts[l] 对 bolts 排序
        int pivot = partition(bolts, nuts[l], compare, l, r);
        //用 bolt[pivot] 对 nuts 排序
        partition(nuts, bolts[pivot], compare, l, r);
        quickSort(nuts, bolts, compare, l, pivot - 1);
        quickSort(nuts, bolts, compare, pivot + 1, r);
    }

    private int partition(String[] strs, String pivot, NBComparator compare, int l, int r) {
        //先找到枢纽元素，将其交换到最左边 l 位置处
        for (int i = l; i <= r; i++) {
            if (compare.cmp(strs[i], pivot) == 0 || compare.cmp(pivot, strs[i]) == 0) {
                swap(strs, i, l);
                break;
            }
        }
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && (compare.cmp(strs[i], pivot) == -1 || compare.cmp(pivot, strs[i]) == 1)) {
                i++;
            }
            while (j >= l + 1 && (compare.cmp(strs[j], pivot) == 1 || compare.cmp(pivot, strs[j]) == -1)) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(strs, i, j);
            i++;
            j--;
        }
        swap(strs, l, j);
        return j;
    }


    private void swap(String[] strs, int left, int right) {
        String temp = strs[left];
        strs[left] = strs[right];
        strs[right] = temp;
    }

    public static class NBComparator {
        public static int cmp(String a, String b) {
            a = a.toLowerCase();
            b = b.toLowerCase();
            if (a.compareTo(b) > 0) {
                return 1;
            } else if (a.compareTo(b) < 0) {
                return -1;
            } else
                return 0;
        }
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"ab", "bc", "dd", "gg"};
        String[] arr2 = new String[]{"DD", "BC", "AB", "GG"};
//            Arrays.sort(arr,NBComparator::cmp);
//            Arrays.sort(arr2,NBComparator::cmp);
        new M399_NutsBoltsProblem().sortNutsAndBolts(arr, arr2, new NBComparator());
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));

    }
}
