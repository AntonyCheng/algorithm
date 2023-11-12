package 第Ⅷ关二叉树的深度优先经典问题.第1节二叉树的经典算法题;

/**
 * <a href="https://leetcode.cn/problems/invert-binary-tree/">翻转二叉树</a>
 *
 * @author AntonyCheng
 * @since 2023/9/29 13:20:56
 */

public class 反转问题Leetcode226翻转二叉树 {
    /**
     * 递归法
     * 递归每个节点的左右节点，然后对其进行位置的调换即可，终止条件就是当所遍历到的根节点为null；
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
