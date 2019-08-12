package leetcode.easy;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * <p>
 * Example:
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * Output: [1,2,2,3,5,6]
 *
 * @author Fighter.
 */
public class E88_MergeSortedArray {

    //从后往前合并，大的放后边
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int index = m + n - 1;
        while (i >= 0 || j >= 0) {
            if (i < 0) {
                nums1[index--] = nums2[j--];
            } else if (j < 0) {
                nums1[index--] = nums1[i--];
            }
            //从后往前合并，大的放后边
            else if (nums1[i] > nums2[j]) {
                nums1[index--] = nums1[i--];
            } else {
                nums1[index--] = nums2[j--];
            }
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, index = nums1.length;
        while (i >= -1 && j >= -1) {
            if (nums1[i] >= nums2[j]) {
                nums1[--index] = nums1[i--];
            } else {
                nums1[--index] = nums2[j--];
            }
        }
        if (i == -1) {
            for (; j >= 0; j--) {
                nums1[--index] = nums2[j];
            }
        } else {
            for (; i >= 0; i--) {
                nums1[--index] = nums1[i];
            }
        }
        int intern = nums1.length - (m + n);
        for (int l = 0; l < (m + n); l++) {
            nums1[l] = nums1[l + intern];
        }
    }
}
