package 第Ⅶ关算法真正开始_递归与二叉树遍历.第1节一图理解递归;

/**
 * 递归
 * 1、递归的简介
 * 递归能够解决很多问题，比如下面这些：
 * （1）与树和二叉树相关的大部问题
 * （2）二分查找相关的问题
 * （3）快速排序、归并排序相关的问题
 * （4）所有回溯的问题
 * （5）所有动态规划的问题
 * 可见，递归是我们算法进阶的基础，是必须要掌握的内容。
 * ----------------------------------------------------------------
 * 2、递归的特征
 * 其实递归本质就一句话：方法自己调用自己。大部分人都知道怎么回事，但是最大的问题就是写不出来代码；
 * 所以我们知道了其本质也需要掌握其特征：
 * （1）执行时范围不断缩小，这样才能触底反弹；
 * （2）递归之前一定会有一个终止条件；
 * 下面我们一条一条看；
 * 第一条——执行范围不断缩小：递归就是数学里的递推，设计递归就是努力寻找数学里的递推公式，
 * 例如阶乘的递推公式就是f(n)=n*f(n-1)，很明显n的范围有限制，即n>0（触底反弹）；
 * 再比如斐波那契数列递归公式f(n)=f(n-1)+f(n-2)（触底反弹条件为n>=2（以元素索引为标准）或n>=3（以元素个数为标准））；
 * 其实范围缩小不一定只体现在n的变化上，也可以是一个抽象的范围，例如数组有规律的变化，例如二分查找、树的递归等；
 * 第二条——递归之前一定会有一个终止条件，其实递归之后还可能会有终止条件，但是在递归之前，一定会有一个终止条件。
 * 这一条可以帮助我们检查写的算法对不对，因为如果一个递归函数想要不断地调用自己，会一直递归下去，知道抛出堆栈溢出异常；
 * 实际一个方法里的递归调用可能不止一次，还会加一些逻辑处理，但是调用递归之前仍然有一个终止的条件，
 * 这一特点启示我们，可以先考虑清楚什么情况下终止，而且相关代码要写在靠前位置的，之后再考虑递归的逻辑，这样可以降低编写的难度。
 * ----------------------------------------------------------------
 * 3、如何写递归
 * （1）第一步：从小到大递归
 * 递归就是数学里的递推，源自于数学归纳法，大致就是你要先猜出存在的关系，例如：f(n)=δf(n-1)，
 * 猜出关系后就要证明当n+1时有f(n+1)=δf(n)，成立即证明对关系的猜测是对的，不过在算法里我们一般不需要这么严谨，
 * 先选几个比较小的值检验一下，再选取几个比较大的值检验一下即可，例如斐波那契数列：
 * arr = {1,1,2,3,5,8,13,21,34,55...}
 * 当n>=2时，就有arr[2] = arr[1] + arr[0]、arr[3] = arr[2] + arr[1]......
 * 最后得出arr[n] = arr[n-1] + arr[n-2]，即递推公式为f(n) = f(n-1) + f(n-2)；
 * （2）第二步：分情况讨论，明确结束条件
 * 上面说过递归里的终止条件一定要靠前，而大部分的递归终止条件不过是n最小开始触底反弹时的一些情况，
 * 例如阶乘的递归，就需要保证数值不小于等于0；再例如斐波那契数列的递归，就需要保证数值不小于等于2或者3；
 * 有些情况也不一定是触底才开始反弹，而是达到某种要求就需要终止，这样需要考虑的情况通常会比较多，最直接的方法就是枚举终止条件，再逐步优化；
 * 确定种植条件对于递归来说至关重要，后面会有很多题目花很大的篇幅来分析怎么判断终止条件，判断完毕也就水到渠成。
 * （3）第三步：组合出完整方法
 * 将递推公式和终止条件组合起来，变成完整的方法。我们能看到的递归方法有很多变换形式，但先不要迷信这些，先分情况逐个写出来，
 * 之后再看到能否精简和优化，在Recursion类中我们会将上面提到的一些例子写一遍，每次写递归的时候，就强迫自己试着去从几个方面来考虑。
 * 上面还有一个重点问题没有介绍，就是入参和出参怎么确定，这个问题因题而异。
 * ----------------------------------------------------------------
 * 4、怎么看懂递归代码
 * 对于很多人来说，递归最大的问题是给了答案也看不懂，其实我们可以将每次方法的调用看成入栈，每次结果的返回看成出栈，
 * 我们在下面以阶乘算法计算f(4)为例画一张图：
 * int f(int n) {           递进：                              回归：
 *     if (n == 1) {        n=4                                在这里一层本来就是n=4
 *         return 1;        执行return (3)*4;                   所以执行f(3)*4=24
 *     }                    此时系统会将n=4的所有信息保存到栈中       ↑
 *     return f(n - 1);     ↓                                   ↑
 * }                        ↓                                   ↑
 * 下面调用普通方法f(3);       ↓                                   ↑
 * int f(int n) {           递进：                              回归：
 *     if (n == 1) {        n=3                                在这里一层本来就是n=3
 *         return 1;        执行return (2)*3;                   所以执行f(2)*3=6
 *     }                    此时系统会将n=3的所有信息保存到栈中       ↑
 *     return f(n - 1);     ↓                                   ↑
 * }                        ↓                                   ↑
 * 下面调用普通方法f(2);       ↓                                   ↑
 * int f(int n) {           递进：                              回归：
 *     if (n == 1) {        n=2                                在这里一层本来就是n=2
 *         return 1;        执行return (1)*2;                   所以执行f(1)*2=2
 *     }                    此时系统会将n=2的所有信息保存到栈中       ↑
 *     return f(n - 1);     ↓                                   ↑
 * }                        ↓                                   ↑
 * 下面调用普通方法f(1);       ↓                                   ↑
 * int f(int n) {           递进：                               ↑
 *     if (n == 1) {        n=1                                 ↑
 *         return 1;        执行return 1;                        ↑
 *     }                    此时系统会将n=1的所有信息保存到栈中        ↑
 *     return f(n - 1);     触底                  --------------→反弹
 * }
 * 触底n==1，进行反弹；
 * 通过这个图你会发现所谓的递归与普通的方法调用没什么区别，只不过我们平时是f(n)里调用g(m)，这里是f(n)继续调用自己而已，没有任何神秘的。
 *
 * @author AntonyCheng
 * @since 2023/9/18 15:34:42
 */

