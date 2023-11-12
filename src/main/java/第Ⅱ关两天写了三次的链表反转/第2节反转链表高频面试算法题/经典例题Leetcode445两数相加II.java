package 第Ⅱ关两天写了三次的链表反转.第2节反转链表高频面试算法题;

/**
 * <a href="https://leetcode.cn/problems/add-two-numbers-ii/">两数相加II</a>
 *
 * @author AntonyCheng
 * @since 2023/9/3 14:25:14
 */

public class 经典例题Leetcode445两数相加II {
    /**
     * 字符串法（思路可行，但Leetcode无法通过，因为根据题目要求链长范围在[1,100]，Long型都无法满足这个位数）
     * 先把链表转换成字符串，然后再进行加法运算，得到的结果进行上述操作的逆过程
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersMethod1(ListNode l1, ListNode l2) {
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();
        while (l1 != null) {
            string1.append(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            string2.append(l2.val);
            l2 = l2.next;
        }
        long num1 = Long.parseLong(string1.toString());
        long num2 = Long.parseLong(string2.toString());
        long resultNum = num1 + num2;
        char[] result = Long.toString(resultNum).toCharArray();
        ListNode resultNode = new ListNode(-1);
        ListNode temp = resultNode;
        for (char c : result) {
            temp.next = new ListNode(c - '0');
            temp = temp.next;
        }
        return resultNode.next;
    }

    /**
     * 反转链表法
     * 首先将两条链反转，然后逐位相加并记录余数，创建新链表后接入这些相加结果，相加完毕之后再进行反转新链表，即可得到答案
     * 由于要多次进行反转链表，所以可以单独写出一个方法；
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersMethod2(ListNode l1, ListNode l2) {
        ListNode reverse1 = reverseList(l1);
        ListNode reverse2 = reverseList(l2);
        ListNode dummyNode = new ListNode(-1);
        ListNode tempNode = dummyNode;
        int remainder = 0;
        while (reverse1 != null || reverse2 != null) {
            int value1 = 0;
            int value2 = 0;
            if (reverse1 != null) {
                value1 = reverse1.val;
                reverse1 = reverse1.next;
            }
            if (reverse2 != null) {
                value2 = reverse2.val;
                reverse2 = reverse2.next;
            }
            int resultValue = value1 + value2 + remainder;
            if (resultValue > 9) {
                remainder = 1;
                tempNode.next = new ListNode(resultValue % 10);
                if (reverse1 == null && reverse2 == null) {
                    tempNode.next.next = new ListNode(1);
                }
            } else {
                remainder = 0;
                tempNode.next = new ListNode(resultValue);
            }
            tempNode = tempNode.next;
        }
        return reverseList(dummyNode.next);
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
