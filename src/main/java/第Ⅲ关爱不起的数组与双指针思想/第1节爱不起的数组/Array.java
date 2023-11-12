package 第Ⅲ关爱不起的数组与双指针思想.第1节爱不起的数组;

import java.util.Arrays;

/**
 * 数组的概念：
 * 1、首先要了解线性表的概念，线性表包括
 * （1）顺序表：一维数组、二维数组；
 * （2）链表：单链表、双链表、循环链表、静态链表；
 * （3）栈：使用链表实现的栈、使用数组实现的栈；
 * （4）队列：使用链表实现的队列、使用数组实现的队列；
 * （5）Hash：数组存储、O(1)访问；
 * 2、数组是线性表最基本的结构，特点是元素是一个紧密在一起的序列，相互之间不需要记录彼此的关系就能访问；
 * 3、数组用索引的数字来标识每项数据在数组中的位置，且在大多数编程语言中，索引是从 0 算起的；
 * 4、数组中的元素在内存中是连续存储的，且每个元素占用相同大小的内存；
 * 5、数组空间不一定是满的，100的空间可能只用了10个位置，所以要注意数据个数的变量size和数组长度length可能不一样；
 *
 * @author AntonyCheng
 * @since 2023/9/4 21:54:35
 */

public class Array {
    /**
     * 创建数组：
     * int[] arr = new int(10);
     * 初始化数组：
     * int[] arr = {...};（最重要的方式）
     * int[] arr = new int(){...};
     *
     * @param nums
     * @return 创建后/初始化后的数组
     */
    public static int[] createOrInitArray(int... nums) {
        if (nums.length == 0) {
            return new int[10];
        } else {
            int[] arr = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                arr[i] = nums[i];
            }
            return arr;
        }
    }

    /**
     * 根据元素查找索引值（出现的第一个索引值）
     *
     * @param arr
     * @param size
     * @param key
     * @return 索引值
     */
    public static int selectByElementGetFirstIndex(int[] arr, int size, int key) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 在递增数组中根据元素查找索引值（相等或大于目标值的索引值）
     *
     * @param arr
     * @param size
     * @param key
     * @return 索引值
     */
    public static int selectByElementGetBiggerOrEqualOne(int[] arr, int size, int key) {
        for (int i = 0; i < size; i++) {
            if ((arr[i] == key && arr[i + 1] >= key) || (arr[i] == key && arr[i + 1] == key)) {
                return i + 1;
            }
        }
        return -1;
    }


    /**
     * 根据元素查找索引值（出现所有索引值）
     *
     * @param arr
     * @param size
     * @param key
     * @return 索引值数组
     */
    public static int[] selectByElementGetAllIndex(int[] arr, int size, int key) {
        int[] temp = new int[size];
        int index = 0;
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (arr[i] == key) {
                temp[index] = i;
                count++;
            } else {
                temp[index] = -1;
            }
            index++;
        }
        int[] res = new int[count];
        int resIndex = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != -1) {
                res[resIndex] = temp[i];
                resIndex++;
            }
        }
        return res;
    }

    /**
     * 向升序数组中增加一个元素（先查找位置，然后移动元素，再插入元素）
     *
     * @param arr
     * @param size
     * @param element
     * @return 插入后元素的索引值
     */
    public static int addElementSequence(int[] arr, int size, int element) {
        // 问题：是否应该是size>arr.length？
        // 不应该，因为要增加一个元素，如果size和length相同的话，就没办法再往里面增加了
        if (size >= arr.length) {
            return -1;
        }
        // 问题：想想这里是否是index=0？
        // 如果index=0，当添加的元素比[0,size)的所有元素都大时，下面for循环的if判断就不会运行，该添加元素默认位置就是0
        int index = size;
        for (int i = 0; i < size; i++) {
            if (element <= arr[i]) {
                index = i;
                break;
            }
        }
        // 问题：这里为什么要从后往前替换？
        // 如果从前往后替换，例如[1,2,3,4]->[1,2,2(3),4]->[1,2,2(3),2(4)]，就会出现错误；
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = element;
        return index;
    }

    /**
     * 向升序数组中增加一个元素（边从后往前查找位置，边移动元素插入元素）
     *
     * @param arr
     * @param size
     * @param element
     * @return 插入后元素的索引值
     */
    public static int addElementSequenceUpgrade(int[] arr, int size, int element) {
        if (size >= arr.length) {
            return -1;
        }
        int index = 0;
        if (arr[size - 1] < element) {
            arr[size] = element;
            return size;
        }
        if (arr[0] > element) {
            for (int i = size; i >0; i--) {
                arr[i] = arr[i - 1];
            }
            arr[0] = element;
            return 0;
        }
        for (int i = size; i > 0; i--) {
            if (element >= arr[i]) {
                arr[i + 1] = element;
                index = i + 1;
                break;
            } else {
                arr[i] = arr[i - 1];
            }
        }
        return index;
    }

    /**
     * 删除数组中的元素（先查找位置，再移动元素）
     *
     * @param arr
     * @param size
     * @param key
     * @return 删除后元素的最大索引值
     */
    public static int deleteElement(int[] arr, int size, int key) {
        // 问题：为什么不能是0~size中的值？
        // 如果index是能够遍历到的一个索引，那当key值不存在于arr数组中时，index就会默认为0~size，那么就会引发对应索引的删除操作；
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arr[i] == key) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < size; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
        }
        return size;
    }

    public static void main(String[] args) {
        int[] ints = createOrInitArray(0, 1, 2, 2, 4, 4, 4, 5, 6);
        System.out.println(addElementSequenceUpgrade(ints, ints.length - 1, -1));
        System.out.println(Arrays.toString(ints));
    }
}
