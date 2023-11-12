package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

/**
 * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/">合并K个升序链表</a>
 *
 * @author AntonyCheng
 * @since 2023/8/30 23:37:57
 */

public class 基础Leetcode23合并K个升序链表 {
    /**
     * 直接法（其实这个只要合并两个有序链表（Leetcode21）能够设计出来，那么K个就不是问题）
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for (ListNode list : lists) {
            res = mergeTwoLists(res, list);
        }
        return res;
    }

    /**
     * 合并两个有序链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);
        ListNode newHead = res;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                newHead.next = list1;
                list1 = list1.next;
            } else {
                newHead.next = list2;
                list2 = list2.next;
            }
            newHead = newHead.next;
        }
        newHead.next = list1 != null ? list1 : list2;
        return res.next;
    }
}
