package 第Ⅲ关爱不起的数组与双指针思想.第2节双指针思想以及应用;

/**
 * <a href="https://leetcode.cn/problems/rotate-array/">轮转数组</a>
 *
 * @author AntonyCheng
 * @since 2023/9/11 00:00:04
 */

public class 反转指针Leetcode189轮转数组 {
    /**
     * 数组平移法（非常非常非常非常非常慢，过不了Leetcode）
     * 平移k次数组，每次都将最后一个值平移到最开始；
     *
     * @param nums
     * @param k
     */
    public void rotateMethod1(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int num = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = num;
        }
    }

    /**
     * 反转指针法
     * 重点在于反转数组这个可以被单独抽取出来的方法，本题的解题思路和反转链表类似，
     * 但是没有反转链表那么抽象，因为数组在底层是一段连续的内存，并不需要单独的引用去指向内容的地址，
     * 下面会对反转数组这个方法进行单独的抽取，该题的图解如下：
     * 初始态  1 2 3 4|5 6 7
     * 第一步  7 6 5|4 3 2 1
     * 第二步  5 6 7|4 3 2 1
     * 第三步  5 6 7|1 2 3 4
     * 由此可见，三步反转即可完成该题
     *
     * @param nums
     * @param k
     */
    public void rotateMethod2(int[] nums, int k) {
        k = k % nums.length;
        reverseArray(nums, 0, nums.length - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, nums.length - 1);
    }

    /**
     * 反转指针对数组进行反转
     *
     * @param nums
     * @param start
     * @param end
     */
    public void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
