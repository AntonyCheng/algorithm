package 第Ⅵ关其实很简单的树与层次遍历问题.第2节二叉树的广度优先遍历经典问题;

/**
 * 二叉树节点类
 *
 * @author AntonyCheng
 * @since 2023/9/17 15:32:46
 */

public class TwoTreeNode {
    int val;
    TwoTreeNode left;
    TwoTreeNode right;

    TwoTreeNode() {
    }

    TwoTreeNode(int val) {
        this.val = val;
    }

    TwoTreeNode(int val, TwoTreeNode left, TwoTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
