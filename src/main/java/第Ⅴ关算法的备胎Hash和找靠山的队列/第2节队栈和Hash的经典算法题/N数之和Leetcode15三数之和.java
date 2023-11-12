package 第Ⅴ关算法的备胎Hash和找靠山的队列.第2节队栈和Hash的经典算法题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/3sum/">三数之和</a>
 *
 * @author AntonyCheng
 * @since 2023/9/12 16:12:36
 */

public class N数之和Leetcode15三数之和 {
    /**
     * 循环扫描法（非常非常非常慢，Leetcode直接超时）
     * 思路：使用三重循环进行扫描判断，难点在于不可以包含重复的三元组，
     * 我们可以在每次遍历加入前都进行排序，并且判断结果集中是否包含排序之后的子集合；
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumMethod1(int[] nums) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        int finalI = i;
                        int finalJ = j;
                        int finalK = k;
                        ArrayList<Integer> addList = new ArrayList<Integer>() {
                            {
                                add(nums[finalI]);
                                add(nums[finalJ]);
                                add(nums[finalK]);
                            }
                        };
                        addList.sort((o1, o2) -> o1 - o2);
                        if (!list.contains(addList)) {
                            list.add(addList);
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * 排序+碰撞指针法
     * 首先对数组进行排序，保留双重for循环结构，第一层循环作用是固定第一个元素即target以及确定右端指针third，
     * 第二层循环作用是遍历左端指针second以及限定second和third的关系；
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumMethod2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }
                // 如果指针重合，随着 b 后续的增加就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}
