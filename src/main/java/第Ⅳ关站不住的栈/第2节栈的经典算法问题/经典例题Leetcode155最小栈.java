package 第Ⅳ关站不住的栈.第2节栈的经典算法问题;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/min-stack/">最小栈</a>
 *
 * @author AntonyCheng
 * @since 2023/9/11 18:10:24
 */

public class 经典例题Leetcode155最小栈 {
    /**
     * 想要在常数时间内随时获取栈中的最小值，那么计算最小值的过程就一定不是在getMin方法中，
     * 而是在push或者pop的同时进行最小值的计算，但是这两个方法会导致最小值的不断变化，
     * 这种变化是随着栈的变化而变化的，所以不能单单仅用一个变量来保存最小值，
     * 而应该用一个容器随时记录这个最小值的变化，所以这里选择用一个辅助栈对最小值进行保存，
     * 栈的每一步push或者pop操作不仅会导致栈的内容发生变化，同时也会导致辅助栈的内容发生变化，
     * 这样当我们要取出栈内最小值的时候，我们只需要取出辅助战栈顶值即可；
     */
    class MinStack {
        private int[] stack;

        private int top;

        private int[] minStack;

        private int minTop;

        public MinStack() {
            stack = new int[10];
            minStack = new int[10];
        }

        private void expendStack(int size) {
            int length = stack.length;
            if (size > length) {
                size = size + size / 2;
                stack = Arrays.copyOf(stack, size);
                minStack = Arrays.copyOf(minStack, size);
            }
        }

        public void push(int val) {
            expendStack(top + 1);
            if (top == 0) {
                minStack[minTop] = val;
            } else {
                minStack[minTop] = Math.min(val, minStack[minTop - 1]);
            }
            minTop++;
            stack[top] = val;
            top++;
        }

        public void pop() {
            if (top > 0) {
                top--;
                minTop--;
            }
        }

        public int top() {
            return stack[top - 1];
        }

        public int getMin() {
            return minStack[minTop - 1];
        }
    }
}
