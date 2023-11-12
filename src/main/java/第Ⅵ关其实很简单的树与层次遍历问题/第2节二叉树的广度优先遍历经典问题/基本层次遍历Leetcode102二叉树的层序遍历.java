package 第Ⅵ关其实很简单的树与层次遍历问题.第2节二叉树的广度优先遍历经典问题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal/">二叉树的层序遍历</a>
 *
 * @author AntonyCheng
 * @since 2023/9/17 15:30:54
 */

public class 基本层次遍历Leetcode102二叉树的层序遍历 {
    /**
     * 逐层遍历标记法
     * 这个题难点就在于如何判断一层被遍历完毕，这里关键点就在于队列的大小；
     * 每次父节点入队后会让队列有一个size值，而这个size值就是该层的大小；
     * 因此循环size次就能够精确遍历出该层所存在的父节点个数，在循环的同时出队父节点，入队子节点，就能够完成层序遍历；
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TwoTreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Queue<TwoTreeNode> queue = new LinkedList<TwoTreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TwoTreeNode t = queue.poll();
                temp.add(t.val);
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
            res.add(temp);
        }
        return res;
    }
}
