package 第Ⅲ关爱不起的数组与双指针思想.第2节双指针思想以及应用;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <a href="https://leetcode.cn/problems/summary-ranges/description/">汇总区间</a>
 *
 * @author AntonyCheng
 * @since 2023/9/11 00:48:57
 */

public class 快慢指针Leetcode228汇总区间 {
    /**
     * 隔离标记法
     * 根据题意将连续的区间分界索引值标记出来
     *
     * @param nums
     * @return
     */
    public List<String> summaryRangesMethod1(int[] nums) {
        ArrayList<Integer> resList = new ArrayList<>();
        if (nums.length == 0) {
            return new ArrayList<String>();
        }
        resList.add(nums[0]);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 != nums[i + 1]) {
                resList.add(nums[i]);
                resList.add(nums[i + 1]);
            }
        }
        resList.add(nums[nums.length - 1]);
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < resList.size() - 1; i = i + 2) {
            if (!Objects.equals(resList.get(i), resList.get(i + 1))) {
                res.add(resList.get(i) + "->" + resList.get(i + 1));
            } else {
                res.add(String.valueOf(resList.get(i)));
            }
        }
        return res;
    }

    /**
     * 快慢指针法
     * 用快慢指针分别标记分区的首位值，同时使用结果集合进行收集；
     *
     * @param nums
     * @return
     */
    public List<String> summaryRangesMethod2(int[] nums) {
        ArrayList<String> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (fast + 1 == nums.length) {
                if (slow == fast) {
                    res.add(String.valueOf(nums[slow]));
                } else {
                    res.add(nums[slow] + "->" + nums[fast]);
                }
                fast++;
                slow = fast;
            } else {
                if (nums[fast] + 1 == nums[fast + 1]) {
                    fast++;
                } else {
                    if (slow == fast) {
                        res.add(String.valueOf(nums[slow]));
                    } else {
                        res.add(nums[slow] + "->" + nums[fast]);
                    }
                    fast++;
                    slow = fast;
                }
            }
        }
        return res;
    }

    /**
     * 快慢指针法（代码优化版）
     * 将原方法的代码结果做进一步优化
     *
     * @param nums
     * @return
     */
    public List<String> summaryRangesMethod3(int[] nums) {
        ArrayList<String> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (fast + 1 == nums.length || nums[fast] + 1 != nums[fast + 1]) {
                StringBuilder sb = new StringBuilder();
                sb.append(nums[slow]);
                if (slow != fast) {
                    sb.append("->").append(nums[fast]);
                }
                res.add(sb.toString());
                slow = fast + 1;
            }
            fast++;
        }
        return res;
    }
}
