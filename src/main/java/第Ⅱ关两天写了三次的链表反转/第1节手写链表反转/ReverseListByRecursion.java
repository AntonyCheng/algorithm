package 第Ⅱ关两天写了三次的链表反转.第1节手写链表反转;

/**
 * 通过递归算法实现链表反转（递归在这里不做重点，了解即可）
 *
 * @author AntonyCheng
 * @since 2023/9/2 01:11:56
 */

public class ReverseListByRecursion {
    /**
     * 节点类
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);
        list.next.next.next.next = new ListNode(5);

        ListNode res = reverseList(list);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
