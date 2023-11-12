package 第Ⅴ关算法的备胎Hash和找靠山的队列.第2节队栈和Hash的经典算法题;

/**
 * <a href="https://leetcode.cn/problems/implement-queue-using-stacks/">用栈实现队列</a>
 *
 * @author AntonyCheng
 * @since 2023/9/12 13:47:28
 */

public class 队栈转换Leetcode232用栈实现队列 {
    /**
     * 用栈实现队列，栈是先进后出，题目要求使用两个栈完成先进后出的功能，我们可以画出如下简图：
     * 我要实现对{1,2,3}进行队列操作
     * 入队栈  出队栈    入队栈  出队栈    入队栈  出队栈     入队栈  出队栈    入队栈  出队栈
     * ?      ?        ?      ?        ?      ?        ?      ?        ?      ?
     * ?      ?   ->   3      ?   ->   ?      ?   ->   ?      ?   ->   ?      1   ->   {1,2,3}
     * ?      ?        2      ?        2      ?        ?      2        ?      2
     * ?      ?        1      ?        1      3        1      3        ?      3
     * 根据进阶题目的要求，为了是时间复杂度为O(1)，那么这里使用链表实现两个栈（一个叫入队栈，一个叫出队栈）
     */
   static class MyQueue {
        /**
         * 节点类
         */
        private class Node {
            public int val;
            public Node next;

            Node(int val) {
                this.val = val;
            }
        }

        /**
         * 入队栈
         */
        private Node pushStack;

        /**
         * 出队栈
         */
        private Node popStack;

        public MyQueue() {
            this.pushStack = null;
            this.popStack = null;
        }

        /**
         * 入队
         *
         * @param x
         */
        public void push(int x) {
            Node newNode = new Node(x);
            newNode.next = pushStack;
            pushStack = newNode;
        }

        /**
         * 出队
         *
         * @return
         */
        public int pop() {
            if (popStack == null) {
                while (pushStack != null) {
                    Node next = pushStack.next;
                    pushStack.next = popStack;
                    popStack = pushStack;
                    pushStack = next;
                }
            }
            int t = popStack.val;
            popStack = popStack.next;
            return t;
        }

        /**
         * 查看下一个出队值
         *
         * @return
         */
        public int peek() {
            if (popStack == null) {
                while (pushStack != null) {
                    Node next = pushStack.next;
                    pushStack.next = popStack;
                    popStack = pushStack;
                    pushStack = next;
                }
            }
            return popStack.val;
        }

        /**
         * 判空
         *
         * @return
         */
        public boolean empty() {
            return pushStack == null && popStack == null;
        }
    }
}
