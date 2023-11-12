package 第Ⅵ关其实很简单的树与层次遍历问题.第2节二叉树的广度优先遍历经典问题;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/">二叉树的锯齿形层序遍历</a>
 *
 * @author AntonyCheng
 * @since 2023/9/18 12:00:29
 */

public class 基本层次遍历Leetcode103二叉树的锯齿形层序遍历 {
    /**
     * 逐层遍历标记法
     * 其实这个题就是将层划为了奇偶层，那么我们可以根据结果集合中元素个数的奇偶情况对其进行判断；
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrderMethod1(TwoTreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        Queue<TwoTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TwoTreeNode t = queue.poll();
                temp.add(t.val);
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
            if (res.size() % 2 != 0) {
                for (int i = 0; i < temp.size() / 2; i++) {
                    int tempNum = temp.get(i);
                    temp.set(i, temp.get(temp.size() - 1 - i));
                    temp.set(temp.size() - 1 - i, tempNum);
                }
            }
            res.add(temp);
        }
        return res;
    }

    /**
     * 双端队列辅助法
     * 层序遍历中的核心要领就是队列的使用，平时使用的单向队列都是A头进B头出，
     * 如果使用双端队列，就既能实现A头进B头出，也能实现A头进A头出，
     * 这个就有点像堆栈了，但是这个题不能使用堆栈，因为堆栈不能两者都实现；
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrderMethod2(TwoTreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<List<Integer>> res = new LinkedList<>();
        Queue<TwoTreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isOrder = false;
        while (!queue.isEmpty()) {
            Deque<Integer> deque = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TwoTreeNode t = queue.poll();
                if (isOrder) {
                    deque.addFirst(t.val);
                } else {
                    deque.addLast(t.val);
                }
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
            res.add(new LinkedList<>(deque));
            isOrder = !isOrder;
        }
        return res;
    }
}
