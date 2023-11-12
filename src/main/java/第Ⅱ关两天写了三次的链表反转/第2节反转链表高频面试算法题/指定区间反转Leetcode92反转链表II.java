package 第Ⅱ关两天写了三次的链表反转.第2节反转链表高频面试算法题;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/reverse-linked-list-ii/">反转链表II</a>
 *
 * @author AntonyCheng
 * @since 2023/9/2 01:31:05
 */

public class 指定区间反转Leetcode92反转链表II {
    /**
     * 集合辅助法
     * 将所有节点的值存入集合中，进行操作后重新构建新的链表
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetweenMethod1(ListNode head, int left, int right) {
        List<Integer> listValues = new ArrayList<>();
        ListNode tempNode = head;
        while (tempNode != null) {
            listValues.add(tempNode.val);
            tempNode = tempNode.next;
        }
        List<Integer> tempValues = new ArrayList<>();
        for (int i = left - 1; i <= right - 1; i++) {
            tempValues.add(listValues.get(i));
        }
        for (int i = 0; i < tempValues.size() / 2; i++) {
            int temp = tempValues.get(tempValues.size() - 1 - i);
            tempValues.set(tempValues.size() - 1 - i, tempValues.get(i));
            tempValues.set(i, temp);
        }
        int index = 0;
        for (int i = left - 1; i <= right - 1; i++) {
            listValues.set(i, tempValues.get(index));
            index++;
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
     * 切割链表反转拼接法
     * 由于只反转部分，所以可以将链表分为“左中右”三部分，将中间部分反转后，进行链表的拼接即可；
     * 注意：如何从链表第一个节点开始反转或者最后一个节点也需要反转，就只能分割成两部分，甚至一部分，
     * 为了报障代码的通用性，这里可以引入一个虚拟节点，假设一个尾部空节点（null）
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetweenMethod2(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode leftTail = dummyNode;
        ListNode middleHead = leftTail.next;
        ListNode rightHead = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            leftTail = leftTail.next;
            middleHead = middleHead.next;
        }
        for (int i = 0; i < right + 1; i++) {
            if (rightHead != null) {
                rightHead = rightHead.next;
            } else {
                break;
            }
        }
        leftTail.next = null;
        ListNode middleTemp = middleHead;
        int length = right - left;
        for (int i = 0; i < length; i++) {
            middleTemp = middleTemp.next;
        }
        middleTemp.next = null;

        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        while (middleHead != null) {
            ListNode next = middleHead.next;
            middleHead.next = temp.next;
            temp.next = middleHead;
            middleHead = next;
        }
        ListNode newMiddleHead = dummy.next;

        leftTail.next = newMiddleHead;
        while (newMiddleHead.next != null) {
            newMiddleHead = newMiddleHead.next;
        }
        newMiddleHead.next = rightHead;
        return dummyNode.next;
    }

    /**
     * 头插法：类似于带有虚拟节点的反转链表
     * 切割链表反转拼接法代码逻辑并不复杂，但是牺牲了过多的空间，这里我们用整体的思想去解决这个问题；
     * 由问题我们可以画出如下流程图（head=[1,2,3,4,5,6,7]，left=3，right=6）：
     * 翻转前：     1    ->    2    ->    3    ->    4    ->    5    ->    6    ->    7
     * {首先找到pre节点，将pre节点及pre节点之前的所有节点看成一个整体作为虚拟节点}
     * 第一步：     1    ->    2    ->   (3)   ->    4    ->    5    ->    6    ->    7
     *                      pre      current      next
     * {next==current.next;current.next==next.next;next.next==pre.next;pre.next==next;}
     * 第二步：     1    ->    2    ->   (4    ->    3)   ->    5    ->    6    ->    7
     *                      pre                 current      next
     * {next==current.next;current.next==next.next;next.next==pre.next;pre.next==next;}
     * 第三步：     1    ->    2    ->   (5    ->    4    ->    3)   ->    6    ->    7
     *                      pre                            current      next
     * {next==current.next;current.next==next.next;next.next==pre.next;pre.next==next;}
     * 第四步：     1    ->    2    ->   (6    ->    5    ->    4    ->    3)   ->    7
     *                      pre                                       current      next
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetweenMethod3(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left-1; i++) {
            pre = pre.next;
        }
        ListNode current = pre.next;
        ListNode next = null;
        for (int i = 0; i < right - left; i++) {
            next = current.next;
            current.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    /**
     * 穿针引线法（切割链表反转拼接法代码优化版本）
     * 这个方法听起来很高级，不好理解，其实内核就是“切割链表反转拼接法”，
     * 只不过上面方法编码复杂程度较高，接下来用图像理解一下（head=[1,2,3,4,5,6,7]，left=3，right=6）：
     * 反转之前：     1    ->    2    ->   (3    ->    4    ->    5    ->    6)   ->    7
     *                       prev       left                             right      succ
     *                                 {先找到需要反转的部分}
     * 反转之后：     1    ->    2    ||   (3    <-    4    <-    5    <-    6)   ||    7
     *                       prev       left                             right      succ
     *                                 {部分反转后的指向箭头}
     *                               ↑-------------------------------------↓
     * 穿针引线：     1    ->    2----↑    (3    <-    4    <-    5    <-    6)    ↑----7
     *                                    ↓-------------------------------------↑
     *                       prev       left                             right      succ
     *
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetweenMethod4(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode prev = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        ListNode leftNode = prev.next;

        ListNode rightNode = prev;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        ListNode succ = rightNode.next;
        rightNode.next =null;

        ListNode prevTemp = null;
        ListNode currentTemp = leftNode;
        while (currentTemp != null) {
            ListNode next = currentTemp.next;
            currentTemp.next = prevTemp;
            prevTemp = currentTemp;
            currentTemp = next;
        }
        prev.next = prevTemp;
        leftNode.next = succ;
        return dummyNode.next;
    }
}
