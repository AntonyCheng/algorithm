package 第Ⅱ关两天写了三次的链表反转.第1节手写链表反转;

import java.util.Stack;

/**
 * 通过栈实现链表反转（该方法仅做拓展，了解即可）
 *
 * @author AntonyCheng
 * @since 2023/9/2 16:05:30
 */

public class ReverseListByStack {
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
        Stack<Integer> listValues = new Stack<>();
        while (head != null) {
            listValues.push(head.val);
            head = head.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        int length = listValues.size();
        for (int i = 0; i < length; i++) {
            temp.next = new ListNode(listValues.pop());
            temp = temp.next;
        }
        return dummy.next;
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
