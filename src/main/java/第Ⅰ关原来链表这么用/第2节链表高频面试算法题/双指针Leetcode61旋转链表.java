package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/rotate-list/">旋转链表</a>
 *
 * @author AntonyCheng
 * @since 2023/8/31 01:47:59
 */

public class 双指针Leetcode61旋转链表 {
    /**
     * 集合辅助+循环链表法
     * 根据题意先获取链表长度，再将所有的节点存入集合中，再把尾节点的next索引指向头节点，构成循环链表
     * 随后观察示例结果：
     * 0            0 -> 1 -> 2 length=3
     * 1            2 -> 0 -> 1
     * 2            1 -> 2 -> 0
     * 3            0 -> 1 -> 2
     * 4            2 -> 0 -> 1
     * 5            1 -> 2 -> 0
     * 6            0 -> 1 -> 2
     * ......
     * k%length==0  0 -> 1 -> 2
     * k%length==1  2 -> 0 -> 1
     * k%length==2  1 -> 2 -> 0
     * k%length==0  0 -> 1 -> 2
     * ......
     * 由上述结果可以得知结果头节点和k、length有唯一关系
     * 当我们找到头节点之后再通过length的值将循环链表截断即可；
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRightMethod1(ListNode head, int k) {
        List<ListNode> nodeList = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            nodeList.add(temp);
            temp = temp.next;
        }
        int length = nodeList.size();
        if (length == 0) {
            return null;
        }
        nodeList.get(length - 1).next = nodeList.get(0);

        int index = k % length;
        if (index == 0) {
            index = length;
        }
        ListNode desNode = nodeList.get(length - index);
        ListNode desTemp = desNode;
        for (int i = 1; i < length; i++) {
            desTemp = desTemp.next;
        }
        desTemp.next = null;
        return desNode;
    }

    /**
     * 双指针法：前后等距指针（经典且常用）
     * 观察题目示例可得：
     * input:   [1 2 3 4    5]  k=1
     * output:  [5    1 2 3 4]
     * input:   [1 2 3    4 5]  k=2
     * output:  [4 5    1 2 3]
     * input:   [1 2    3 4 5]  k=3
     * output:  [3 4 5    1 2]
     * input:   [1    2 3 4 5]  k=4
     * output:  [2 3 4 5    1]
     * input:   [   1 2 3 4 5]  k=5
     * output:  [1 2 3 4 5   ]
     * 如果我们找到分割的节点，然后再让他们独立成为两个链表，再进行拼接，就能够得到最后的答案；
     * 难点就在于如何找到分割的节点，该节点和K有关，那么这个解法就需要用到双指针；
     * 大白话就是前一个指针和后一个指针相隔一段距离，然后同时向后遍历，当后一个指针遍历至最后时，前一个指针的位置就是目标节点的位置
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRightMethod2(ListNode head, int k) {
        ListNode before = head;
        ListNode after = head;
        ListNode temp = head;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        if (length == 0) {
            return null;
        }
        int index = k % length;
        if (index == 0) {
            return head;
        }
        for (int i = 0; i < index; i++) {
            after = after.next;
        }
        while (after.next != null) {
            after = after.next;
            before = before.next;
        }
        ListNode res = before.next;
        before.next = null;
        after.next = head;
        return res;
    }

    /**
     * 反转链表法（反转链表是第二关的重点内容，这里只是做一个类似预习的练习）
     * 首先明确两个问题：
     * 1、如何反转链表
     * 下面会有两个常见的反转链表的方法（理解并记忆且背诵），这里不过多做赘述；
     * 2、为什么这个题能够使用反转链表
     * 观察题目示例可得：
     * input:   [1 2 3 4    5]  k=1
     * output:  [5    1 2 3 4]
     * input:   [1 2 3    4 5]  k=2
     * output:  [4 5    1 2 3]
     * input:   [1 2    3 4 5]  k=3
     * output:  [3 4 5    1 2]
     * input:   [1    2 3 4 5]  k=4
     * output:  [2 3 4 5    1]
     * input:   [   1 2 3 4 5]  k=5
     * output:  [1 2 3 4 5   ]
     * 将整个链表反转变成{5,4,3,2,1}，然后再将前K和N-K两个部分分别反转，也就是分别变成了{4,5}和{1,2,3},这样就轻松解决了
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRightMethod3(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (k == 0) {
            return head;
        }
        int length = 0;
        ListNode lengthTemp = head;
        while (lengthTemp != null) {
            length++;
            lengthTemp = lengthTemp.next;
        }
        // 整个链表进行反转
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // 后部分链表进行反转
        ListNode afterCurrent = prev;
        for (int i = 0; i < k%length; i++) {
            afterCurrent = afterCurrent.next;
        }
        ListNode afterPrev = null;
        while (afterCurrent != null) {
            ListNode next = afterCurrent.next;
            afterCurrent.next = afterPrev;
            afterPrev = afterCurrent;
            afterCurrent = next;
        }
        ListNode afterHead = afterPrev;

        // 前部分链表进行反转
        ListNode beforeCurrent = prev;
        ListNode beforePrev = null;
        for (int i = 0; i < k%length; i++) {
            ListNode next = beforeCurrent.next;
            beforeCurrent.next = beforePrev;
            beforePrev = beforeCurrent;
            beforeCurrent = next;
        }
        ListNode beforeHead = beforePrev;

        // 拼接操作
        boolean flag = true;
        while (flag) {
            if (beforePrev == null){
                beforeHead = afterHead;
                flag = false;
                continue;
            }
            if (beforePrev.next == null) {
                beforePrev.next = afterHead;
                flag = false;
                continue;
            }
            beforePrev = beforePrev.next;
        }
        return beforeHead;
    }

    /**
     * 建立虚拟节点进行链表的反转
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        ListNode dummyNode = new ListNode(-1);
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = dummyNode.next;
            dummyNode.next = current;
            current = next;
        }
        return dummyNode.next;
    }

    /**
     * 在原链表上进行链表的反转
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
