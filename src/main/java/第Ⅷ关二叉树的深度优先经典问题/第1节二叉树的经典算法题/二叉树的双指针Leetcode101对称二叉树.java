package 第Ⅷ关二叉树的深度优先经典问题.第1节二叉树的经典算法题;

/**
 * <a href="https://leetcode.cn/problems/symmetric-tree/">对称二叉树</a>
 *
 * @author AntonyCheng
 * @since 2023/9/19 15:29:52
 */

public class 二叉树的双指针Leetcode101对称二叉树 {
    /**
     * 反转法
     * "对称二叉树"其实就是"相同的树"的变型，我们将根节点的左右子树单独看成两颗树，这两颗树的区别就是镜像对称，
     * 那么我们只需要左树的右边节点和右树的左边节点相同即可；
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        return isSame(left, right);
    }

    /**
     * 判断左右子树是否镜像对称
     *
     * @param left
     * @param right
     * @return
     */
    public boolean isSame(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSame(left.left, right.right) && isSame(left.right, right.left);
    }
}
