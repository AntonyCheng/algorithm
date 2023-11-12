package 特辑.传教士与野人问题;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 传教士和野人问题：三个传教士（M）和三个野人（C）在河的一岸（默认左岸），有一条能载一个人或者两个人的船（B）。
 * 请设法使所有人都渡到河的另一岸，要求在任何地方野人数都不能多于传教士的人数。
 * 这个问题我们简化之后就如下图所示：
 * 左岸 右岸                  左岸 右岸
 * 3M   0M   ----一定条件--->  0M  3M
 * 3C   0C                   0C  3C
 * 一定条件：
 * （1）一条能搭载1~2人的小船（B.people.count==1||B.people.count==2）
 * （2）左岸、右岸以及小船上的传教士人数大于等于野人人数（M.count>=C.count）
 * 我们一定要有一个基础的推理前置准备，即：事物（事件）的变化包含确定的初始状态，确定的终止状态以及有限的条件，数目的状态数量一定是有限并且可确定的；
 * 对全部情况进行描述得到下图：
 * 左岸          船上          右岸
 * M C B        M C B        M C B
 * 3 3 1        0 0 0        0 0 0    ※
 * 3 1 0  --->  0 2 1  --->  0 0 0    这一步船上的情况还可以是1 1 1
 * 3 1 0        0 0 0        0 2 1    ※
 * 3 1 0  <---  0 1 1  <---  0 1 0
 * 3 2 1        0 0 0        0 1 0    ※
 * 3 0 0  --->  0 2 1  --->  0 1 0
 * 3 0 0        0 0 0        0 3 1    ※
 * 3 0 0  <---  0 1 1  <---  0 2 0
 * 3 1 1        0 0 0        0 2 0    ※
 * 1 1 0  --->  2 0 1  --->  0 2 0
 * 1 1 0        0 0 0        2 2 1    ※
 * 1 1 0  <---  1 1 1  <---  1 1 0
 * 2 2 1        0 0 0        1 1 0    ※
 * 0 2 0  --->  2 0 1  --->  1 1 0
 * 0 2 0        0 0 0        3 1 1    ※
 * 0 2 0  <---  0 1 1  <---  3 0 0
 * 0 3 1        0 0 0        3 0 0    ※
 * 0 1 0  --->  0 2 1  --->  3 0 0
 * 0 1 0        0 0 0        3 2 1    ※
 * 0 1 0  <---  0 1 1  <---  3 1 0    这一步船上的情况还可以是1 0 1
 * 0 2 1        0 0 0        3 1 0    ※
 * 0 0 0  --->  0 2 1  --->  3 1 0
 * 0 0 0        0 0 0        3 3 1    ※
 * 至此整个事件已经全部列举了出来。
 * 显然需要定义一个这样的状态值：Status[M.count, C.count, B.count]（依次是传教士的人数，野人的人数，岸边的船数）
 * 其中：0<=M.count<=3、0<=C.count<=3、0<=B.count<=1
 * 因此可以确定初始状态：左岸（Status[3,3,1]）、右岸（Status[0,0,0]）; 终止状态：左岸（Status[0,0,0]）、右岸（Status[3,3,1]）
 * 这个状态是可以简化的，因为由条件可知在知道左岸的情况下一定可以推出右岸，那么我们默认左岸，得到最终的初始状态和终止状态，即：
 * 初始状态：Status[3,3,1]，终止状态：Status[0,0,0]
 * 以上之所以能够进行穷举，是因为状态有限，我们主要看带※行的左岸的情况
 * 当左岸有船时，即B=1，状态只会有五种变化：
 * （1）M = M-1，C = C-1，B = B-1 = 0       M=M-1  C=C-1  B=0
 * （2）M = M-2，C = C-0，B = B-1 = 0       M=M-2  B=0
 * （3）M = M-0，C = C-2，B = B-1 = 0 ====> C=C-2  B=0
 * （4）M = M-1，C = C-0，B = B-1 = 0       M=M-1  B=0
 * （5）M = M-0，C = C-1，B = B-1 = 0       C=C-1  B=0
 * 当左岸没船时，即B=0，状态只会有五种变化：
 * （1）M = M+1，C = C+1，B = B+1 = 1       M=M+1  C=C+1  B=1
 * （2）M = M+2，C = C+0，B = B+1 = 1       M=M+2  B=1
 * （3）M = M+0，C = C+2，B = B+1 = 1 ====> C=C+2  B=1
 * （4）M = M+1，C = C+0，B = B+1 = 1       M=M+1  B=1
 * （5）M = M+0，C = C+1，B = B+1 = 1       C=C+1  B=1
 * 以上十种变化情况均需要满足条件的状态空间才能执行。
 * 现在可以根据穷举结果进行满足条件的状态空间的描述：
 * [3,3,1],[3,2,1],[3,1,1],[3,0,1],[2,2,1],[1,1,1],[0,3,1],[0,2,1],[0,1,1],
 * [0,0,0],[3,2,0],[3,1,0],[3,0,0],[2,2,0],[1,1,0],[0,3,0],[0,2,0],[0,1,0];
 * 对此可以画出一颗搜索树，该搜索树中只包含：
 * 详情查看：<a href="http://121.36.90.144:30080/wp-content/uploads/2023/10/传教士与野人问题.png">传教士与野人问题</a>
 * 编码文字描述如下：
 * 1. 首先定义了一个状态节点类  StatusNode ，包含三个属性： missionaries  表示传教士数量， cannibals  表示野人数量， boat  表示此岸船的数量。
 * 2. 定义了一个辅助双端队列  deque ，用于遍历状态节点；定义了一个满足条件的状态节点队列  conditionNodeList ，用于存放满足条件的状态节点。
 * 3. isGoal(StatusNode statusNode)  方法用于判断一个状态节点是否为终止节点，即传教士和野人都已经过河到达对岸。
 * 4. isLegal(StatusNode statusNode)  方法用于判断一个状态节点是否满足状态条件，即传教士数量在 0 到 3 之间，野人数量在 0 到 3 之间，并且满足传教士数量等于野人数量或传教士数量等于 3 或传教士数量等于 0。
 * 5. isEqual(StatusNode m1, StatusNode m2)  方法用于判断两个状态节点是否相同，即传教士数量、野人数量和船的数量都相等。
 * 6. isContainByConditionNodeList(StatusNode statusNode)  方法用于判断一个状态节点是否存在于满足条件的状态节点列表中。
 * 7. addConditionNode(StatusNode statusNode, int boatCount, Deque<StatusNode> deque)  方法用于穷举状态节点的情况，并且选择满足条件的状态节点按次序无重复地存放到双端队列中。
 * 根据船的数量，生成新的状态节点，并判断是否满足条件和是否已存在于满足条件的状态节点列表中。
 * 8. main(String[] args)  方法是算法的执行类。首先创建初始状态节点  initNode ，将其加入双端队列  deque 。然后开始循环遍历双端队列，取出队列中的节点。
 * 如果节点是终止节点，则将其加入满足条件的状态节点列表，并输出结果。如果节点不在满足条件的状态节点列表中，则将其加入列表，并生成新的状态节点，继续遍历。最终得到满足条件的状态节点列表。
 *
 * @author AntonyCheng
 * @since 2023/10/2 20:09:09
 */

