package 第Ⅷ关二叉树的深度优先经典问题.第1节二叉树的经典算法题;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-paths/">二叉树的所有路径</a>
 *
 * @author AntonyCheng
 * @since 2023/9/19 16:23:56
 */

public class 路径问题Leetcode257二叉树的所有路径 {
    /**
     * 递归法
     * 最直观的方法是使用深度优先搜索。在深度优先搜索遍历二叉树时，我们需要考虑当前的节点以及它的孩子节点。
     * 如果当前节点不是叶子节点，则在当前的路径末尾添加该节点，并继续递归遍历该节点的每一个孩子节点。
     * 如果当前节点是叶子节点，则在当前路径末尾添加该节点后我们就得到了一条从根节点到叶子节点的路径，将该路径加入到答案即可。
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        binaryTreePaths(root, "", res);
        return res;
    }

    /**
     * 递归函数
     *
     * @param root
     * @param path
     * @param res
     */
    private void binaryTreePaths(TreeNode root, String path, List<String> res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(path + root.val);
            return;
        }
        binaryTreePaths(root.left, path + root.val + "->", res);
        binaryTreePaths(root.right, path + root.val + "->", res);
    }
}
