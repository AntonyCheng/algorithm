package 第Ⅴ关算法的备胎Hash和找靠山的队列.第2节队栈和Hash的经典算法题;

import java.util.HashMap;

/**
 * <a href="https://leetcode.cn/problems/two-sum/">两数之和</a>
 *
 * @author AntonyCheng
 * @since 2023/9/12 15:42:10
 */

public class N数之和Leetcode1两数之和 {
    /**
     * 循环扫描法
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumMethod1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * HashMap辅助法
     * 建立HashMap<nums[i],i>并且遍历数组，首先判断HashMap中是否包含target-nums[i]的键（这里求得两数之和，利用减法将复杂度降至为O(1)），
     * 如果包含，那么就取出该键所对应的索引，和i一同组成一个数组进行返回，
     * 如果不包含，那么就将遍历得到的值和索引按照<nums[i],i>键值对的形式存入HashMap中；
     * 如果知道遍历完也没有，那么就返回空数组；
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumMethod2(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{hashMap.get(target - nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[0];
    }
}
