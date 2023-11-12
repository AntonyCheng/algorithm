package 第Ⅲ关爱不起的数组与双指针思想.第2节双指针思想以及应用;

import java.util.Arrays;

/**
 * 双指针思想
 * 双指针思想的核心就是两个“指针”变量对一个线性集合进行遍历，
 * 这里的线性集合通常是数组、链表、栈、队列、字符串等，
 * 根据双指针的移动方向，我们可以大致分为三种：
 * 1、端到端（最常用）
 * （1）快慢指针（去重）
 * （2）前后等距指针（链表寻址）
 * （3）循环扫描指针（遍历链表，效率极低，但是是一个方法）
 * （4）反转指针（这是一个特殊的端到端的指针方法，这个“端”不一定是最边缘，一般用来反转数组）
 * 2、端到中点
 * （1）碰撞指针
 * 3、中点到端
 * 在下面的例子会使用双指针解决一个经典题目，即“去重”；
 *
 * @author AntonyCheng
 * @since 2023/9/10 14:55:02
 */

public class DoublePointer {
    /**
     * 删除升序数组中的重复数据
     * 该题解题核心就是快慢指针（slow和fast），首先fast要在slow后一个位置，
     * 然后fast和slow做对比，如果不相等，slow位置不变，fast位置后移一个，
     * 如果相等，slow+1的值替换为fast的值，slow位置也后移一个；
     *
     * @param arr
     * @return
     */
    public static int[] removeDuplicateDataByDoublePointer(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int slow = 0;
        int fast = 1;
        while (fast < arr.length) {
            if (arr[slow] != arr[fast]) {
                slow++;
                arr[slow] = arr[fast];
            }
            fast++;
        }
        int[] res = new int[slow + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {};
        int[] ints = removeDuplicateDataByDoublePointer(arr);
        System.out.println(Arrays.toString(ints));
    }
}
