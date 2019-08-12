package leetcode.hard;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 * <p>
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 * <p>
 * https://blog.csdn.net/chen_xinjia/article/details/69258706
 * https://www.youtube.com/watch?v=do7ibYtv5nk
 * <p>
 * O(log(min(m,n)))
 * O(1)
 */
public class H4_MedianofTwoSortedArrays {

    /**
     * 假设两个有序数组的长度分别为m和n，由于两个数组长度之和 m+n 的奇偶不确定，因此需要分情况来讨论。
     * 对于奇数的情况，直接找到最中间的数即可，偶数的话需要求最中间两个数的平均值。
     * 为了简化代码，不分情况讨论，我们使用一个小 trick，我们分别找第 (m+n+1) / 2 个，和 (m+n+2) / 2 个，然后求其平均值即可，这对奇偶数均适用。
     * 假如 m+n 为奇数的话，那么其实 (m+n+1) / 2 和 (m+n+2) / 2 的值相等，相当于两个相同的数字相加再除以2，还是其本身。
     * <p>
     * https://www.cnblogs.com/grandyang/p/4465932.html
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, l = (m + n + 1) / 2, r = (m + n + 2) / 2;
        return (findMedianSortedArrays(nums1, 0, nums2, 0, l) + findMedianSortedArrays(nums1, 0, nums2, 0, r)) / 2.0;

    }

    //在两个有序数组中找到第K个元素
    //为了避免拷贝产生新的数组从而增加时间复杂度，我们使用两个变量i和j分别来标记数组 nums1 和 nums2 的起始位置。
    //然后来处理一些 corner cases，比如当某一个数组的起始位置大于等于其数组长度时，说明其所有数字均已经被淘汰了，相当于一个空数组了，那么实际上就变成了在另一个数组中找数字，直接就可以找出来了。还有就是如果 K=1 的话，那么我们只要比较 nums1 和 nums2 的起始位置i和j上的数字就可以了。
    private double findMedianSortedArrays(int[] nums1, int i, int[] nums2, int j, int k) {
        //当某一个数组的起始位置大于等于其数组长度时，说明其所有数字均已经被淘汰了，相当于一个空数组了，那么实际上就变成了在另一个数组中找数字
        if (i >= nums1.length) {
            return nums2[j + k - 1];
        }
        if (j >= nums2.length) {
            return nums1[i + k - 1];
        }
        //如果 K=1 的话，那么我们只要比较 nums1 和 nums2 的起始位置i和j上的数字即可
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        //需要在两个有序数组中找到第K个元素，为了加快搜索的速度，我们要使用二分法，那么对谁二分呢，数组么？其实要对K二分，意思是我们需要分别在 nums1 和 nums2 中查找第 K/2 个元素，注意这里由于两个数组的长度不定，所以有可能某个数组没有第 K/2 个数字，所以我们需要先 check 一下，数组中到底存不存在第 K/2 个数字，如果存在就取出来，否则就赋值上一个整型最大值。如果某个数组没有第 K/2 个数字，那么我们就淘汰另一个数组的前 K/2 个数字即可。
        int mid1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int mid2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        //比较这两个数组的第 K/2 小的数字 midVal1 和 midVal2 的大小，如果第一个数组的第 K/2 个数字小的话，那么说明我们要找的数字肯定不在 nums1 中的前 K/2 个数字，所以我们可以将其淘汰，将 nums1 的起始位置向后移动 K/2 个，并且此时的K也自减去 K/2，调用递归
        if (mid1 < mid2) {
            return findMedianSortedArrays(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findMedianSortedArrays(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }


    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            //使得 nums1 保证是小数组
            return findMedianSortedArrays2(nums2, nums1);
        }

        int n1 = nums1.length, n2 = nums2.length, totalLen = n1 + n2;
        //cut1、cut2 分别表示 nums1 和 nums2 左边元素个数
        int cut1 = 0, cut2 = 0, cutL = 0, cutR = nums1.length;
        while (cut1 <= nums1.length) {
            cut1 = cutL + (cutR - cutL) / 2;
            cut2 = totalLen / 2 - cut1;

            double L1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            double L2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            double R1 = (cut1 == n1) ? Integer.MAX_VALUE : nums1[cut1];
            double R2 = (cut2 == n2) ? Integer.MAX_VALUE : nums2[cut2];

            if (L1 > R2) {
                //cut1的范围就变成了[cutL,cut1-1]
                cutR = cut1 - 1;
            } else if (L2 > R1) {
                //cut1的范围就变成了[cut1+1,cutR]
                cutL = cut1 + 1;
            } else {
                if (totalLen % 2 == 0) {
                    double a = (L1 > L2 ? L1 : L2);
                    double b = (R1 < R2 ? R1 : R2);
                    return (a + b) / 2;
                } else {
                    return R1 < R2 ? R1 : R2;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new H4_MedianofTwoSortedArrays().findMedianSortedArrays2(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{-2, -1}));
        ;
    }
}
