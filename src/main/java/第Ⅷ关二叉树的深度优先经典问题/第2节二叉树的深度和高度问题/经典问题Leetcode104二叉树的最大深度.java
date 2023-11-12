package 第Ⅷ关二叉树的深度优先经典问题.第2节二叉树的深度和高度问题;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/">二叉树的最大深度</a>
 *
 * @author AntonyCheng
 * @since 2023/9/29 13:29:57
 */

public class 经典问题Leetcode104二叉树的最大深度 {
    /**
     * 广度优先遍历法
     * 使用广度优先遍历去对每一层进行累加
     *
     * @param root
     * @return
     */
    public int maxDepthMethod1(TreeNode root) {
        int res = 0;
        if (root==null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left!= null) {
                    queue.offer(node.left);
                }
                if (node.right!= null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }

    /**
     * 递归法
     * 即遍历左右子树，有子树就返回res+1，没子树就返回0，左右子树需要取最大值；
     *
     * @param root
     * @return
     */
    public int maxDepthMethod2(TreeNode root) {
        if (root==null) {
            return 0;
        }
        int leftDepth = maxDepthMethod2(root.left);
        int rightDepth = maxDepthMethod2(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
