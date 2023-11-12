package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">删除链表的倒数第N个结点</a>
 *
 * @author AntonyCheng
 * @since 2023/9/1 00:50:28
 */

public class 删除特定节点Leetcode19删除链表的倒数第N个节点 {
    /**
     * 集合辅助法
     * 将所有节点存入集合，删除目标节点之后重新构造一条新链；
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEndMethod1(ListNode head, int n) {
        List<Integer> nodeValues = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            nodeValues.add(temp.val);
            temp = temp.next;
        }
        nodeValues.remove(nodeValues.size() - n);
        ListNode dummyNode = new ListNode(-1);
        ListNode tempNode = dummyNode;
        for (Integer value : nodeValues) {
            tempNode.next = new ListNode(value);
            tempNode = tempNode.next;
        }
        return dummyNode.next;
    }

    /**
     * 链表遍历删除法
     * 首先找到链表的长度（length），然后根据
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEndMethod2(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        int length = 0;
        ListNode lengthTemp = head;
        while (lengthTemp != null) {
            length++;
            lengthTemp = lengthTemp.next;
        }

        if (n == length) {
            head = head.next;
            return head;
        }
        if (n == 1) {
            ListNode temp = head;
            for (int i = 1; i < length - 1; i++) {
                temp = temp.next;
            }
            temp.next = null;
            return head;
        }

        ListNode temp = head;
        for (int i = 1; i < length - n; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;

        return head;
    }

    /**
     * 双指针法：前后等距指针（经典且常用）
     * 先利用双指针找到目标位置，然后进行删除操作；
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEndMethod3(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode before = dummy;
        ListNode after = head;
        for (int i = 0; i < n; i++) {
            after = after.next;
        }
        while (after != null) {
            after = after.next;
            before = before.next;
        }
        before.next = before.next.next;
        return dummy.next;
    }
}
