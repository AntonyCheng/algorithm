package 第Ⅷ关二叉树的深度优先经典问题.第1节二叉树的经典算法题;

/**
 * <a href="https://leetcode.cn/problems/merge-two-binary-trees/">合并二叉树</a>
 *
 * @author AntonyCheng
 * @since 2023/9/19 15:39:43
 */

public class 二叉树的双指针Leetcode617合并二叉树 {
    /**
     * 递归法
     *
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = mergeTrees(root1.left, root2.left);
        merged.right = mergeTrees(root1.right, root2.right);
        return merged;
    }
}
