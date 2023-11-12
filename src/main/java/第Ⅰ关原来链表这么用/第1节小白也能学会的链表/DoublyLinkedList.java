package 第Ⅰ关原来链表这么用.第1节小白也能学会的链表;

import java.util.ArrayList;
import java.util.List;

/**
 * 双向链表类
 * 双向链表和单链表的主要差别就是双向链表节点类比单链表节点类多一个前驱节点指针域
 *
 * @author AntonyCheng
 * @since 2023/8/27 23:03:36
 */

public class DoublyLinkedList {
    /**
     * 节点类
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode prev;

        ListNode(int firstVal) {
            this.val = firstVal;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * 初始化链表
     *
     * @param firstVal
     * @return
     */
    public static ListNode initLinkedList(int firstVal) {
        return new ListNode(firstVal);
    }

    /**
     * 从头部遍历获取链表的长度
     *
     * @param headNode
     * @return
     */
    public static int getLinkedListLengthByHead(ListNode headNode) {
        ListNode temp = headNode;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 从尾部遍历获取链表的长度
     *
     * @param tailNode
     * @return
     */
    public static int getLinkedListLengthByTail(ListNode tailNode) {
        ListNode temp = tailNode;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.prev;
        }
        return length;
    }


    /**
     * 从头部遍历获取所有节点的值
     *
     * @param headNode
     * @return
     */
    public static List<Integer> getAllNodesByHead(ListNode headNode) {
        ListNode temp = headNode;
        int linkedListLengthByHead = getLinkedListLengthByHead(headNode);
        ArrayList<Integer> nodes = new ArrayList<>();
        for (int i = 1; i < linkedListLengthByHead + 1; i++) {
            nodes.add(temp.val);
            temp = temp.next;
        }
        return nodes;
    }

    /**
     * 从尾部遍历获取所有节点的值
     *
     * @param tailNode
     * @return
     */
    public static List<Integer> getAllNodesByTail(ListNode tailNode) {
        ListNode temp = tailNode;
        int linkedListLengthByTail = getLinkedListLengthByTail(tailNode);
        ArrayList<Integer> nodes = new ArrayList<>();
        for (int i = 1; i < linkedListLengthByTail + 1; i++) {
            nodes.add(temp.val);
            temp = temp.prev;
        }
        return nodes;
    }

    /**
     * 从头部遍历插入数据
     *
     * @param headNode
     * @param insertNode
     * @param position
     * @return
     */
    public static ListNode addNodeByHead(ListNode headNode, ListNode insertNode, int position) {
        int linkedListLengthByHead = getLinkedListLengthByHead(headNode);
        if (linkedListLengthByHead == 0) {
            return insertNode;
        }

        if (position <= 1) {
            headNode.prev = insertNode;
            insertNode.next = headNode;
            return insertNode;
        }

        if (position > linkedListLengthByHead) {
            ListNode temp = headNode;
            for (int i = 1; i < linkedListLengthByHead; i++) {
                temp = temp.next;
            }
            insertNode.prev = temp;
            temp.next = insertNode;
            return headNode;
        }

        ListNode temp = headNode;
        for (int i = 1; i < position - 1; i++) {
            temp = temp.next;
        }
        insertNode.prev = temp;
        insertNode.next = temp.next;
        temp.next = insertNode;
        return headNode;
    }

    /**
     * 从尾部遍历插入数据
     *
     * @param tailNode
     * @param insertNode
     * @param position
     * @return
     */
    public static ListNode addNodeByTail(ListNode tailNode, ListNode insertNode, int position) {
        int linkedListLengthByTail = getLinkedListLengthByTail(tailNode);
        if (linkedListLengthByTail == 0) {
            return insertNode;
        }

        if (position <= 1) {
            insertNode.prev = tailNode;
            tailNode.next = insertNode;
            return insertNode;
        }

        if (position > linkedListLengthByTail) {
            ListNode temp = tailNode;
            for (int i = 1; i < linkedListLengthByTail; i++) {
                temp = temp.prev;
            }
            temp.prev = insertNode;
            insertNode.next = temp;
            return tailNode;
        }

        ListNode temp = tailNode;
        for (int i = 1; i < position - 1; i++) {
            temp = temp.prev;
        }
        insertNode.next = temp;
        insertNode.prev = temp.prev;
        temp.prev = insertNode;
        return tailNode;
    }

