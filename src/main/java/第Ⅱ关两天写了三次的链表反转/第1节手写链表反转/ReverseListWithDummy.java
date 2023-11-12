package 第Ⅱ关两天写了三次的链表反转.第1节手写链表反转;

/**
 * 带有虚拟节点的反转链表（重点理解并且记忆）
 * 假设list=[1->2->3->4->5]，可以按照如下图示进行反转
 * dummy              1   ->   2   ->   3   ->   4   ->   5
 *                 current   next
 * ↓
 * {current.next==dummy.next;dummy.next==current;current==next;}
 * ↓
 * dummy   ->   1              2   ->   3   ->   4   ->   5
 *                          current   next
 * ↓
 * {current.next==dummy.next;dummy.next==current;current==next;}
 * ↓
 * dummy   ->   2   ->   1              3   ->   4   ->   5
 *                                   current   next
 * ......
 *
 * @author AntonyCheng
 * @since 2023/9/2 00:30:05
 */

public class ReverseListWithDummy {
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
        ListNode dummyNode = new ListNode(-1);
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = dummyNode.next;
            dummyNode.next = current;
            current = next;
        }
        return dummyNode.next;
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
