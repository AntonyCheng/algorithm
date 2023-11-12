package 第Ⅲ关爱不起的数组与双指针思想.第1节爱不起的数组;

/**
 * <a href="https://leetcode.cn/problems/search-insert-position/">搜索插入位置</a>
 *
 * @author AntonyCheng
 * @since 2023/9/10 11:59:40
 */

public class 经典例题Leetcode35搜索插入位置 {
    /**
     * 数组遍历法
     * 如果不根据题目要求（时间复杂度为logN），可以使用这种方法
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsertMethod1(int[] nums, int target) {
        if (target <= nums[0]) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (target > nums[i] && target <= nums[i + 1]) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找法
     * 如果根据题目要求，可以使用这种方法
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsertMethod2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
