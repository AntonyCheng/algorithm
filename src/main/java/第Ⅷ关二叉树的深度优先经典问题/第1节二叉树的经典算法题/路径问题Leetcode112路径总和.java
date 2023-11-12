package 第Ⅷ关二叉树的深度优先经典问题.第1节二叉树的经典算法题;

/**
 * <a href="https://leetcode.cn/problems/path-sum/">路径总和</a>
 *
 * @author AntonyCheng
 * @since 2023/9/19 16:49:34
 */

public class 路径问题Leetcode112路径总和 {
    /**
     * 递归法
     * 本题询问是否存在从当前节点 root 到叶子节点的路径，满足其路径和为 sum，假定从根节点到当前节点的值之和为 val，
     * 我们可以将这个大问题转化为一个小问题：是否存在从当前节点的子节点到叶子的路径，满足其路径和为 sum - val。
     * 不难发现这满足递归的性质，若当前节点就是叶子节点，那么我们直接判断 sum 是否等于 val 即可（因为路径和已经确定，就是当前节点的值，我们只需要判断该路径和是否满足条件）。
     * 若当前节点不是叶子节点，我们只需要递归地询问它的子节点是否能满足条件即可。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        boolean left = hasPathSum(root.left, targetSum - root.val);
        boolean right = hasPathSum(root.right, targetSum - root.val);
        return left || right;
    }
}
