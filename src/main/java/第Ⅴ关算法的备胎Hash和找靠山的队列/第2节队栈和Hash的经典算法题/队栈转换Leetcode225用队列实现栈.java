package 第Ⅴ关算法的备胎Hash和找靠山的队列.第2节队栈和Hash的经典算法题;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/implement-stack-using-queues/">用队列实现栈</a>
 *
 * @author AntonyCheng
 * @since 2023/9/12 15:34:30
 */

public class 队栈转换Leetcode225用队列实现栈 {
    /**
     * 用队列实现栈，中心思想就是queue1队列存储栈内元素，queue2队列作为入栈时的辅助队列，
     * 入栈时首先将元素入队进queue2，然后将queue1的全部元素依次出队并且入队进queue2中，
     * 此时queue1队列的前端和后端分别对应栈的栈顶和栈底，由以下示意图进行展示：
     * 为了提高检索性能，这里使用数组进行队列构造，入栈元素为[1,2,3]
     * 初始状态：
     * queue1    ?    ?    ?    ?
     * queue2    ?    ?    ?    ?
     * 第一次入栈（1）：
     * queue1    ?    ?    ?    ?
     * queue2    1    ?    ?    ?
     * 第一次入栈（2）：
     * queue1    ?    ?    ?    ?
     * queue2    1    ?    ?    ?
     * 第一次入栈（3）：                         最终状态：
     * queue2    ?    ?    ?    ?             queue2    ?    ?    ?    ?
     * queue1    1    ?    ?    ?             queue1    ?    ?    ?    ?
     * 第二次入栈（1）：                         ^
     * queue2    2    ?    ?    ?             |
     * queue1    1    ?    ?    ?             |
     * 第二次入栈（2）：                         第三次出栈（3）：
     * queue2    1    2    ?    ?             queue2    ?    ?    ?    ?
     * queue1    ?    ?    ?    ?             queue1    ?    ?    ?    ?
     * 第二次入栈（3）：                         ^
     * queue1    1    2    ?    ?             |
     * queue2    ?    ?    ?    ?             |
     * 第三次入栈（1）：                         第二次出栈（2）：
     * queue1    1    2    ?    ?             queue2    ?    ?    ?    ?
     * queue2    3    ?    ?    ?             queue1    3    ?    ?    ?
     * 第三次入栈（2）：                         ^
     * queue1    ?    ?    ?    ?             |
     * queue2    1    2    3    ?             |
     * 第三次入栈（3）：                ---->    第一次出栈（1）：
     * queue2    ?    ?    ?    ?             queue2    ?    ?    ?    ?
     * queue1    1    2    3    ?             queue1    2    3    ?    ?
     */
    class MyStack {
        /**
         * 数组队列
         * 这里使用从数组index==size入队，从数组index==0出队
         */
        private class QueueArray {
            /**
             * 元素数组
             */
            private Object[] queue;

            /**
             * 队列大小
             */
            private int size;

            QueueArray() {
                queue = new Object[10];
            }

            QueueArray(int capacity) {
                if (capacity <= 0) {
                    queue = new Object[10];
                } else {
                    queue = new Object[capacity];
                }
            }

            /**
             * 队列扩容（扩容50%）
             *
             * @param size
             */
            private void expandCapacity(int size) {
                int length = queue.length;
                if (size > length) {
                    size = size + size / 2;
                    queue = Arrays.copyOf(queue, size);
                }
            }

            /**
             * 入队
             *
             * @param value
             */
            public void push(Object value) {
                expandCapacity(size + 1);
                queue[size] = value;
                size++;
            }

            /**
             * 出队
             *
             * @return
             */
            public Object pull() {
                Object t = null;
                if (size != 0) {
                    t = queue[0];
                    for (int i = 0; i < size - 1; i++) {
                        queue[i] = queue[i + 1];
                    }
                    size--;
                }
                return t;
            }

            /**
             * 遍历队列
             */
            public void traverse() {
                for (int i = 0; i < size; i++) {
                    System.out.print(queue[i] + "\t");
                }
                System.out.println();
            }

            /**
             * 获取队列大小
             *
             * @return
             */
            public int size() {
                return size;
            }

            /**
             * 获取队尾元素
             *
             * @return
             */
            public Object peek() {
                return queue[0];
            }
        }

        private QueueArray queue1;

        private QueueArray queue2;

        public MyStack() {
            this.queue1 = new QueueArray();
            this.queue2 = new QueueArray();
        }

        public void push(int x) {
            queue2.push(x);
            while (queue1.size() > 0) {
                queue2.push(queue1.pull());
            }
            QueueArray temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        public int pop() {
            return (int) queue1.pull();
        }

        public int top() {
            return (int) queue1.peek();
        }

        public boolean empty() {
            return queue1.size == 0;
        }
    }
}
