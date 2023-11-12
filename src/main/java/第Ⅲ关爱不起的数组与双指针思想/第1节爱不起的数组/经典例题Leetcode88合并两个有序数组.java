package 第Ⅲ关爱不起的数组与双指针思想.第1节爱不起的数组;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/merge-sorted-array/">合并两个有序数组</a>
 *
 * @author AntonyCheng
 * @since 2023/9/10 12:53:01
 */

public class 经典例题Leetcode88合并两个有序数组 {
    /**
     * 先追加，再排序
     * 由题目可知，num1既是参数，也是返回值，
     * 那么两个数组合并，nums.length肯定大于或者等于m+n（题目是等于），
     * 所以这里先把num2的元素追加到num1中，然后再对num1进行排序；
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void mergeMethod1(int[] nums1, int m, int[] nums2, int n) {
        int index = m;
        for (int i = 0; i < n; i++) {
            nums1[index] = nums2[i];
            index++;
        }
        Arrays.sort(nums1);
    }

    /**
     * 尾插法
     * 因为num1的长度恰好等于n+m，那么我们可以从num1的length尾部入手，
     * 依次比较num1的size尾部和num2的size尾部的大小，大的就插入length尾部；
     * 对比到最后可能会出现有一方没有对比完，需要单独考虑；
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void mergeMethod2(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int size1 = m - 1;
        int size2 = n - 1;
        while (size1 >= 0 && size2 >= 0) {
            if (nums1[size1] <= nums2[size2]) {
                nums1[index] = nums2[size2];
                index--;
                size2--;
            } else if (nums2[size2] <= nums1[size1]) {
                nums1[index] = nums1[size1];
                index--;
                size1--;
            }
        }
        while (size1 != -1) {
            nums1[index] = nums1[size1];
            index--;
            size1--;
        }
        while (size2 != -1) {
            nums1[index] = nums2[size2];
            index--;
            size2--;
        }
    }
}
