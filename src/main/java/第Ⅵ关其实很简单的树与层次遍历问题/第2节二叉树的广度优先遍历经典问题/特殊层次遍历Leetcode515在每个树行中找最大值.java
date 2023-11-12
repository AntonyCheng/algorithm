package 第Ⅵ关其实很简单的树与层次遍历问题.第2节二叉树的广度优先遍历经典问题;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/find-largest-value-in-each-tree-row/">在每个树行中找最大值</a>
 *
 * @author AntonyCheng
 * @since 2023/9/18 13:29:52
 */

public class 特殊层次遍历Leetcode515在每个树行中找最大值 {

    /**
     * 层次遍历法
     * 将每层遍历之后获得的集合中的最大值放进结果集合中；
     *
     * @param root
     * @return
     */
    public List<Integer> largestValuesMethod1(TwoTreeNode root) {
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
            Integer max = Collections.max(temp);
            res.add(max);
        }
        return res;
    }

    /**
     * 层次遍历法（时间改进版）
     * 将每层遍历之后获得的集合中的最大值放进结果集合中；
     * 而找最大值的这个过程我们使用打擂法实现同步查找；
     *
     * @param root
     * @return
     */
    public List<Integer> largestValuesMethod2(TwoTreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TwoTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            int max = queue.peek().val;
            for (int i = 0; i < size; i++) {
                TwoTreeNode t = queue.poll();
                if (t.val > max) {
                    max = t.val;
                }
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
            res.add(max);
        }
        return res;
    }

    /**
     * 层次遍历法（时空改进版）
     * 将每层遍历之后获得的集合中的最大值放进结果集合中；
     * 而找最大值的这个过程我们使用打擂法实现同步查找；
     * 打擂法的第一个擂主通常是打擂元素中第一个元素，这里会进行不断地打雷回合，
     * 不断变化第一个擂主就会大大提升空间复杂度，我们索性把这个最值规定成计算机的最值，
     * 即：使用Integer中的MIN_VALUE和MAX_VALUE
     *
     * @param root
     * @return
     */
    public List<Integer> largestValuesMethod3(TwoTreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TwoTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TwoTreeNode t = queue.poll();
                if (t.val > max) {
                    max = t.val;
                }
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
            res.add(max);
        }
        return res;
    }
}
