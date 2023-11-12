package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/middle-of-the-linked-list/">链表的中间结点</a>
 *
 * @author AntonyCheng
 * @since 2023/8/31 01:02:20
 */

public class 双指针Leetcode876链表的中间节点 {
    /**
     * 遍历获取长度法
     * 先获取链表长度，在进行二次遍历中间节点
     *
     * @param head
     * @return
     */
    public ListNode middleNodeMethod1(ListNode head) {
        ListNode lengthTemp = head;
        int length = 0;
        while (lengthTemp != null) {
            length++;
            lengthTemp = lengthTemp.next;
        }

        ListNode temp = head;
        for (int i = 0; i < length / 2; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 堆栈法（用集合也行，就是使用容器辅助的思路）
     * 使用堆栈容纳所有节点，然后弹出一半的节点
     *
     * @param head
     * @return
     */
    public ListNode middleNodeMethod2(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        return stack.get(stack.size() / 2 + 1);
    }

    /**
     * 双指针法：快慢指针（经典且常用）
     * 找寻可迭代对象的中点常用的一种高效思维，
     * 第一个指针向后走一个单位，第二个指针向后走两个单位，当第二个指针遍历完可迭代对象后，第一个指针恰好就在中点位置
     *
     * @param head
     * @return
     */
    public ListNode middleNodeMethod3(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
