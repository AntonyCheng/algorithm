package 第Ⅶ关算法真正开始_递归与二叉树遍历.第3节迭代实现二叉树的深度优先遍历;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 迭代实现深度优先遍历类
 * 递归实现深度优先遍历好理解，实现也简单，所以为了提升难度，需要设计出迭代方法实现深度优先遍历，
 * 理论上递归能做的事情，迭代也能做，反之亦然，
 * 迭代和递归的区别就是递归是方法级别的循环调用，迭代是代码块级别的循环调用；
 *
 * @author AntonyCheng
 * @since 2023/9/19 10:44:35
 */

public class DepthFirstTraversalByIterate {
    /**
     * 树节点类
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * 前序遍历
     * 前序遍历是中左右，如果还有左子树就一直向下找。完了之后再返回从最底层逐步向上向右找。
     * 而“从底层逐步向上找“这个操作就需要搭配栈，即动作如下图所示（数字表示步骤）：
     *              1
     *            /   \
     *          2      7
     *         / \    / \
     *        3  4   8   9
     *          / \     / \
     *         5  6    10 11
     *                    /\
     *                  12 13
     *
     * @param root
     * @return
     */
    public List<Integer> preOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> midOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.val);
            node = node.right;
        }
        return res;
    }

    /**
     * 后序遍历：反转法
     *      3
     *     / \
     *    9   4
     *       / \
     *      5   7
     * 后序为：     9 5 7 4 3
     * 反转后序得：  3 4 7 5 9
     * 将反转后的序列按照原树顺序如下：
     *      1
     *     / \
     *    5   2
     *       / \
     *      4   3
     * 这个顺序和前序遍历的顺序相似，唯一的不同点在于左右对调了，
     * 那么我们可以先仿前序遍历一次，然后将结果进行反转操作；
     *
     * @param root
     * @return
     */
    public List<Integer> postOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node!=null){
                res.add(node.val);
                stack.push(node);
                node = node.right;
            }
            node = stack.pop();
            node = node.left;
        }
        Collections.reverse(res);
        return res;
    }
}
