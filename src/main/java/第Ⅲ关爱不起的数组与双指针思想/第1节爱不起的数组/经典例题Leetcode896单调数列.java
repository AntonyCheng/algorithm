package 第Ⅲ关爱不起的数组与双指针思想.第1节爱不起的数组;

/**
 * <a href="https://leetcode.cn/problems/monotonic-array/">单调数列</a>
 *
 * @author AntonyCheng
 * @since 2023/9/5 21:01:15
 */

public class 经典例题Leetcode896单调数列 {
    /**
     * 分别判断是否是单调数列
     *
     * @param nums
     * @return
     */
    public boolean isMonotonicMethod1(int[] nums) {
        boolean ascending = true;
        boolean descending = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                ascending = false;
                break;
            }
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                descending = false;
                break;
            }
        }
        return ascending || descending;
    }

    /**
     * 同时判断是否是单调数列（代码逻辑更简单，空间复杂下降但是耗时更多，因为没有break进行优化）
     * 分别判断会触发两次循环，其实在这里我们只需要一次循环即可，
     * 只要如果是单调函数，前一个会一直大于（小于）后一个，
     * 那么结果肯定是升序标识和降序标识其中一个是true，另一个是false
     * 如果两个都为false，那就不是单调函数
     *
     * @param nums
     * @return
     */
    public boolean isMonotonicMethod2(int[] nums) {
        boolean ascending = true;
        boolean descending = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                ascending = false;
            }
            if (nums[i] < nums[i + 1]) {
                descending = false;
            }
        }
        return ascending || descending;
    }
}
