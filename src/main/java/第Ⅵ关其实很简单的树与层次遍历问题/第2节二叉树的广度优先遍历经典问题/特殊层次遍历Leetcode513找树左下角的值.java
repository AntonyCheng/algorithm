package 第Ⅵ关其实很简单的树与层次遍历问题.第2节二叉树的广度优先遍历经典问题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/find-bottom-left-tree-value/">找树左下角的值</a>
 *
 * @author AntonyCheng
 * @since 2023/9/18 14:41:08
 */

public class 特殊层次遍历Leetcode513找树左下角的值 {
    /**
     * 层次遍历法
     * 注意题目要求，找出的是最底层的最左边的节点，也就是说我们先要遍历到最底层，然后再去找最左边的值；
     * 我们就把每一层的元素用集合收集起来，再把这些列表用一个集合收集起来，最后去获取集合最后一个元素的第一个元素；
     *
     * @param root
     * @return
     */
    public int findBottomLeftValueMethod1(TwoTreeNode root) {
        List<List<Integer>> tempRes = new ArrayList<List<Integer>>();
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
            tempRes.add(temp);
        }
        return tempRes.get(tempRes.size() - 1).get(0);
    }

    /**
     * 层次遍历法（改进版）
     * 像方法一中那样先收集数据在进行处理，时空复杂度都会大幅度上升，对此就需要进行改进，
     * 如果我们可以把每一层都反转一次，那么我们找的位置就成了最底部最右边的数，
     * 我们只需要广度遍历一次就能够得到最后的结果；
     *
     * @param root
     * @return
     */
    public int findBottomLeftValueMethod2(TwoTreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int res = root.val;
        Queue<TwoTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TwoTreeNode t = queue.poll();
            if (t.right != null) {
                queue.offer(t.right);
            }
            if (t.left != null) {
                queue.offer(t.left);
            }
            res = t.val;
        }
        return res;
    }
}
