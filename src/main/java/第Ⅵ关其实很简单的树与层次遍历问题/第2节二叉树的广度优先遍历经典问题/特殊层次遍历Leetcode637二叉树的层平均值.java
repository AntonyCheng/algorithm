package 第Ⅵ关其实很简单的树与层次遍历问题.第2节二叉树的广度优先遍历经典问题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/average-of-levels-in-binary-tree/">二叉树的层平均值</a>
 *
 * @author AntonyCheng
 * @since 2023/9/18 13:54:09
 */

public class 特殊层次遍历Leetcode637二叉树的层平均值 {
    /**
     * 层次遍历法
     * 遍历每一层，将每一层所有的数据相加，计算出平均值，最后
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TwoTreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Double> res = new ArrayList<>();
        Queue<TwoTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double num = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TwoTreeNode t = queue.poll();
                num += t.val;
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
            res.add(num / size);
        }
        return res;
    }
}
