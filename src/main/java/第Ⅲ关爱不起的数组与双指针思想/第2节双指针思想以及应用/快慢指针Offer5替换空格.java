package 第Ⅲ关爱不起的数组与双指针思想.第2节双指针思想以及应用;

/**
 * <a href="https://leetcode.cn/problems/ti-huan-kong-ge-lcof/">替换空格</a>
 *
 * @author AntonyCheng
 * @since 2023/9/11 01:50:47
 */

public class 快慢指针Offer5替换空格 {
    /**
     * String.replace()法
     * 顾名思义
     *
     * @param s
     * @return
     */
    public String replaceSpaceMethod1(String s) {
        return s.replace(" ", "%20");
    }

    /**
     * 重构字符串法
     * 时间复杂度大
     *
     * @param s
     * @return
     */
    public String replaceSpaceMethod2(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                res += "%20";
            } else {
                res += c;
            }
        }
        return res;
    }

    /**
     * 快慢指针法
     * 首先弄清楚返回字符串的长度，即“原长度+2*空格个数”，
     * 所以我们先要知道空格的个数，
     * 然后快指针指向返回字符串的头部，慢指针指向原字符串的头部，
     * 当慢指针遇到空格时，快指针向字符串中插入"%20"即可；
     *
     * @param s
     * @return
     */
    public String replaceSpaceMethod3(String s) {
        int blankCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (' ' == s.charAt(i)) {
                blankCount++;
            }
        }
        char[] chars = new char[s.length() + 2 * blankCount];
        int slow = 0;
        int fast = 0;
        int slowIndex = 0;
        while (slowIndex != s.length()) {
            char c = s.charAt(slow);
            if (c == ' ') {
                chars[fast] = '%';
                fast++;
                chars[fast] = '2';
                fast++;
                chars[fast] = '0';
            } else {
                chars[fast] = c;
            }
            fast++;
            slow++;
            slowIndex++;
        }
        return new String(chars);
    }
}