    /**
     * 从头部遍历删除数据
     *
     * @param headNode
     * @param position
     * @return
     */
    public static ListNode deleteNodeByHead(ListNode headNode, int position) {
        int linkedListLengthByHead = getLinkedListLengthByHead(headNode);
        if (linkedListLengthByHead <= 1) {
            return null;
        }

        if (position <= 1) {
            return headNode.next;
        }

        if (position > linkedListLengthByHead) {
            ListNode temp = headNode;
            for (int i = 1; i < linkedListLengthByHead - 1; i++) {
                temp = temp.next;
            }
            temp.next = null;
            return headNode;
        }

        ListNode temp = headNode;
        for (int i = 1; i < position - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return headNode;
    }

    /**
     * 从尾部遍历删除数据
     *
     * @param tailNode
     * @param position
     * @return
     */
    public static ListNode deleteNodeByTail(ListNode tailNode, int position) {
        int linkedListLengthByTail = getLinkedListLengthByTail(tailNode);
        if (linkedListLengthByTail <= 1) {
            return null;
        }

        if (position <= 1) {
            return tailNode.prev;
        }

        if (position > linkedListLengthByTail) {
            ListNode temp = tailNode;
            for (int i = 1; i < linkedListLengthByTail - 1; i++) {
                temp = temp.prev;
            }
            temp.prev = null;
            return tailNode;
        }

        ListNode temp = tailNode;
        for (int i = 1; i < position - 1; i++) {
            temp = temp.prev;
        }
        temp.prev = temp.prev.prev;
        return tailNode;
    }

    /**
     * 从头部遍历更新节点
     *
     * @param headNode
     * @param updateNode
     * @param position
     * @return
     */
    public static ListNode updateNodeByHead(ListNode headNode, ListNode updateNode, int position) {
        int linkedListLengthByHead = getLinkedListLengthByHead(headNode);
        if (linkedListLengthByHead == 0) {
            return null;
        }
        if (linkedListLengthByHead == 1) {
            return updateNode;
        }

        if (position <= 1) {
            updateNode.next = headNode.next;
            return updateNode;
        }

        if (position > linkedListLengthByHead) {
            ListNode temp = headNode;
            for (int i = 1; i < linkedListLengthByHead - 1; i++) {
                temp = temp.next;
            }
            temp.next = updateNode;
            updateNode.prev = temp;
            return headNode;
        }

        ListNode temp = headNode;
        for (int i = 1; i < position - 1; i++) {
            temp = temp.next;
        }
        updateNode.prev = temp;
        updateNode.next = temp.next.next;
        temp.next = updateNode;
        return headNode;
    }

    /**
     * 从尾部遍历更新节点
     *
     * @param tailNode
     * @param updateNode
     * @param position
     * @return
     */
    public static ListNode updateNodeByTail(ListNode tailNode, ListNode updateNode, int position) {
        int linkedListLengthByTail = getLinkedListLengthByTail(tailNode);
        if (linkedListLengthByTail == 0) {
            return null;
        }
        if (linkedListLengthByTail == 1) {
            return updateNode;
        }

        if (position <= 1) {
            updateNode.prev = tailNode.prev;
            return updateNode;
        }

        if (position > linkedListLengthByTail) {
            ListNode temp = tailNode;
            for (int i = 1; i < linkedListLengthByTail - 1; i++) {
                temp = temp.prev;
            }
            temp.prev = updateNode;
            updateNode.next = temp;
            return tailNode;
        }

        ListNode temp = tailNode;
        for (int i = 1; i < position - 1; i++) {
            temp = temp.prev;
        }
        updateNode.next = temp;
        updateNode.prev = temp.prev.prev;
        temp.prev = updateNode;
        return tailNode;
    }

    public static void main(String[] args) {
        ListNode linkedListNodeResult = null;
        for (int i = 1; i < 10; i++) {
            ListNode insertNode = new ListNode(i * 10);
            linkedListNodeResult = addNodeByTail(linkedListNodeResult, insertNode, 9);
            System.out.println(getAllNodesByTail(linkedListNodeResult));
        }
        ListNode updateNode = new ListNode(1000);
        ListNode listNode = updateNodeByTail(linkedListNodeResult, updateNode, 900);
        System.out.println(getAllNodesByTail(listNode));
    }
}
