package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/palindrome-linked-list/">回文链表</a>
 *
 * @author AntonyCheng
 * @since 2023/8/30 08:33:42
 */

public class 基础Leetcode234回文链表 {
    /**
     * 直接法
     * 回文的特点就是回文内容关于内容中点对称，那么我们可以先找到中点位置，将前一半的节点存储起来，然后遍历后一半节点做比较；
     *
     * @param head
     * @return
     */
    public boolean isPalindromeMethod1(ListNode head) {
        ListNode tempCount = head;
        int count = 0;
        while (tempCount != null) {
            count++;
            tempCount = tempCount.next;
        }
        if (count == 0) {
            return false;
        }

        ListNode tempNode = head;
        Set<Integer> nodeValues = new HashSet<>();
        for (int i = 0; i < count / 2; i++) {
            nodeValues.add(tempNode.val);
            tempNode = tempNode.next;
        }
        if (count % 2 != 0) {
            tempNode = tempNode.next;
        }

        for (int i = nodeValues.size(); i > 0; i--) {
            if (nodeValues.iterator().next() != tempNode.val) {
                return false;
            }
            tempNode = tempNode.next;
        }
        return true;
    }

    /**
     * 堆栈法
     * 直接法思路清晰，但是利用到了集合，换种说法就是改变了这道题的性质，脱离了链表，但是上述思维我们可以使用堆栈代替
     * 也就是将前一半数据压入栈中，让后一半数据依次和出栈的每个值作比较
     *
     * @param head
     * @return
     */
    public boolean isPalindromeMethod2(ListNode head) {
        ListNode tempCount = head;
        int count = 0;
        while (tempCount != null) {
            count++;
            tempCount = tempCount.next;
        }
        if (count == 0) {
            return false;
        }

        ListNode tempNode = head;
        Stack<Integer> nodeValues = new Stack<>();
        for (int i = 0; i < count / 2; i++) {
            nodeValues.push(tempNode.val);
            tempNode = tempNode.next;
        }
        if (count % 2 != 0) {
            tempNode = tempNode.next;
        }

        for (int i = 0; i < count / 2; i++) {
            if (nodeValues.pop() != tempNode.val) {
                return false;
            }
            tempNode = tempNode.next;
        }
        return true;
    }

    /**
     * 堆栈法（代码简化，性能弱化版本）
     * 原版堆栈法主要按照直接法转换得到的，这样就在获取链表长度时出现了运算冗余，
     * 我们可以压栈和计算链表长度同时进行，出栈时就只验证一半
     *
     * @param head
     * @return
     */
    public boolean isPalindromeMethod3(ListNode head) {
        ListNode temp = head;
        int length = 0;
        Stack<Integer> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp.val);
            length++;
            temp = temp.next;
        }

        for (int i = 0; i < length / 2; i++) {
            if (stack.pop() != head.val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }
}