public class Recursion {
    /**
     * 1-n相加
     *
     * @param n
     * @return
     */
    public static int sumN(int n) {
        if (n==1){
            return 1;
        }
        return sumN(n-1)+n;
    }

    /**
     * n的阶乘
     *
     * @param n
     * @return
     */
    public static int factorial(int n){
        if (n<=0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        return factorial(n-1)*n;
    }

    /**
     * 斐波那契数列
     * 1 1 2 3 5 8 13 21 34 55 ...
     *
     * @param n
     * @return
     */
    public static int fibonacci(int n){
        if (n<=2){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }

    /**
     * 二分查找
     *
     * @param arr
     * @param target
     * @return
     */
    public static int binaryLookup(int[] arr,int target){
        if (!isOrder(arr)) {
            throw new RuntimeException("该数组无序!");
        }
        return binaryLookup(arr,0,arr.length-1,target);
    }

    /**
     * 判断是否有序
     *
     * @param arr
     * @return
     */
    public static boolean isOrder(int[] arr){
        boolean ascending = true;
        boolean descending = true;
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i]>=arr[i+1]){
                ascending = false;
            }
            if (arr[i]<arr[i+1]){
                descending = false;
            }
        }
        return ascending||descending;
    }

    /**
     * 二分查找递归方法
     *
     * @param arr
     * @param left
     * @param right
     * @param target
     * @return
     */
    private static int binaryLookup(int[] arr, int left, int right, int target) {
        int middleIndex = (right-left)/2+left;
        if (target==arr[middleIndex]){
            return middleIndex;
        }
        if (left>=right){
            return -1;
        }
        if (target>arr[middleIndex]){
            return binaryLookup(arr,middleIndex+1,right,target);
        } else {
            return binaryLookup(arr,left,middleIndex-1,target);
        }
    }

    public static void main(String[] args) {
        System.out.println(Recursion.sumN(100));
        System.out.println(Recursion.factorial(10));
        for (int i = 1; i < 10; i++) {
            System.out.print(fibonacci(i)+" ");
        }
        System.out.println();
        int[] arr1 = {1,2,3,4,5,6,7};
        int[] arr2 = {6,5,4,3};
        int[] arr3 = {1,2,1,4,1,5};
        System.out.println(isOrder(arr1));
        System.out.println(isOrder(arr2));
        System.out.println(isOrder(arr3));
        System.out.println(binaryLookup(arr1, 1));
    }
}
