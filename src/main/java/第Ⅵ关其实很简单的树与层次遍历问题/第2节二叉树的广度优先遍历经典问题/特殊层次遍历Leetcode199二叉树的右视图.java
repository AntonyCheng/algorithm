package 第Ⅵ关其实很简单的树与层次遍历问题.第2节二叉树的广度优先遍历经典问题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-right-side-view/">二叉树的右视图</a>
 *
 * @author AntonyCheng
 * @since 2023/9/18 14:21:19
 */

public class 特殊层次遍历Leetcode199二叉树的右视图 {
    /**
     * 层次遍历法
     * 遍历每一层的所有元素，将每一层最后一个元素存入结果集合中；
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TwoTreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TwoTreeNode> queue = new LinkedList<>();
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
            res.add(temp.get(temp.size() - 1));
        }
        return res;
    }
}
