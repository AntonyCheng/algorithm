package 第Ⅲ关爱不起的数组与双指针思想.第2节双指针思想以及应用;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/">删除有序数组中的重复项</a>
 *
 * @author AntonyCheng
 * @since 2023/9/10 23:03:07
 */

public class 快慢指针Leetcode26删除有序数组中的重复项 {
    /**
     * Hash辅助法
     * 将所有数据存入Hash链表中，去重后再转为数组
     *
     * @param nums
     * @return
     */
    public int removeDuplicatesMethod1(int[] nums) {
        HashSet<Integer> set = new LinkedHashSet<Integer>() {
            {
                for (int num : nums) {
                    add(num);
                }
            }
        };
        Iterator<Integer> iterator = set.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            nums[index] = iterator.next();
            index++;
        }
        return index;
    }

    /**
     * 快慢指针法
     * 使用快慢指针的前提就是题目已经给出了该数组是升序排列，那就说明相同的数位置是相邻的，
     * 所以用B指针去识别A指针之后的相邻值，如果相等就跳过，
     * 如果不等，就让B指针的值赋值给A+1指针，原来的A指针指向A+1指针所指的内容；
     *
     * @param nums
     * @return
     */
    public int removeDuplicatesMethod2(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                nums[slow + 1] = nums[fast];
                slow = slow + 1;
            }
            fast++;
        }
        return slow + 1;
    }
}
