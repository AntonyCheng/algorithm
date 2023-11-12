package 第Ⅳ关站不住的栈.第2节栈的经典算法问题;

import java.util.Arrays;
import java.util.Stack;

/**
 * 最大栈（Leetcode Plus题目，需要VIP，本地测试成功即可）
 *
 * @author AntonyCheng
 * @since 2023/9/11 19:43:42
 */

public class 经典例题Leetcode716最大栈 {
    /**
     * 该题和最小栈相似，最大的区别就在于popMax这个方法，最小栈题中没有弹出最小栈的方法，
     * 这个方法的思路：主栈和辅助栈同时出栈，当主栈第一次遇见最大值时将其弹出，随后重新入栈，同时更新辅助栈；
     */
    class MaxStack {

        private int[] stack;

        private int top;

        private int[] maxStack;

        private int maxTop;

        public MaxStack() {
            stack = new int[10];
            maxStack = new int[10];
        }

        private void expendStack(int size) {
            int length = stack.length;
            if (size > length) {
                size = size + size / 2;
                stack = Arrays.copyOf(stack, size);
                maxStack = Arrays.copyOf(maxStack, size);
            }
        }

        public void push(int val) {
            expendStack(top + 1);
            if (top == 0) {
                maxStack[maxTop] = val;
            } else {
                maxStack[maxTop] = Math.max(val, maxStack[maxTop - 1]);
            }
            maxTop++;
            stack[top] = val;
            top++;
        }

        public Integer pop() {
            Integer t = null;
            if (top > 0) {
                t = stack[top - 1];
                top--;
                maxTop--;
            }
            return t;
        }

        public Integer top() {
            return stack[top - 1];
        }

        public Integer peekMax() {
            return maxStack[maxTop - 1];
        }

        public Integer popMax() {
            int maxNum = maxStack[maxTop - 1];
            Stack<Integer> tempStack = new Stack<>();
            while (top() != maxNum) {
                tempStack.push(pop());
            }
            pop();
            while (!tempStack.isEmpty()) {
                push(tempStack.pop());
            }
            return maxNum;
        }
    }
}
