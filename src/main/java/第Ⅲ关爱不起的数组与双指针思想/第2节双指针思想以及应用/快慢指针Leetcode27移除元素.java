package 第Ⅲ关爱不起的数组与双指针思想.第2节双指针思想以及应用;

import java.util.ArrayList;

/**
 * <a href="https://leetcode.cn/problems/remove-element/">移除元素</a>
 *
 * @author AntonyCheng
 * @since 2023/9/10 15:57:32
 */

public class 快慢指针Leetcode27移除元素 {
    /**
     * 集合辅助法
     * 如果不考虑题目要求，我们可以使用列表辅助移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElementMethod1(int[] nums, int val) {
        ArrayList<Integer> list = new ArrayList<Integer>() {
            {
                for (int num : nums) {
                    add(num);
                }
            }
        };
        list.removeIf(o -> o == val);
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        return list.size();
    }

    /**
     * 快慢指针法
     * 按照题目要求，使用双指针法进行原地删除的操作，和“去重”操作一样，原理就是用地址元素的覆盖；
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElementMethod2(int[] nums, int val) {
        int slow = -1;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    /**
     * 碰撞指针法
     * 这个也叫做交换转移，就是从右侧找到不是val的值如顶替左侧是val的值；
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElementMethod3(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if ((nums[left]) == val && (nums[right] != val)) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
            if (nums[left]!=val){
                left++;
            }
            if (nums[right]==val){
                right--;
            }
        }
        return left;
    }
}
