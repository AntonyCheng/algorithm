package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除链表M个节点之后的N个节点（Leetcode Plus题目，需要VIP，本地测试成功即可）
 *
 * @author AntonyCheng
 * @since 2023/9/1 10:16:57
 */

public class 删除特定节点Leetcode1474删除链表M个节点之后的N个节点 {
    /**
     * 集合辅助法
     * 将所有节点存入集合中，在集合中进行删除操作
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode removeMthToNthMethod1(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        List<Integer> listValues = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            listValues.add(temp.val);
            temp = temp.next;
        }
        for (int i = 0; i < n; i++) {
            if (listValues.size() == m) {
                break;
            }
            listValues.remove(m);
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode dummyTemp = dummyNode;
        for (Integer listValue : listValues) {
            dummyTemp.next = new ListNode(listValue);
            dummyTemp = dummyTemp.next;
        }
        return dummyNode.next;
    }

    /**
     * 虚拟节点构造法
     * 构造一个虚拟节点，在M之前（包括M）的节点都加入到虚拟节点后，M之后的N个节点不加入虚拟节点中；
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode removeMthToNthMethod2(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode dummyTemp = dummyNode;
        int index = 1;
        while (head != null) {
            if (index <= m || index > n + m) {
                dummyTemp.next = new ListNode(head.val);
                dummyTemp = dummyTemp.next;
            }
            index++;
            head = head.next;
        }
        return dummyNode.next;
    }

    /**
     * 双指针法：前后等距指针（经典且常用）
     * 先构造一对相距n个节点的前后指针对，再同时平移m个位置，然后将指针对之间的节点看成一个整体，最后进行删除
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode removeMthToNthMethod3(ListNode head, int m, int n) {
        ListNode before = head;
        ListNode after = head;
        for (int i = 0; i < n + 1; i++) {
            if (after != null) {
                after = after.next;
            }
        }
        for (int i = 1; i < m; i++) {
            if (before == null) {
                return head;
            }
            before = before.next;
            if (after != null) {
                after = after.next;
            }
        }
        before.next = after;
        return head;
    }
}
