package 第Ⅵ关其实很简单的树与层次遍历问题.第1节理解树的结构;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树的基础
 * 1、树的常见概念
 * 树是一个有n个有限节点组成一个具有层次关系的集合，每个节点有0个或者多个子节点，
 * 没有父节点的节点称为根节点，也就是说除了根节点以外每个节点都有父节点，并且有且只有一个。
 * 树的种类比较多，我们最常见的应该是二叉树了，基本结构如下：
 *               o
 *            /    \
 *           o      o
 *         /  \    /  \
 *        o    o  o    o
 *       / \     / \
 *      o   o   o   o
 * 参考上面的结构，可以很方便的理解树的如下概念：
 *  (1)节点的度:一个节点含有的子节点的个数称为该节点的度;
 *  (2)树的度:一棵树中，最大的节点的度称为树的度，注意与节点度的区别；
 *  (3)叶节点或终端节点:度为0的节点称为叶节点;
 *  (4)非终端节点或分支节点:度不为0的节点;
 *  (5)双亲节点或父节点:若一个节点含有子节点，则这个节点称为其子节点的父节点;
 *  (6)孩子节点或子节点:一个节点含有的子树的根节点称为该节点的子节点;
 *  (7)兄弟节点:具有相同父节点的节点互称为兄弟节点;
 *  (8)节点的祖先:从根到该节点所经分支上的所有节点;
 *  (9)子孙:以某节点为根的子树中任一节点都称为该节点的子孙;
 *  (10)森林:由m(m>=0)棵互不相交的树的集合称为森林;
 *  (11)无序树:树中任意节点的子节点之间没有顺序关系，这种树称为无序树，也称为自由树;
 *  (12)有序树:树中任意节点的子节点之间有顺序关系，这种树称为有序树;
 *  (13)二叉树:每个节点最多含有两个子树的树称为二叉树;
 * ------------------------------------------------------------------------
 * 2、树的性质
 * （1）在二叉树的第i层上至多有 2^(i-1) 个结点（i>0）;
 * （2）深度为k的二叉树至多有 2^k-1 个结点（k>0）;
 * （3）对于任意一棵二叉树，如果其叶结点数为N0，而度数为2的结点总数为N2，则N0=N2+1;
 * （4）具有n个结点的完全二叉树的深度必为 log2(n+1);
 * （5）对完全二叉树，若从上至下、从左至右编号，则编号为i 的结点，其左孩子编号必为2i;
 * 其右孩子编号必为2i＋1，其双亲的编号必为i/2（i＝1 时为根,除外）;
 * 满二叉树和完全二叉树是经常晕的问题，我们有必要单独看一下。
 * 满二叉树就是如果一棵二叉树只有度为0的节点和度为2的节点，并且度为0的节点在同一层上，则这棵二叉树为满二叉树。
 *               o
 *            /    \
 *           o      o
 *         /  \    /  \
 *        o   o   o    o
 *       /\  /\  /\   / \
 *      o o o o o  o o   o
 * 这棵二叉树为满二叉树，也可以说深度为k=4，有2^k-1=15个节点的二叉树。
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 这个定义最邪乎了，估计大部分看了之后还是不懂什么是完全二叉树，看这个图就知道了：
 *               o                          o                          o
 *            /    \                     /    \                     /    \
 *           o      o                   o      o                   o      o
 *         /  \    /  \               /  \    /  \               /  \    /  \
 *        o   o   o    o             o   o   o    o             o   o   o    o
 *       /\  /                      /\  /\                     /\  /\   \   / \
 *      o o o                      o o o o                    o o o o    o o   o
 *         是完全二叉树                 是完全二叉树                 不是完全二叉树
 * 前面两棵树的前n-1层都是满的，最后一层所有节点都集中在左侧区域，而且节点之间不能有空隙。
 * 最后一个为什么不是？因为有一节点缺了一个左子节点。
 * ------------------------------------------------------------------------
 * 3、树的定义和存储方式
 * 在这里我们主要文字讨论，至于代码实现放在BaseTree这个类中；
 * 定义树的原理与前面讲的链表本质上是一样的，只不过多了一个指针，如果是二叉树，只要在链表的定义上增加一个指针就可以了。
 * 这里本质上就是有两个Node引用（left和right），分别指向两个位置，为了便于理解，我们分别命名为左孩子和右孩子。
 * 如果是N叉树该如何定义呢？
 * 其实就是每个节点最多可以有N个指针指向其他地方，这是不用left和right，使用一个List<Node>就可以了。
 * 那能否用数组来存储二叉树呢？答案是可以，顺序存储的方式如图:
 *             a(0)
 *            /     \
 *         b(1)     c(2)
 *         /  \     /  \
 *      d(3) e(4) f(5) g(6)
 * 数组：a | b | c | d | e | f | g
 * 下标：0   1   2   3   4   5   6
 * 用数组来存储二叉树如何遍历的呢？如果父节点的数组下表是i，那么它的左孩子就是i*2+1，右孩子就是i*2+2。
 * 但是用链式表示的二叉树，更有利于我们理解，所以一般我们都是用链式存储二叉树。
 * 所以大家要了解，用数组依然可以表示二叉树。
 * 使用数组存储的最大不足是可能存在大量的空间浪费。
 * 例如上图中如果b分支没有，那么数组种1 3 4位置都要空着，但是整个数组的大小仍然是7，因此很少使用数组来存储树。
 * ------------------------------------------------------------------------
 * 4、树的遍历方式
 * 我们现在就来看一下树的常见遍历方法。二叉树的遍历方式有层次遍历和深度优先遍历两种：
 * （1）深度优先遍历：先往深走，遇到叶子节点再往回走。
 *      a、前序遍历
 *      b、中序遍历
 *      c、后序遍历
 * （2）广度优先遍历：一层一层的去遍历，一层访问完再访问下一层。
 * 这两种遍历方式不仅仅是二叉树，N叉树也有这两种方式的，图结构也有，只不过我们更习惯叫广度优先和深度优先。
 * 深度优先遍历中的三种遍历方法可能会让人产生疑惑，问题就在不清楚这里前中后是相对谁来说的。
 * 记住一点：前指的是中间的父节点在遍历中的顺序，只要大家记住前中后序指的就是中间节点的位置就可以了。
 * 前序遍历——中左右
 * 中序遍历——左中右
 * 后序遍历——左右中
 * 用一道例题来描述这个定义（右图为例题图）：
 *                                        5
 *                                     /     \
 * 前序遍历（中左右）：5412678            4      6
 * 中序遍历（左中右）：1425768          /  \    /  \
 * 后序遍历（左右中）：1247865         1   2   7   8
 * 后面大量的算法都是以深度优先遍历和广度优先遍历两种方式为基础进行拓展的；
 * 有的题目根据处理角度不同 ，可以用层次遍历，也可以用一种甚至两种深度优先的方式来实现。
 * ------------------------------------------------------------------------
 * 5、通过序列构造二叉树（针对深度优先遍历的题目）
 * 其实这个过程就是深度优先遍历的逆过程，由遍历后的结果构造遍历前的树；
 * 对前面的结果进行观察：前序遍历后根节点一定在第一个位置，后序遍历后根节点一定在最后一个位置，中序遍历后根节点位置左右两边的数字必是左右子树中的数字；
 * 下面是三个序列遍历结果，之后的笔记围绕这个来展开：
 * 前序：1 2 3 4 5 6 8 7 9 10 11 12 13 15 14
 * 中序：3 4 8 6 7 5 2 1 10 9 11 15 13 14 12
 * 后序：8 7 6 5 4 3 2 10 15 14 13 12 11 9 1
 * （1）通过前序序列和中序序列复原二叉树
 * 前序：1 2 3 4 5 6 8 7 9 10 11 12 13 15 14
 * 中序：3 4 8 6 7 5 2 1 10 9 11 15 13 14 12
 * 第一步：找根节点和根节点的左右子树（前序第一个是根节点，然后在中序中将根节点左右两边元素分别归为一类，即左右子树），并且标记出来；
 * 前序：1 [2 3 4 5 6 8 7] [9 10 11 12 13 15 14]
 * 中序：[3 4 8 6 7 5 2] 1 [10 9 11 15 13 14 12]
 * 第二步：循环往复去找子树的左右子树，知道遍历到叶子节点（即序列不可再分）才结束；
 * 前序：1 [2 [3 [4 [5 [6 [8] [7]]]]]] [9 [10] [11 [12 [13 [15] [14]]]]]
 * 中序：[[3 [4 [[[8] 6 [7]] 5]]] 2] 1 [[10] 9 [11 [[[15] 13 [14]] 12]]]
 * 第三步：依靠处理完之后的中序序列复现二叉树；
 *                  1
 *              /        \
 *            2           9
 *         /             /   \
 *      3             10     11
 *        \                     \
 *         4                     12
 *          \                  /
 *           5                13
 *          /                / \
 *         6               15  14
 *        / \
 *       8  7
 * （2）通过后序序列和中序序列复原二叉树
 * 后序：8 7 6 5 4 3 2 10 15 14 13 12 11 9 1
 * 中序：3 4 8 6 7 5 2 1 10 9 11 15 13 14 12
 * 第一步：找根节点和根节点的左右子树（后序最后一个是根节点，然后在中序中将根节点左右两边元素分别归为一类，即左右子树），并且标记出来；
 * 后序：[8 7 6 5 4 3 2] [10 15 14 13 12 11 9] 1
 * 中序：[3 4 8 6 7 5 2] 1 [10 9 11 15 13 14 12]
 * 第二步：循环往复去找子树的左右子树，知道遍历到叶子节点（即序列不可再分）才结束；
 * 后序：[[[[[[8] [7] 6] 5] 4] 3] 2] [[10] [[[[15] [14] 13] 12] 11] 9] 1
 * 中序：[[3 [4 [[[8] [6] 7] 5]]] 2] 1 [[10] 9 [11 [[[15] 13 [14]] 12]]]
 * 第三步：依靠处理完之后的中序序列复现二叉树；
 *                  1
 *              /        \
 *            2           9
 *         /             /   \
 *      3             10     11
 *        \                     \
 *         4                    12
 *          \                  /
 *           5                13
 *          /                / \
 *         6               15  14
 *        / \
 *       8  7
 * 这样可以看出，前中序列恢复二叉树和后中序列恢复的二叉树相同，在BaseTree类中我们会探讨恢复二叉树的相关题目；
 * 这一关重点讨论深度优先遍历的问题，在下一关中重点讨论广度优先遍历的问题；
 *
 * @author AntonyCheng
 * @since 2023/9/16 23:01:30
 */

