package 第Ⅳ关站不住的栈.第1节理解栈手写栈;

import java.util.Stack;

/**
 * 栈的基本特征：先进后出——FILO
 * 栈是很多表达式、符号等运算的基础，也是递归的底层实现。理论上递归能做的题目栈都可以，只是有些问题用栈会非常复杂。
 * 栈底层实现仍然是链表或者顺序表，栈与线性表的最大区别是数据的存取的操作被限制了，其插入和删除操作只允许在线性表的一端进行。
 * 一般而言，把允许操作的一端称为栈顶(Top)，不可操作的一端称为栈底(Bottom)，
 * 同时把插入元素的操作称为入栈(Push),删除元素的操作称为出栈(Pop)。若栈中没有任何元素，则称为空栈；
 * top有的地方指向栈顶元素，有的地方指向栈顶再往上的一个空单位，这个根据题目要求设计就好，
 * 如果是面试的时候就直接问面试官，top指向到哪里，本文采用指向栈顶空位置。
 * 栈的常用操作主要有：
 * ● push(E):增加一个元素E
 * ● pop():弹出元素E
 * ● peek():显示栈顶元素，但是不出栈
 * ● empty():判断栈是否为空
 * 这里直接用Java中util包中的Stack类做演示
 *
 * @author AntonyCheng
 * @since 2023/9/11 08:25:51
 */

public class BaseStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        // 增
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        // 查
        while (!stack.isEmpty()) {
            // 查看栈顶
            System.out.println(stack.peek());
            // 删除栈顶并返回栈顶值
            System.out.println(stack.pop());
        }
    }
}
