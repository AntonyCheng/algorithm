package 第Ⅴ关算法的备胎Hash和找靠山的队列.第1节队列和Hash的特征;

import java.util.*;

/**
 * 1、Hash基础
 * 哈希（Hash）也称为散列，就是吧任意长度的输入，通过散列算法，变换成固定长度的输出，这个输出值就是散列值。
 * 散列算法中有一个很重要的操作叫做“映射“，就是把散列值和地址值相互绑定，这样就能使其访问的时间复杂度为O(1)。
 * 下面就演示把一个存放1-15的数组中所有的值存入一个大小为7的Hash表中（计算公式/模拟散列算法是index=number%7）：
 * 0        1        2        3        4        5        6        Hash表索引
 * null     1        2        3        4        5        6
 * 7        8        9        10       11       12       13       Array值
 * 14       15
 * 当我索引13是否在Hash表中时，通过公式得到Hash索引为6，Hash(6)中就有13，所以在访问时间复杂度就为O(1)；
 * ---------------------------------------------------------------------
 * 2、Hash冲突（Hash碰撞）
 * （1）什么是Hash冲突（Hash碰撞）？
 * 即两个不同的值通过散列算法计算出来得到的是同一个散列值（就例如上面的Hash(1)下的1、8、15）；
 * （2）如何解决Hash冲突（Hash碰撞）？
 * 一共有四种方法（前两种最常见）：1、开放地址法（Java中的ThreadLocal）；2、链地址法（Java中的ConcurrentHashMap）；3、再哈希法（布隆过滤器）；4、建立公共溢出区；
 * 开放地址法：当哈希冲突发生时，从发生冲突的那个单元起，按照一定的次序，从哈希表中寻找一个空闲的单元，然后把发生冲突的元素存入到该单元，而这个寻找过程是依照一个复杂公式进行计算的；
 * 链地址法（开发常用的HashMap）：首先该结构推荐使用2的n次幂的Hash长度，其次当入链元素数量大于Hash长度的75%时会触发扩容机制，链地址法示意图如下：
 * 0    1    2    3    4    5    6    7    （Hash长度为8满足2的n次幂）
 * |    |              |    |
 * 7    8              11   5
 * |
 * 14
 * 在Java8之后，当某条链长度大于8时，该Hash索引下的链会被转换成为红黑树；
 * ---------------------------------------------------------------------
 * 3、Hash能解决的一些经典问题，例如大规模数据的读写、大规模内嵌循环问题（N数之和）；
 *
 * @author AntonyCheng
 * @since 2023/9/11 21:34:02
 */

public class BaseHash {
    /**
     * 以下列举Java中的Hash数据结构
     *
     * @param args
     */
    public static void main(String[] args) {
        // 无序、允许null键和null值、高性能、线程不安全
        HashMap<Object, Object> hashMap = new HashMap<>();
        // 存储唯一值、无序、允许null元素、线程不安全
        HashSet<Object> hashSet = new HashSet<>();
        // 线程安全、不允许null值和null键、较低性能
        Hashtable<Object, Object> hashtable = new Hashtable<>();
        // 有序、允许null值和null键、中等性能
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        // 有序、不允许null键但允许null值、较低性能
        TreeMap<Object, Object> treeMap = new TreeMap<>();
    }
}
