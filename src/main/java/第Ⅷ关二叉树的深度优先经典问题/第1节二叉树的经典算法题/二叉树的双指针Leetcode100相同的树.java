package 第Ⅷ关二叉树的深度优先经典问题.第1节二叉树的经典算法题;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/same-tree/">相同的树</a>
 *
 * @author AntonyCheng
 * @since 2023/9/19 13:00:01
 */

public class 二叉树的双指针Leetcode100相同的树 {
    /**
     * 双指针逐位比较（广度优先遍历）
     * 使用两个指针（这里可以直接使用参数p、q）分别指向根节点，并且开始遍历，重复相同的操作，查看位置是否相同；
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTreeMethod1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        Queue<TreeNode> pQueue = new LinkedList<>();
        Queue<TreeNode> qQueue = new LinkedList<>();
        pQueue.offer(p);
        qQueue.offer(q);
        while (!pQueue.isEmpty() && !qQueue.isEmpty()) {
            TreeNode pNode = pQueue.poll();
            TreeNode qNode = qQueue.poll();
            if (pNode.val != qNode.val) {
                return false;
            }
            TreeNode pLeft = pNode.left;
            TreeNode qLeft = qNode.left;
            TreeNode pRight = pNode.right;
            TreeNode qRight = qNode.right;
            if (pLeft == null && qLeft != null) {
                return false;
            }
            if (pRight == null && qRight != null) {
                return false;
            }
            if (pLeft != null && qLeft == null) {
                return false;
            }
            if (pRight != null && qRight == null) {
                return false;
            }
            if (pLeft != null) {
                pQueue.offer(pLeft);
            }
            if (pRight != null) {
                pQueue.offer(pRight);
            }
            if (qLeft != null) {
                qQueue.offer(qLeft);
            }
            if (qRight != null) {
                qQueue.offer(qRight);
            }
        }
        return pQueue.isEmpty() && qQueue.isEmpty();
    }

    /**
     * 双指针逐位比较（深度优先遍历——递归）
     * 使用两个指针（这里可以直接使用参数p、q）分别指向根节点，并且开始遍历，重复相同的操作，查看位置是否相同；
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTreeMethod2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTreeMethod2(p.left, q.left) && isSameTreeMethod2(p.right, q.right);
    }

    /**
     * 双指针逐位比较（深度优先遍历——迭代）
     * 使用两个指针（这里可以直接使用参数p、q）分别指向根节点，并且开始遍历，重复相同的操作，查看位置是否相同；
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTreeMethod3(TreeNode p, TreeNode q) {
        // 使用栈来模拟DFS
        Stack<TreeNode> stack = new Stack<>();
        stack.push(p);
        stack.push(q);

        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();

            // 如果两个节点都为空，则继续下一个迭代
            if (node1 == null && node2 == null) {
                continue;
            }

            // 如果只有一个节点为空，或者节点的值不相等，则树不相同
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }

            // 将左子树的节点入栈
            stack.push(node1.left);
            stack.push(node2.left);

            // 将右子树的节点入栈
            stack.push(node1.right);
            stack.push(node2.right);
        }

        // 如果循环结束都没有返回false，则两棵树相同
        return true;
    }
}
