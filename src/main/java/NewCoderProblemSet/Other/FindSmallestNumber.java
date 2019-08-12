package NewCoderProblemSet.Other;

import java.util.*;

/**
 * 在一个无序数组中找到3个数字组成数最小，要求这三个数字的索引是按序排列，例如23156这个数组，得到的结果是156，(面试时我提出用最小堆配合索引解决)，面试官说用python的一种基本数据结构就可以解决，基本数据结构无非就是map，dict，set这些，我这两天想了好久没想到，求解。。
 * https://www.nowcoder.com/discuss/203658?type=0&order=0&pos=14&page=1
 */
public class FindSmallestNumber {
    //双端队列
    static void findSmallestNumber(int[] arr) {
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                if (q.size() >= 3 && q.peekLast() > arr[i]) {
                    q.pollLast();
                }
            } else {
                //队列内保持元素值升序，且由于数组是按从左往右遍历，故索引自然是按升序排列
                while (!q.isEmpty() && q.peekLast() > arr[i]) {
                    q.pollLast();
                }
            }
            if (q.size() < 3) {
                q.add(arr[i]);
            }
        }
        for (int i = 0, len = q.size(); i < len; i++) {
            System.out.println(q.poll());
        }
    }

    //双端队列
    static void findSmallestNumber2(int[] arr) {
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            //队列内保持元素值升序，且由于数组是按从左往右遍历，故索引自然是按升序排列
            while (!q.isEmpty() && q.peekLast() > arr[i]) {
                q.pollLast();
            }
            if (q.size() < 3) {
                q.add(arr[i]);
            }
        }
        for (int i = 0, len = q.size(); i < len; i++) {
            System.out.println(q.poll());
        }
    }

    static void findSmallestNumber3(int[] arr) {
        int index = -1, bound = 0;
        int min, j;
        int[] res = new int[3];
        for (int i = 0; i < 3; i++) {
            min = Integer.MAX_VALUE;
            for (j = arr.length - 3 + i; j >= bound; j--) {
                if (i == 0) {
                    if (arr[j] != 0 && min >= arr[j]) {
                        min = arr[j];
                        index = j;
                    }
                } else if (min >= arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            res[i] = min;
            bound = index + 1;
        }
        System.out.println(Arrays.toString(res));
    }

    public static void main(String[] args) {
//        int[] arr = {2, 3, 1, 5, 6, 0,8};
        int[] arr = {1, 0, 0, 0, 0, 0, 0, 2};
        findSmallestNumber3(arr);
        arr = new int[]{2, 3, 1, 5, 6, 0};
        findSmallestNumber(arr);
    }
}
