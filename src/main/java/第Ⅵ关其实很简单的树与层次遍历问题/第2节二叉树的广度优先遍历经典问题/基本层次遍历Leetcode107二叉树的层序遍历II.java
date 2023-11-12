package 第Ⅵ关其实很简单的树与层次遍历问题.第2节二叉树的广度优先遍历经典问题;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/">二叉树的层序遍历II</a>
 *
 * @author AntonyCheng
 * @since 2023/9/18 10:32:10
 */

public class 基本层次遍历Leetcode107二叉树的层序遍历II {
    /**
     * 堆栈辅助法
     * 先正常层序遍历，将每一层包装成一个集合，包装完成之后入栈；
     * 最后出栈到一个结果集合中即可
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottomMethod1(TwoTreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Stack<List<Integer>> stack = new Stack<>();
        Queue<TwoTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
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
            stack.push(temp);
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

    /**
     * 直接头插法
     * 层序遍历依旧照常遍历，得到每一层的元素集合，当插入结果集合时进行头插即可；
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottomMethod2(TwoTreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> res = new LinkedList<>();
        Queue<TwoTreeNode> queue = new LinkedList<TwoTreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TwoTreeNode t = queue.poll();
                temp.add(t.val);
                if (t.right != null) {
                    queue.add(t.right);
                }
                if (t.left != null) {
                    queue.add(t.left);
                }
            }
            res.add(0, temp);
        }
        return res;
    }
}
