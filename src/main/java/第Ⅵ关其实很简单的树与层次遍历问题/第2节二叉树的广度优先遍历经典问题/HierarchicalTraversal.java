package 第Ⅵ关其实很简单的树与层次遍历问题.第2节二叉树的广度优先遍历经典问题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的广度优先遍历问题（层次遍历问题）
 * 广度优先在面试题中的出现频率非常高，基本思想就是图转队列的变换思想，掌握解题模板之后就属于较为简单的题；
 * 广度优先又叫层次遍历，基本过程如下：
 * 基本过程文字描述就是父节点及其兄弟节点入队之后，依次将他们的左右子节点入队，然后父节点出队，子节点作为新的父节点；
 * [1]从左去入队：   3                                 题图如下：
 * [2]子节点入队：   3    9     20                          3
 * [3]出队去遍历：   9    20                              /   \
 * [4]子节点入队：   9    20    8    13    15    17      9    20
 * [5]出队去遍历：   8    13    15   17                 / \   / \
 * [6]出队去遍历：   null                              8  13 15 17
 * 那么下面我们就会以这颗二叉树为例，对其进行遍历，存入集合并输出；
 *
 * @author AntonyCheng
 * @since 2023/9/17 14:36:21
 */

public class HierarchicalTraversal {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public static List<Integer> hierarchicalTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        //将根节点放入队列中，然后不断遍历队列
        queue.add(root);
        //有多少元素执行多少次
        while (!queue.isEmpty()) {
            //获取当前队列的长度，这个长度相当于当前这一层的节点个数
            TreeNode t = queue.remove();
            res.add(t.val);
            if (t.left != null) {
                queue.add(t.left);
            }
            if (t.right != null) {
                queue.add(t.right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(13);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(17);
        hierarchicalTraversal(root).forEach(System.out::println);
    }
}
