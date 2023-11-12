package 第Ⅳ关站不住的栈.第1节理解栈手写栈;

/**
 * 手写链表栈
 *
 * @author AntonyCheng
 * @since 2023/9/11 14:11:13
 */

public class StackLinkedList<T> {
    /**
     * 节点类
     */
    private class Node {
        public T t;
        public Node next;

        Node(T t) {
            this.t = t;
            this.next = null;
        }
    }

    /**
     * 头节点
     */
    private Node top;

    /**
     * 链表长度
     */
    private int size;

    /**
     * 入栈
     *
     * @param t
     */
    public void push(T t) {
        if (t == null) {
            throw new NullPointerException("参数不能为空");
        }
        if (top == null) {
            top = new Node(t);
        } else {
            Node newNode = new Node(t);
            newNode.next = top;
            top = newNode;
        }
        size++;
    }

    /**
     * 获取栈顶
     *
     * @return
     */
    public T peek() {
        return top == null ? null : top.t;
    }

    /**
     * 出栈
     *
     * @return
     */
    public T pop() {
        if (top == null) {
            return null;
        }
        T t = top.t;
        top = top.next;
        size--;
        return t;
    }

    /**
     * 判空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * 获取长度
     *
     * @return
     */
    public int length() {
        return size;
    }
}

class StackLinkedListDemo {
    public static void main(String[] args) {
        StackLinkedList<String> stack = new StackLinkedList<>();
        System.out.println(stack.peek());
        System.out.println(stack.isEmpty());
        stack.push("java");
        stack.push("is");
        stack.push("beautiful");
        stack.push("language");
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.isEmpty());
    }
}