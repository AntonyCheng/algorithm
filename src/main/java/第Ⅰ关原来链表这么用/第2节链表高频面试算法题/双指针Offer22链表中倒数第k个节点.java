package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/">链表中倒数第k个节点</a>
 * 链表中倒数第k个节点
 *
 * @author AntonyCheng
 * @since 2023/8/31 01:29:04
 */

public class 双指针Offer22链表中倒数第k个节点 {
    /**
     * 反转链表法（大白话就是用集合装起来，反向遍历）
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEndMethod1(ListNode head, int k) {
        List<ListNode> nodeList = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            nodeList.add(temp);
            temp = temp.next;
        }
        ListNode res = null;
        for (int i = nodeList.size() - 1; i >= nodeList.size() - k; i--) {
            res = nodeList.get(i);
        }
        return res;
    }

    /**
     * 堆栈法（大白话就是用堆栈装装起来，弹出K个即可）
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEndMethod2(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        ListNode res = null;
        for (int i = 0; i < k; i++) {
            res = stack.pop();
        }
        return res;
    }

    /**
     * 双指针法：前后等距指针（经典且常用）
     * 我们可以设计两个指针，使前一个指针和后一个指针所指内容相差K个，然后同时平移，
     * 当后一个指针遍历到最后一个元素时，前一个指针指向的内容就是倒数第K个；
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEndMethod3(ListNode head, int k) {
        ListNode before = head;
        ListNode after = head;
        for (int i = 0; i < k; i++) {
            after = after.next;
        }
        while (after != null) {
            before = before.next;
            after = after.next;
        }
        return before;
    }
}
