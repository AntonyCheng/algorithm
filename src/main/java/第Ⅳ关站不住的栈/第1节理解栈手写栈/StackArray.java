package 第Ⅳ关站不住的栈.第1节理解栈手写栈;

import java.util.Arrays;

/**
 * 手写数组栈
 *
 * @author AntonyCheng
 * @since 2023/9/11 08:53:31
 */

public class StackArray<T> {
    /**
     * 元素数组
     */
    private Object[] stack;

    /**
     * 栈顶
     */
    private int top;

    StackArray() {
        stack = new Object[10];
    }

    StackArray(int capacity) {
        if (capacity <= 0) {
            stack = new Object[10];
        } else {
            stack = new Object[capacity];
        }
    }

    /**
     * 栈扩容（扩容50%）
     *
     * @param size
     */
    private void expandCapacity(int size) {
        int length = stack.length;
        if (size > length) {
            size = size + size / 2;
            stack = Arrays.copyOf(stack, size);
        }
    }

    /**
     * 入栈
     *
     * @param val
     */
    public void push(T val) {
        expandCapacity(top + 1);
        stack[top] = val;
        top++;
    }

    /**
     * 获取栈顶
     *
     * @return
     */
    public T peek() {
        T t = null;
        if (top > 0) {
            t = (T) stack[top - 1];
        }
        return t;
    }

    /**
     * 出栈
     *
     * @return
     */
    public T pop() {
        T t = null;
        if (top > 0) {
            t = (T) stack[top - 1];
            top--;
        }
        return t;
    }

    /**
     * 判空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * 获取长度
     *
     * @return
     */
    public int length() {
        return top;
    }
}

class StackArrayDemo {
    public static void main(String[] args) {
        StackArray<String> stack = new StackArray<>();
        System.out.println(stack.peek());
        System.out.println(stack.isEmpty());
        stack.push("java");
        stack.push("is");
        stack.push("beautiful");
        stack.push("language");
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
    }
}