public class BaseTree {
    /**
     * 树节点类
     */
    class TreeNode {
        int val;
        List<TreeNode> nodes;
        TreeNode(int val){
            this.val = val;
            this.nodes = null;
        }
    }

    /**
     * 二叉树节点类
     */
    class BinaryTreeNode {
        int val;
        BinaryTreeNode left;
        BinaryTreeNode right;
        BinaryTreeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * 通过前序和中序恢复二叉树的结构（Leetcode105题）
     * 前序：{根}                  {        左子树        }                  {        右子树        }
     *    preLeft          preLeft+1  pIndex-inLeft+preLeft         pIndex-inLeft+preLeft+1  preRight
     * 中序：{        左子树        }                  {根}                  {        右子树        }
     *    inLeft             pIndex-1              pIndex              pIndex+1               inRight
     * 总体思想就是递归，递归的跳出条件就是当序列（子序列）最左值大于最右值；
     * 由题目中描述说没有重复的值，那么pIndex的检索工作可以存入HashMap中进行；
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public BinaryTreeNode buildTreeByPreAndIn(int[] preorder, int[] inorder) {
        int preLen = preorder.length;
        int inLen = inorder.length;

        Map<Integer,Integer> map = new HashMap<Integer,Integer>(){
            {
                for (int i = 0; i < inLen;i++){
                    put(inorder[i],i);
                }
            }
        };
        return buildTreeByPreAndIn(preorder, 0 , preLen-1 , map , 0 , inLen-1);
    }