public class MissionariesAndCannibals {
    /**
     * 状态节点类
     */
    static class StatusNode {
        /**
         * 传教士数量
         */
        int missionaries;
        /**
         * 野人数量
         */
        int cannibals;
        /**
         * 此岸船的数量
         */
        int boat;

        public StatusNode() {

        }

        public StatusNode(int missionaries, int cannibals, int boat) {
            this.missionaries = missionaries;
            this.cannibals = cannibals;
            this.boat = boat;
        }
    }

    /**
     * 遍历所需要的辅助双端队列
     */
    static Deque<StatusNode> deque = new LinkedList<>();

    /**
     * 满足条件的状态节点队列
     */
    static List<StatusNode> conditionNodeList = new ArrayList<>();

    /**
     * 判断一个状态节点是否为终止节点
     *
     * @param statusNode
     * @return
     */
    public static boolean isGoal(StatusNode statusNode) {
        return statusNode.missionaries == 0 && statusNode.cannibals == 0 && statusNode.boat == 0;
    }

    /**
     * 判断一个状态节点是否满足状态条件
     *
     * @param statusNode
     * @return
     */
    public static boolean isLegal(StatusNode statusNode) {
        if (statusNode.missionaries >= 0 && statusNode.missionaries <= 3 && statusNode.cannibals >= 0 && statusNode.cannibals <= 3) {
            return (statusNode.missionaries == statusNode.cannibals) || (statusNode.missionaries == 3) || (statusNode.missionaries == 0);
        } else {
            return false;
        }
    }

