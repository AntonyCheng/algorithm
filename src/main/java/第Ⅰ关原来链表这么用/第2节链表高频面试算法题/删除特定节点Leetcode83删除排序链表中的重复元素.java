package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list">删除排序链表中的重复元素</a>
 *
 * @author AntonyCheng
 * @since 2023/9/1 16:13:04
 */

public class 删除特定节点Leetcode83删除排序链表中的重复元素 {
    /**
     * 集合辅助法
     * 我们可以直接通过集合收集进行流式去重以及排序，最后进行链表的重新构造；
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesMethod1(ListNode head) {
        List<Integer> listValues = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            listValues.add(temp.val);
            temp = temp.next;
        }
        listValues = listValues.stream().distinct().sorted(((o1, o2) -> {
            return o1 - o2;
        })).collect(Collectors.toList());
        ListNode dummyNode = new ListNode(-1);
        ListNode dummyTemp = dummyNode;
        for (Integer listValue : listValues) {
            dummyTemp.next = new ListNode(listValue);
            dummyTemp = dummyTemp.next;
        }
        return dummyNode.next;
    }

    /**
     * Hash辅助法
     * 我们也可以直接将所有节点的值放入Hash表中，Hash表能自动去重，完事儿后排序、重新构建链表即可
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesMethod2(ListNode head) {
        Set<Integer> listValuesSet = new HashSet<>();
        ListNode temp = head;
        while (temp != null) {
            listValuesSet.add(temp.val);
            temp = temp.next;
        }
        List<Integer> listValues = new ArrayList<>(listValuesSet);
        listValues.sort((o1, o2) -> {
            return o1 - o2;
        });
        ListNode dummyNode = new ListNode(-1);
        ListNode dummyTemp = dummyNode;
        for (Integer listValue : listValues) {
            dummyTemp.next = new ListNode(listValue);
            dummyTemp = dummyTemp.next;
        }
        return dummyNode.next;
    }

    /**
     * 双指针法：遍历指针
     * 可以设计两个指针，第一个指针每经过一个节点，第二个指针就遍历剩下的所有节点
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesMethod3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode translateNode = head;
        while (translateNode != null) {
            ListNode traverseNode = translateNode;
            while (traverseNode.next != null) {
                if (traverseNode.next.val == translateNode.val) {
                    traverseNode.next = traverseNode.next.next;
                } else {
                    traverseNode = traverseNode.next;
                }
            }
            translateNode = translateNode.next;
        }
        return head;
    }

    /**
     * 直接遍历法
     * 由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，
     * 因此我们只需要对链表进行一次遍历，就可以删除重复的元素。
     * 具体地，我们从指针current指向链表的头节点，随后开始对链表进行遍历。
     * 如果当前current与current.next对应的元素相同，那么我们就将current.next从链表中移除；
     * 否则说明链表中已经不存在其它与current对应的元素相同的节点，
     * 因此可以将current指向current.next。当遍历完整个链表之后，我们返回链表的头节点即可。
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesMethod4(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode current = head;
        while (current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}
