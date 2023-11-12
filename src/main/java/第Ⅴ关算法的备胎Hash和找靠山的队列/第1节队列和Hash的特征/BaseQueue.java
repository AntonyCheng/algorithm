package 第Ⅴ关算法的备胎Hash和找靠山的队列.第1节队列和Hash的特征;

import java.util.Arrays;

/**
 * 队列的基本特征：先进先出——FIFO
 * 能够基于链表和数组进行实现，基于链表实现相对简单很多；
 * Java中的队列没有像Stack一样的直接的类；
 * 但有一个Queue接口，被继承者中常用的就是LinkedList类（链表队列）和ArrayDeque类（数组队列）；
 * 为了原始操作，在下面就将实现一个链表队列和一个数组队列；
 * 队列的常用操作主要有：
 * ● offer(E):入队
 * ● poll():出队
 * ● peek():查看队尾元素
 * ● traverse():遍历队列
 * ● size():获取队列大小
 *
 * @author AntonyCheng
 * @since 2023/9/11 23:34:16
 */

public class BaseQueue {
    public static void main(String[] args) {
        QueueArray queue = new QueueArray(1);
        //    QueueLinkedList queue = new QueueLinkedList(1);
        queue.offer(1);
        System.out.println(queue.size());
        queue.traverse();
        queue.offer(2);
        System.out.println(queue.size());
        queue.traverse();
        queue.offer(3);
        System.out.println(queue.size());
        queue.traverse();
        queue.offer(4);
        System.out.println(queue.size());
        queue.traverse();
        queue.offer(5);
        System.out.println(queue.size());
        queue.traverse();
        System.out.println(queue.poll());
        queue.traverse();
        System.out.println(queue.poll());
        queue.traverse();
        System.out.println(queue.poll());
        queue.traverse();
        System.out.println(queue.poll());
        queue.traverse();
        System.out.println(queue.size());
        System.out.println(queue.poll());
        queue.traverse();
        System.out.println(queue.poll());
        System.out.println(queue.size());
    }
}

/**
 * 链表队列
 * 这里使用从链尾入队，从链首出队
 */
class QueueLinkedList {
    /**
     * 节点类
     */
    private class Node {
        public Object t;
        public Node next;

        Node(Object t) {
            this.t = t;
            this.next = null;
        }
    }

    /**
     * 队头
     */
    private Node front;

    /**
     * 队尾
     */
    private Node rear;

    /**
     * 队列大小
     */
    private int size;

    public QueueLinkedList() {
        this.front = new Node(null);
        this.rear = null;
    }

    /**
     * 入队
     *
     * @param value
     */
    public void offer(Object value) {
        Node newNode = new Node(value);
        Node temp = front;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        rear = newNode;
        size++;
    }

    /**
     * 出队
     *
     * @return
     */
    public Object poll() {
        if (front.next == null) {
            return null;
        }
        Object t = front.next.t;
        front.next = front.next.next;
        size--;
        return t;
    }

    /**
     * 查看队尾元素
     *
     * @return
     */
    public Object peek() {
        if (front.next == null) {
            return null;
        }
        return front.next.t;
    }

    /**
     * 遍历队列
     */
    public void traverse() {
        Node temp = front.next;
        while (temp != null) {
            System.out.print(temp.t + "\t");
            temp = temp.next;
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
}

/**
 * 数组队列
 * 这里使用从数组index==size入队，从数组index==0出队
 */
class QueueArray {
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
    public void offer(Object value) {
        expandCapacity(size + 1);
        queue[size] = value;
        size++;
    }

    /**
     * 出队
     *
     * @return
     */
    public Object poll() {
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
     * 查看队尾值
     *
     * @return
     */
    public Object peek() {
        Object t = null;
        if (size != 0) {
            t = queue[0];
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
}