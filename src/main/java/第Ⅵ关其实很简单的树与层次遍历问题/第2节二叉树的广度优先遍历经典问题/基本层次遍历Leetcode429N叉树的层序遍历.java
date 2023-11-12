package 第Ⅵ关其实很简单的树与层次遍历问题.第2节二叉树的广度优先遍历经典问题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/n-ary-tree-level-order-traversal/">N叉树的层序遍历</a>
 *
 * @author AntonyCheng
 * @since 2023/9/18 13:09:12
 */

public class 基本层次遍历Leetcode429N叉树的层序遍历 {
    /**
     * 层序遍历法
     * 类似于二叉树的层序遍历，只不过入队子节点时，不止入队左右子节点而已；
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(NTreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<NTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NTreeNode t = queue.poll();
                temp.add(t.val);
                if (t.children != null) {
                    for (int j = 0; j < t.children.size(); j++) {
                        queue.offer(t.children.get(j));
                    }
                }
            }
            res.add(temp);
        }
        return res;
    }
}
