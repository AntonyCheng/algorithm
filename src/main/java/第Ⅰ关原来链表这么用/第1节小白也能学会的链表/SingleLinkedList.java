package 第Ⅰ关原来链表这么用.第1节小白也能学会的链表;

import java.util.ArrayList;
import java.util.List;

/**
 * 单链表类
 * 单链表的最大特点就是每个节点有且只有一个值域和一个后继节点指针域，数据基础结构见下方节点类；
 * 得益于JVM的堆栈存储管理（栈主要保存引用，堆主要保存对象）和GC机制，这里的数据结构和算法就不需要设计得像C语言那么细致；
 * 对于单链表的修改操作需要从头开始遍历，所以链表头节点就尤为重要，多数方法设计上链表头节点尽量做到已知状态（将头节点作为参数传递进方法中）；
 */
public class SingleLinkedList {
    /**
     * 节点类
     * 注意：按照面向对象的思想，该类本应该被设计成含有Getter和Setter的标准Bean，但是为了简化代码，大多数情况下会设计成以下缩减版的一个“算法”类；
     */
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int firstVal) {
            this.val = firstVal;
            this.next = null;
        }
    }

    /**
     * 初始化链表
     * 即：返回一个节点对象；
     *
     * @param firstVal 首元素
     * @return 返回初始化后的链表
     */
    public static ListNode initLinkedList(int firstVal) {
        return new ListNode(firstVal);
    }

    /**
     * 获取链表长度
     * 即：统计后继节点指针域不为null的节点个数；
     *
     * @param head 链表头节点
     * @return 返回链表长度
     */
    public static int getLinkedListLength(ListNode head) {
        ListNode temp = head;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 获取所有节点的值
     * 即：将该链表所有节点的值（node.val）使用列表收集起来进行返回；
     *
     * @param headNode 链表头节点
     * @return 所有节点的值列表
     */
    public static List<Integer> getAllNodes(ListNode headNode) {
        ListNode tempNode = headNode;
        int linkedListLength = getLinkedListLength(headNode);
        ArrayList<Integer> nodes = new ArrayList<>();
        for (int i = 1; i < linkedListLength + 1; i++) {
            nodes.add(tempNode.val);
            tempNode = tempNode.next;
        }
        return nodes;
    }

    /**
     * 增加节点
     * 即：向链表中插入节点（insertNode），对于单链表增删改的操作，都必须单独注意的三个点就是链头操作、链尾操作以及链中操作；
     * 增加节点的技巧：
     * 1、对于链中的操作：对于用户要操作的的节点位置（position），遍历最后到达的节点位置应该是用户操作节点位置的前一个节点位置（beforePosition），
     * 这样就能先让插入节点的后继节点指针域（insertNode.next）指向position节点，然后让beforePosition节点的后继节点指针域指向insertNode；
     * 2、对于链头的操作：插入节点的后继节点指针域指向原链表的头节点（insertNode.next = headNode）；
     * 3、对于链尾的操作：原链表的尾节点的后继节点指针域指向插入节点（tailNode.next = insertNode）；
     *
     * @param headNode   头节点（如果不存在可以为空，默认插入节点成为头节点）
     * @param insertNode 插入的节点
     * @param position   插入的位置
     * @return 返回插入后的链表头节点
     */
    public static ListNode addNode(ListNode headNode, ListNode insertNode, int position) {
        // 先判断原链表长度是否合理
        int linkedListLength = getLinkedListLength(headNode);
        if (linkedListLength == 0) {
            return insertNode;
        }

        // 考虑链头
        if (position <= 1) {
            insertNode.next = headNode;
            return insertNode;
        }

        // 考虑链尾
        if (position > linkedListLength) {
            ListNode temp = headNode;
            for (int i = 1; i < linkedListLength; i++) {
                temp = temp.next;
            }
            temp.next = insertNode;
            return headNode;
        }

        // 考虑链中
        ListNode temp = headNode;
        for (int i = 1; i < position - 1; i++) {
            temp = temp.next;
        }
        insertNode.next = temp.next;
        temp.next = insertNode;
        return headNode;
    }

    /**
     * 删除节点
     * 即：从链表中删除节点
     * 删除节点的技巧：
     * 1、对于链中的操作：对于用户要操作的的节点位置（position），遍历最后到达的节点位置应该是用户操作节点位置的前一个节点位置（beforePosition），
     * 这样就能先让beforePosition节点的后继节点指针域（beforePositionNode.next）指向用户操作节点位置的后一个节点位置（afterPosition）节点即可；
     * 2、对于链头的操作：原链表的头节点的后继节点指针域的节点，返回形成一条新的链表；
     * 3、对于链尾的操作：原链表的尾节点的beforePosition节点的后继节点指针域指向null；
     *
     * @param headNode 链表头节点
     * @param position 被删除的位置
     * @return 返回删除后的链表头节点
     */
    public static ListNode deleteNode(ListNode headNode, int position) {
        // 先判断原链表长度是否合理
        int linkedListLength = getLinkedListLength(headNode);
        if (linkedListLength <= 1) {
            return null;
        }

        // 考虑链首
        if (position <= 1) {
            return headNode.next;
        }

        // 考虑链尾
        if (position > linkedListLength) {
            ListNode temp = headNode;
            for (int i = 1; i < linkedListLength - 1; i++) {
                temp = temp.next;
            }
            temp.next = null;
            return headNode;
        }

        // 考虑链中
        ListNode temp = headNode;
        for (int i = 1; i < position - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return headNode;
    }

    /**
     * 更新节点
     * 即：更改链表中的节点
     * 更新节点的技巧：
     * 1、对于链中的操作：对于用户要操作的的节点位置（position），遍历最后到达的节点位置应该是用户操作节点位置的前一个节点位置（beforePosition），
     * 这样就能先让待更新节点（updateNode）的后继节点指针域（updateNode.next）指向beforePosition节点位置的后一个节点的后一个节点（beforePositionNode.next.next）,
     * 然后让beforePosition节点的后继节点指针域（beforePositionNode.next）指向updateNode；
     * 2、对于链头的操作：updateNode的后继节点指针域指向原链表头节点的下一个节点（updateNode.next = headNode.next）；
     * 3、对于链尾的操作：原链表尾节点的前一个节点的后继节点指针域指向待更新节点（beforeTailNode.next = updateNode）；
     *
     * @param headNode   链表头节点
     * @param updateNode 被更新的节点
     * @param position   被更新的位置
     * @return 返回更新后的链表头节点
     */
    public static ListNode updateNode(ListNode headNode, ListNode updateNode, int position) {
        int linkedListLength = getLinkedListLength(headNode);
        if (linkedListLength <= 0) {
            return null;
        }
        if (linkedListLength == 1) {
            return updateNode;
        }

        if (position <= 1) {
            updateNode.next = headNode.next;
            return updateNode;
        }

        if (position > linkedListLength) {
            ListNode temp = headNode;
            for (int i = 1; i < linkedListLength-1; i++) {
                temp = temp.next;
            }
            temp.next = updateNode;
            return headNode;
        }

        ListNode temp = headNode;
        for (int i = 1; i < position - 1; i++) {
            temp = temp.next;
        }
        updateNode.next = temp.next.next;
        temp.next = updateNode;
        return headNode;
    }

    public static void main(String[] args) {
        ListNode listNodeResult = null;
        for (int i = 1; i < 10; i++) {
            ListNode insertNode = new ListNode(i * 10);
            listNodeResult = addNode(listNodeResult, insertNode, 8000);
            System.out.println(getAllNodes(listNodeResult));
        }
        ListNode updateNode = new ListNode(1000);
        listNodeResult = updateNode(listNodeResult, updateNode, 8);
        System.out.println(getAllNodes(listNodeResult));
    }
}
