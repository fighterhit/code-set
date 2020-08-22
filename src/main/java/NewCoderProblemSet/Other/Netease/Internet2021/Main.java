package NewCoderProblemSet.Other.Netease.Internet2021;

import java.util.*;

/**
 * 整型数组中取出一部分数字，将剩余数组分成两部分且使其和相等，求取出的数组的和最小
 * 组合+分割数组 未验证对错
 * 参考：
 * Freewheel：ClosestTwoSum、DivideArrayTest
 * M416_PartitionEqualSubsetSum
 * <p>
 * 其余题：
 * 将一个数分解成素数最多的和，求此时分解后素数的个数
 * （参考：将一个数分解成多个素数和的方法数 https://blog.csdn.net/h6363817/article/details/12308385
 */
public class Main {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        //注意：求子集循环从 start 开始，每次递归 i + 1，排列每次从 0 开始
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue; // skip duplicates
            }
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int total = 0;
            int min = Integer.MAX_VALUE;
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = sc.nextInt();
                total += arr[j];
            }
            List<List<Integer>> ls = subsetsWithDup(arr);
            for (List<Integer> list : ls) {
                if (list.size() >= 2) {
                    int diff = getMaxDiff(list.toArray(new Integer[list.size()]));
                    if (diff == 0) {
                        min = Math.min(min, total - list.stream().reduce(Integer::sum).orElse(0));
                    }
                }
            }
            System.out.println(min);
        }
    }

    public static int getMaxDiff(Integer[] array) {
        int sum = getSum(array);
        int length = array.length;
        int[][] f = new int[length + 1][sum / 2 + 1];
        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                f[i + 1][j] = f[i][j];
                if (array[i] <= j && f[i][j - array[i]] + array[i] > f[i][j]) {
                    f[i + 1][j] = f[i][j - array[i]] + array[i];
                }
            }
        }
        return sum - 2 * f[length][sum / 2];
    }

    public static int getSum(Integer[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }
}