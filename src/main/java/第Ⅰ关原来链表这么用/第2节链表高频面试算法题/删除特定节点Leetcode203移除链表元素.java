package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/remove-linked-list-elements/">移除链表元素</a>
 *
 * @author AntonyCheng
 * @since 2023/8/31 23:41:52
 */

public class 删除特定节点Leetcode203移除链表元素 {
    /**
     * 集合辅助法
     * 将所有节点存入集合，去重操作之后重新构造一条新链；
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElementsMethod1(ListNode head, int val) {
        List<Integer> nodeValues = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            nodeValues.add(temp.val);
            temp = temp.next;
        }
        nodeValues.removeIf(value -> {
            return value == val;
        });
        ListNode dummyNode = new ListNode(-1);
        ListNode tempNode = dummyNode;
        for (Integer value : nodeValues) {
            tempNode.next = new ListNode(value);
            tempNode = tempNode.next;
        }
        return dummyNode.next;
    }

    /**
     * 链表节点删除法
     * 使用传统的链表节点删除方法进行直接删除，这里为了避免
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElementsMethod2(ListNode head, int val) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode dummyTemp = dummyNode;
        while (dummyTemp.next != null) {
            if (dummyTemp.next.val == val) {
                dummyTemp.next = dummyTemp.next.next;
            }else {
                dummyTemp = dummyTemp.next;
            }
        }
        return dummyNode.next;
    }
}