    /**
     * 判断两个状态节点是否相同
     *
     * @param m1
     * @param m2
     * @return
     */
    static boolean isEqual(StatusNode m1, StatusNode m2) {
        return m1.missionaries == m2.missionaries && m1.cannibals == m2.cannibals && m1.boat == m2.boat;
    }

    /**
     * 判断一个状态节点存在于满足条件的状态节点列表
     *
     * @param statusNode
     * @return
     */
    public static boolean isContainByConditionNodeList(StatusNode statusNode) {
        for (StatusNode node : conditionNodeList) {
            if (isEqual(statusNode, node)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 穷举状态节点的情况，并且选择满足条件的状态节点按次序无重复地存放到双端队列中
     */
    public static void addConditionNode(StatusNode statusNode, int boatCount, Deque<StatusNode> deque) {
        StatusNode[] nodes = new StatusNode[5];
        for (int i = 0; i < 5; i++) {
            nodes[i] = new StatusNode(0, 0, 0);
        }
        if (boatCount == 1) {
            for (StatusNode node : nodes) {
                node.boat = 0;
            }
            nodes[0].missionaries = statusNode.missionaries - 1;
            nodes[0].cannibals = statusNode.cannibals - 1;
            nodes[1].missionaries = statusNode.missionaries - 2;
            nodes[1].cannibals = statusNode.cannibals;
            nodes[2].missionaries = statusNode.missionaries;
            nodes[2].cannibals = statusNode.cannibals - 2;
            nodes[3].missionaries = statusNode.missionaries - 1;
            nodes[3].cannibals = statusNode.cannibals;
            nodes[4].missionaries = statusNode.missionaries;
            nodes[4].cannibals = statusNode.cannibals - 1;
        } else {
            for (StatusNode node : nodes) {
                node.boat = 1;
            }
            nodes[0].missionaries = statusNode.missionaries + 1;
            nodes[0].cannibals = statusNode.cannibals + 1;
            nodes[1].missionaries = statusNode.missionaries + 2;
            nodes[1].cannibals = statusNode.cannibals;
            nodes[2].missionaries = statusNode.missionaries;
            nodes[2].cannibals = statusNode.cannibals + 2;
            nodes[3].missionaries = statusNode.missionaries + 1;
            nodes[3].cannibals = statusNode.cannibals;
            nodes[4].missionaries = statusNode.missionaries;
            nodes[4].cannibals = statusNode.cannibals + 1;
        }
        for (StatusNode node : nodes) {
            if (isLegal(node) && !isContainByConditionNodeList(node)) {
//                deque.offerFirst(node);// 双端队列做队列，队列后进后出，广度优先搜索，最后得到一条最小解序列
                deque.offerLast(node);// 双端队列做堆栈，堆栈先进后出，深度优先搜索，最后得到一条最小解序列
            }
        }
    }

    /**
     * 算法执行主方法
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        StatusNode initNode = new StatusNode(3, 3, 1);
        deque.offerFirst(initNode);
        while (!deque.isEmpty()) {
            StatusNode tempNode = deque.pollFirst();
            if (isGoal(tempNode)) {
                conditionNodeList.add(tempNode);
                for (StatusNode statusNode : conditionNodeList) {
                    System.out.println(statusNode.missionaries + "," + statusNode.cannibals + "," + statusNode.boat);
                }
                break;
            }
            if (!isContainByConditionNodeList(tempNode)) {
                conditionNodeList.add(tempNode);
                addConditionNode(tempNode, tempNode.boat, deque);
            }
        }
        System.out.println(System.currentTimeMillis());
    }
}