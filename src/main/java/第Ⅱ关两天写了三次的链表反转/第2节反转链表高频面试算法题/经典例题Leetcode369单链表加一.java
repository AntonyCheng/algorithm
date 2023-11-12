package 第Ⅱ关两天写了三次的链表反转.第2节反转链表高频面试算法题;

/**
 * 单链表加一（Leetcode Plus题目，需要VIP，本地测试成功即可）
 *
 * @author AntonyCheng
 * @since 2023/9/2 23:18:07
 */

public class 经典例题Leetcode369单链表加一 {
    /**
     * 字符串法
     * 将单链表所表示的非负整数使用字符串拼接，转换成整数然后减一，
     * 最后进行上述操作的逆过程即可；
     *
     * @param head
     * @return
     */
    public ListNode plusOneMethod1(ListNode head) {
        ListNode temp = head;
        StringBuilder value = new StringBuilder();
        while (temp != null) {
            value.append(temp.val);
            temp = temp.next;
        }
        Integer num = Integer.parseInt(value.toString());
        num++;
        char[] chars = num.toString().toCharArray();

        ListNode dummyNode = new ListNode(-1);
        ListNode dummyTemp = dummyNode;
        for (int i = 0; i < chars.length; i++) {
            dummyTemp.next = new ListNode(chars[i] - '0');
            dummyTemp = dummyTemp.next;
        }
        return dummyNode.next;
    }

    /**
     * 反转链表法
     * 我们来观察一个加法：
     *    2 6
     * +  9 7
     * -------
     *  1 2 3
     * 加法运算都是从个位开始进位的；
     * 如果是链表加法就会出现如下难点：
     * 1、单链表没有前继，链表都是从高位到低位；
     * 2、如果相加的两数位数不同，就还要先判断位数相同位；
     * 为了解决以上难题，反转链表就是一个不错的思考方向。
     *
     * @param head
     * @return
     */
    public ListNode plusOneMethod2(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        ListNode node = prev;
        boolean flag = true;
        while (flag) {
            if (node.val == 9) {
                node.val = 0;
                if (node.next == null) {
                    node.next = new ListNode(0);
                }
                node = node.next;
            } else {
                node.val = node.val + 1;
                flag = false;
            }
        }
        ListNode prevTemp = null;
        while (prev != null) {
            ListNode next = prev.next;
            prev.next = prevTemp;
            prevTemp = prev;
            prev = next;
        }
        return prevTemp;
    }
}
