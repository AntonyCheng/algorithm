package 第Ⅱ关两天写了三次的链表反转.第2节反转链表高频面试算法题;

/**
 * <a href="https://leetcode.cn/problems/swap-nodes-in-pairs/">两两交换链表中的节点</a>
 *
 * @author AntonyCheng
 * @since 2023/9/2 22:17:18
 */

public class 经典例题Leetcode24两两交换链表中的节点 {
    /**
     * 直接修改链表法
     * 按照题目要求（只能进行节点交换），该题有且只有一种解决思想，就是在原链表上直接操作；
     * 交换图示如下（head=[1,2,3,4]）：
     * 交换前： dummy    ->    1    ->    2    ->    3    ->    4
     *
     *                   ↑--------------↓
     * 交换时： dummy   --↑    1 <------- 2    ↑-->  3    ->    4
     *                       ↓---------------↑
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode dummyTemp = dummyNode;
        while (dummyTemp.next != null && dummyTemp.next.next != null){
            ListNode next = dummyTemp.next;
            ListNode nextAndNext = next.next;
            dummyTemp.next = nextAndNext;
            next.next = nextAndNext.next;
            nextAndNext.next = next;
            dummyTemp = nextAndNext;
        }
        return dummyNode.next;
    }
}
