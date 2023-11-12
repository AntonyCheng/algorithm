package 第Ⅵ关其实很简单的树与层次遍历问题.第2节二叉树的广度优先遍历经典问题;

import java.util.List;

/**
 * N叉树节点类
 *
 * @author AntonyCheng
 * @since 2023/9/18 13:09:49
 */

public class NTreeNode {
    public int val;
    public List<NTreeNode> children;

    public NTreeNode() {
    }

    public NTreeNode(int val) {
        this.val = val;
    }

    public NTreeNode(int val, List<NTreeNode> children) {
        this.val = val;
        this.children = children;
    }
}
