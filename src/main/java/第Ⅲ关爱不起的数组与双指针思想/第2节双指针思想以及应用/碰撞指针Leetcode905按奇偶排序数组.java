package 第Ⅲ关爱不起的数组与双指针思想.第2节双指针思想以及应用;

import java.util.ArrayList;

/**
 * <a href="https://leetcode.cn/problems/sort-array-by-parity/">按奇偶排序数组</a>
 *
 * @author AntonyCheng
 * @since 2023/9/10 23:43:41
 */

public class 碰撞指针Leetcode905按奇偶排序数组 {
    /**
     * 集合辅助法
     * 将偶数放入一个集合，奇数放入一个集合，然后偶数集合后拼接奇数集合
     *
     * @param nums
     * @return
     */
    public int[] sortArrayByParityMethod1(int[] nums) {
        ArrayList<Integer> even = new ArrayList<>();
        ArrayList<Integer> odd = new ArrayList<>();
        for (int num : nums) {
            if (num % 2 == 0) {
                even.add(num);
            } else {
                odd.add(num);
            }
        }
        even.addAll(odd);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = even.get(i);
        }
        return nums;
    }

    /**
     * 碰撞指针法
     * 用数组后面的偶数去替换数组前面的奇数
     *
     * @param nums
     * @return
     */
    public int[] sortArrayByParityMethod2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 != 0 && nums[right] % 2 == 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
            if (nums[left] % 2 == 0) {
                left++;
            }
            if (nums[right] % 2 != 0) {
                right--;
            }
        }
        return nums;
    }
}
