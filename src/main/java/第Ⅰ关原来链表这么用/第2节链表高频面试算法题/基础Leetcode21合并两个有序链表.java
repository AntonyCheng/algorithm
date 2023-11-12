package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/merge-two-sorted-lists/">合并两个有序链表</a>
 *
 * @author AntonyCheng
 * @since 2023/8/30 12:36:54
 */

public class 基础Leetcode21合并两个有序链表 {
    /**
     * 集合辅助法（利用集合）
     * 将两个链表的所有数据装入一个集合中，排序后再复原成一个链表；
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoListsMethod1(ListNode list1, ListNode list2) {
        List<Integer> nodeList = new ArrayList<>();
        ListNode temp1 = list1;
        ListNode temp2 = list2;
        ListNode res = new ListNode(-1);
        ListNode tempRes = res;
        while (temp1 != null) {
            nodeList.add(temp1.val);
            temp1 = temp1.next;
        }
        while (temp2 != null) {
            nodeList.add(temp2.val);
            temp2 = temp2.next;
        }
        nodeList.sort((o1, o2) -> o1 - o2);
        for (Integer listNodeValue : nodeList) {
            tempRes.next = new ListNode(listNodeValue);
            tempRes = tempRes.next;
        }
        return res.next;
    }

    /**
     * 链表穿插法（直接使用原链表对新链表进行插入操作）
     * 直接利用链表的插入和删除操作进行新链表的构造
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoListsMethod2(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);
        ListNode newHead = res;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                newHead.next = list1;
                list1 = list1.next;
                newHead = newHead.next;
            } else if (list1.val > list2.val) {
                newHead.next = list2;
                list2 = list2.next;
                newHead = newHead.next;
            } else {
                newHead.next = list1;
                list1 = list1.next;
                newHead = newHead.next;
                newHead.next = list2;
                list2 = list2.next;
                newHead = newHead.next;
            }
        }
        while (list1 != null) {
            newHead.next = list1;
            list1 = list1.next;
            newHead = newHead.next;
        }
        while (list2 != null) {
            newHead.next = list2;
            list2 = list2.next;
            newHead = newHead.next;
        }
        return res.next;
    }

    /**
     * 链表穿插法（代码结构改进，性能有所降低版本）
     * 观察原方法的代码结构，可以发现有若干处重复的while操作，我们可以想办法合并一下
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoListsMethod3(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);
        ListNode newHead = res;
        // 首先关于两链表数据相等的情况，我们其实是可以不用单独考虑的，大不了就做两次while操作（这里就造成了内存性能的稍稍下降）
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
        // 在以上操作完毕之后，其实最后有且只能有一条链表不为空，所以直接使用三目运算符进行赋值即可；
        newHead.next = list1 != null ? list1 : list2;
        return res.next;
    }
}