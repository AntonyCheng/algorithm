package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

/**
 * <a href="https://leetcode.cn/problems/merge-in-between-linked-lists/">合并两个链表</a>
 *
 * @author AntonyCheng
 * @since 2023/8/30 23:51:52
 */

public class 基础Leetcode1669合并两个链表 {
    /**
     * 直接链表增删法
     * 根据题意直接利用链表的增删操作进行解题，难点就是a->b距离的判断，这里可以选择使用四个指针对象来标记四个连接节点（一条链两个）
     *
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetweenMethod1(ListNode list1, int a, int b, ListNode list2) {
        ListNode pointer1A = list1;
        ListNode pointer1B = list1;
        ListNode pointer2A = list2;
        ListNode pointer2B = list2;
        for (int i = 0; i < a - 1; i++) {
            pointer1A = pointer1A.next;
        }
        for (int i = 0; i < b; i++) {
            pointer1B = pointer1B.next;
        }
        while (pointer2B != null) {
            pointer2B = pointer2B.next;
            if (pointer2B.next == null) {
                break;
            }
        }
        pointer1A.next = pointer2A;
        pointer2B.next = pointer1B.next;
        return list1;
    }
}
