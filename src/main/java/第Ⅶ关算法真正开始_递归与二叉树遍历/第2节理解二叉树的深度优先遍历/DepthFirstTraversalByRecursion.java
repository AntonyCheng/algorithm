package 第Ⅶ关算法真正开始_递归与二叉树遍历.第2节理解二叉树的深度优先遍历;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归实现深度优先遍历类
 * 深度优先遍历中包含三种遍历方式：前序遍历、中序遍历、后序遍历；
 * 深度优先遍历和递归深度绑定，核心思想就是父节点只关注儿子节点，而不关注孙子节点，每当遍历到一个节点时，默认其为父节点，
 * 有无儿子节点就成了终止条件，如果没有儿子节点就终止即可；
 *
 * @author AntonyCheng
 * @since 2023/9/19 10:10:55
 */

public class DepthFirstTraversalByRecursion {
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
     *
     * @param root
     * @return
     */
    public static List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        preOrder(root, res);
        return res;
    }

    /**
     * 前序遍历递归方法
     *
     * @param root
     * @param list
     */
    private static void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> midOrder(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        midOrder(root, res);
        return res;
    }

    /**
     * 中序遍历递归方法
     *
     * @param root
     * @param list
     */
    private static void midOrder(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        midOrder(root.left, list);
        list.add(root.val);
        midOrder(root.right, list);
    }

    /**
     * 后序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> postOrder(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    /**
     * 后序遍历递归方法
     *
     * @param root
     * @param list
     */
    private static void postOrder(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        preOrder(root).forEach(System.out::print);
        System.out.println();
        midOrder(root).forEach(System.out::print);
        System.out.println();
        postOrder(root).forEach(System.out::print);
    }
}
