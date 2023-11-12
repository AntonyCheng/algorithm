package 第Ⅰ关原来链表这么用.第2节链表高频面试算法题;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/description/">两个链表的第一个公共节点</a>
 *
 * @author AntonyCheng
 * @since 2023/8/29 16:45:00
 */

public class 基础Offer52两个链表的第一个公共节点 {
    /**
     * 暴力枚举法
     * 就是遍历A链表的所有节点，再用B链表的所有节点去比较A链表的每一个节点；
     *
     * @param headA
     * @param headB
     * @return
     */
    ListNode getIntersectionNodeMethod1(ListNode headA, ListNode headB) {
        ListNode aTemp = headA;
        while (aTemp != null) {
            ListNode bTemp = headB;
            while (bTemp != null) {
                if (aTemp.equals(bTemp)) {
                    return aTemp;
                }
                bTemp = bTemp.next;
            }
            aTemp = aTemp.next;
        }
        return null;
    }

    /**
     * Hash和集合法
     * 先将A链表里所有的节点使用Hash表或者集合收集起来，在判断B链中是否有节点存在于Hash表或者集合中；
     *
     * @param headA
     * @param headB
     * @return
     */
    ListNode getIntersectionNodeMethod2(ListNode headA, ListNode headB) {
        ListNode aTemp = headA;
        Set<ListNode> nodes = new HashSet<>();
        while (aTemp != null) {
            nodes.add(aTemp);
            aTemp = aTemp.next;
        }
        ListNode bTemp = headB;
        while (bTemp != null) {
            if (nodes.contains(bTemp)) {
                return bTemp;
            }
            bTemp = bTemp.next;
        }
        return null;
//        集合的解法看似和Hash的解法差不多，但是相比于使用Hash的解法，集合的解法速度会慢很多很多
//        ListNode aTemp = headA;
//        List<ListNode> nodes = new ArrayList<>();
//        while (aTemp != null) {
//            nodes.add(aTemp);
//            aTemp = aTemp.next;
//        }
//        ListNode bTemp = headB;
//        while (bTemp != null) {
//            if (nodes.contains(bTemp)) {
//                return bTemp;
//            }
//            bTemp = bTemp.next;
//        }
//        return null;
    }

    /**
     * 堆栈法
     * 根据单链表的特性可知，若A、B链表拥有公共节点，那么A、B链表的尾部肯定相同，
     * 那么使用堆栈将A、B链表依次压栈，再同时出栈，最后一个相同的节点即为公共节点
     *
     * @param headA
     * @param headB
     * @return
     */
    ListNode getIntersectionNodeMethod3(ListNode headA, ListNode headB) {
        Stack<ListNode> aStack = new Stack<ListNode>();
        Stack<ListNode> bStack = new Stack<ListNode>();
        while (headA != null) {
            aStack.push(headA);
            headA = headA.next;
        }
        while (headB != null) {
            bStack.push(headB);
            headB = headB.next;
        }
        ListNode result = null;
        boolean flag = true;
        while (flag) {
            if (aStack.isEmpty() || bStack.isEmpty()) {
                break;
            }
            ListNode aNode = aStack.pop();
            ListNode bNode = bStack.pop();
            if (aNode.equals(bNode)) {
                result = aNode;
                continue;
            }
            flag = false;
        }
        return result;
    }

    /**
     * 拼接法（LeetCode由于结构问题无法通过，但是实际可行）
     * 结合数形结合的思想，发现A、B链表的长度不一致是一个隐藏的解决难点，但是当AB拼接和BA拼接相比较时，就能发现规律：
     * A 1->2->3->1->2
     * B 9->3->1->2
     * AB 1->2->3->1->2->9->"3->1->2"
     * BA 9->3->1->2->1->2->"3->1->2"
     * 即，如果交叉拼接后会发现两条新链表长度相同，链尾部能轻松找到公共点，以这样的思想写出以下代码
     *
     * @param headA
     * @param headB
     * @return
     */
    ListNode getIntersectionNodeMethod4(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode aCountTemp = headA;
        int aCount = 0;
        while (aCountTemp != null) {
            aCount++;
            aCountTemp = aCountTemp.next;
        }
        ListNode bCountTemp = headB;
        int bCount = 0;
        while (bCountTemp != null) {
            bCount++;
            bCountTemp = bCountTemp.next;
        }
        int allCount = aCount + bCount;

        List<ListNode> aList = new ArrayList<>();
        List<ListNode> bList = new ArrayList<>();
        ListNode aTemp = headA;
        ListNode bTemp = headB;
        for (int i = 1; i < aCount; i++) {
            aTemp = aTemp.next;
        }
        aTemp.next = headB;
        ListNode aListTemp = headA;
        for (int i = 0; i < allCount; i++) {
            aList.add(aListTemp);
            aListTemp = aListTemp.next;
        }

        for (int i = 1; i < bCount; i++) {
            bTemp = bTemp.next;
        }
        bTemp.next = headA;
        ListNode bListTemp = headB;
        for (int i = 0; i < allCount; i++) {
            bList.add(bListTemp);
            bListTemp = bListTemp.next;
        }

        for (int i = 0; i < allCount; i++) {
            if (aList.get(i) == bList.get(i)) {
                return aList.get(i);
            }
        }
        return null;
    }

    /**
     * 双指针法（拼接法优化版本）
     * 拼接法的代码只是为了证明拼接思想可行，但是实现过于复杂，这种代码写法注定被弃用，我们按照原有思想，换个方法重构一下代码：
     * 拼接法的代码最严重的问题就是需要不断创建新的对象以及辅助容器来进行逻辑的穿插，这个恰巧踩了Java按引用传值的弊端，
     * 我们如果要合理利用这个按引用传值这个语言特性，就会极大简化代码工程，方法描述如下：
     * 拼接思想简而言之就是想办法让A、B成为相同长度的遍历对象，在里面找到相同的元素，
     * 对此我们可以设计两个作为指针的对象，分别在原有的A、B链表上进行平移判断，若一条链表遍历完成，即可转移到另外一条链表上继续遍历，
     * 这样就可以不用创建新对象来避免按引用传值的弊端，反而利用了这个语言特性在原参数上进行遍历对象的更换；
     *
     * @param headA
     * @param headB
     * @return
     */
    ListNode getIntersectionNodeMethod5(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pointerA = headA;
        ListNode pointerB = headB;
        while (pointerA != pointerB) {
            pointerA = pointerA.next;
            pointerB = pointerB.next;
            //下面三行是关键，这里做了一个判断是否指针遍历到了AB(BA)最后的位置，
            //即当指针同时为null时，就能够确定已经遍历到了最后的位置，即可判断没有公共节点
            if (pointerA == null && pointerB == null) {
                return null;
            }
            //遍历到A链表最后时更换为B链表
            if (pointerA == null) {
                pointerA = headB;
            }
            //遍历到B链表最后时更换为A链表
            if (pointerB == null) {
                pointerB = headA;
            }
        }
        return pointerA;
    }
}