    /**
     * 前中序列恢复二叉树递归函数
     *
     * @param preorder 前序序列
     * @param preLeft 前序序列最左值
     * @param preRight 前序序列最右值
     * @param inMap 存入Map<num,index>的中序序列
     * @param inLeft 中序序列最左值
     * @param inRight 中序序列最右值
     * @return
     */
    private BinaryTreeNode buildTreeByPreAndIn(int[] preorder, int preLeft, int preRight, Map<Integer, Integer> inMap, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight){
            return null;
        }
        BinaryTreeNode root = new BinaryTreeNode(preorder[preLeft]);
        int pIndex = inMap.get(preorder[preLeft]);
        root.left = buildTreeByPreAndIn(preorder, preLeft + 1,pIndex - inLeft + preLeft, inMap, inLeft,pIndex-1);
        root.right = buildTreeByPreAndIn(preorder, pIndex - inLeft + preLeft + 1, preRight, inMap,pIndex+1, inRight);
        return root;
    }

    /**
     * 通过后序和中序恢复二叉树的结构（Leetcode106题）
     * 后序：{        左子树        }                  {        右子树        }                  {根}
     *    postLeft pIndex-inRight+postRight-1  pIndex-inRight+postRight postRight-1         postRight
     * 中序：{        左子树        }                  {根}                  {        右子树        }
     *    inLeft             pIndex-1              pIndex            pIndex+1                 inRight
     * 总体思想就是递归，递归的跳出条件就是当序列（子序列）最左值大于最右值；
     * 由题目中描述说没有重复的值，那么pIndex的检索工作可以存入HashMap中进行；
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public BinaryTreeNode buildTreeByPostAndIn(int[] inorder,int[] postorder) {
        int inLen = inorder.length;
        int postLen = postorder.length;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>(){
            {
                for (int i = 0; i < inLen; i++) {
                    put(inorder[i],i);
                }
            }
        };
        return buildTreeByPostAndIn(postorder,0,postLen-1,map,0,inLen-1);
    }

    /**
     * 后中序列恢复二叉树递归函数
     *
     * @param postorder 后序序列
     * @param postLeft 后序序列最左值
     * @param postRight 后序序列最右值
     * @param inMap 存入Map<num,index>的中序序列
     * @param inLeft 中序序列最左值
     * @param inRight 中序序列最右值
     * @return
     */
    private BinaryTreeNode buildTreeByPostAndIn(int[] postorder, int postLeft, int postRight, Map<Integer, Integer> inMap, int inLeft, int inRight) {
        if (postLeft>postRight || inLeft>inRight){
            return null;
        }
        BinaryTreeNode root = new BinaryTreeNode(postorder[postRight]);
        int pIndex = inMap.get(postorder[postRight]);
        root.left = buildTreeByPostAndIn(postorder,postLeft,pIndex-inRight+postRight-1,inMap,inLeft,pIndex-1);
        root.right = buildTreeByPostAndIn(postorder,pIndex-inRight+postRight,postRight-1,inMap,pIndex+1,inRight);
        return root;
    }
}
