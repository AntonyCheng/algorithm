package 第Ⅱ关两天写了三次的链表反转.第1节手写链表反转;

/**
 * 不带有虚拟节点的反转链表（必须重点理解并且记忆）
 * 假设list=[1->2->3->4->5]，prev节点为null，可以按照如下图示进行反转
 * null            1   ->   2   ->   3   ->   4   ->   5
 * prev         current   next
 * ↓
 * {current.next==prev;prev==current;current==next;}
 * ↓
 * null   <-   1            2   ->   3   ->   4   ->   5
 *           prev        current   next
 * ↓
 * {current.next==prev;prev==current;current==next;}
 * ↓
 * null   <-   1   <-   2            3   ->   4   ->   5
 *                    prev        current   next
 * ......
 *
 * @author AntonyCheng
 * @since 2023/9/2 00:50:41
 */

public class ReverseListWithoutDummy {
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
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
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
