package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/">删除排序链表中的重复元素II</a>
 *
 * @author AntonyCheng
 * @since 2023/9/1 21:45:44
 */

public class 删除特定节点Leetcode82删除排序链表中的重复元素II {
    /**
     * 集合和Map辅助法
     * 将所有节点值存放进集合中，在集合中处理完数据之后，重新构建新的链表
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
        Map<Integer, Integer> numCount = new HashMap<>();
        for (Integer listValue : listValues) {
            Integer num = numCount.get(listValue);
            if (num == null) {
                numCount.put(listValue, 1);
            } else {
                num++;
                numCount.put(listValue, num);
            }
        }
        List<Integer> keys = new ArrayList<>(numCount.keySet());
        List<Integer> values = new ArrayList<>(numCount.values());
        int count = numCount.size();
        for (int i = 0; i < count; i++) {
            if (values.get(i) > 1) {
                numCount.remove(keys.get(i));
            }
        }
        List<Integer> resList = new ArrayList<>(numCount.keySet());
        resList.sort((o1, o2) -> {
            return o1 - o2;
        });
        ListNode dummyNode = new ListNode(-1);
        ListNode dummyTemp = dummyNode;
        for (Integer integer : resList) {
            dummyTemp.next = new ListNode(integer);
            dummyTemp = dummyTemp.next;
        }
        return dummyNode.next;
    }

    /**
     * 直接遍历法
     * 首先进行去重操作，去重的同时使用集合搜集重复节点的值，待去重后再根据集合进行重复值的删除操作
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesMethod2(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> repeatList = new ArrayList<>();
        ListNode current = head;
        while (current.next != null) {
            if (current.next.val == current.val) {
                repeatList.add(current.val);
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode dummyTemp = dummyNode;
        while (dummyTemp.next != null) {
            if (repeatList.contains(dummyTemp.next.val)) {
                dummyTemp.next = dummyTemp.next.next;
            } else {
                dummyTemp = dummyTemp.next;
            }
        }
        return dummyNode.next;
    }

    /**
     * 直接遍历法（改进版本）
     * 由于原版本依旧使用了集合外部辅助，所以原版本不能完全算是直接遍历链表;
     * 又因为原链表为升序排列，那么重复的数据一定相邻，
     * 那么我们只需要创建虚拟结点之后考虑current.next和current.next.next节点；
     * head:    1       2       3       3       4       4       5
     * dummy -> 1       2       3       3       4       4       5
     * current = dummy;
     * current->1       2       3       3       4       4       5
     *        next   n.next  n.n.next
     *
     *      current->   2       3       3       4       4       5
     *                next   n.next  n.n.next
     *
     *              current->   3       3       4       4       5
     *                        next   n.next  n.n.next
     *                   while(current.next == current.next.next)
     * 这一步之后就已经让current.next == current.next.next.next
     * current                      current->   4       4       5
     *                                        next   n.next  n.n.next
     *                                        ...................
     * @param head
     * @return
     */
    public ListNode deleteDuplicatesMethod3(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode current = dummyNode;
        while (current.next != null && current.next.next != null) {
            if (current.next.val == current.next.next.val) {
                int x = current.next.val;
                while (current.next != null && current.next.val == x) {
                    current.next = current.next.next;
                }
            } else {
                current = current.next;
            }
        }
        return dummyNode.next;
    }
}
