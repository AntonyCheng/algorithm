package 第Ⅳ关站不住的栈.第2节栈的经典算法问题;

import java.util.HashMap;
import java.util.Stack;

/**
 * <a href="https://leetcode.cn/problems/valid-parentheses/description/">有效的括号</a>
 *
 * @author AntonyCheng
 * @since 2023/9/11 14:41:34
 */

public class 经典例题Leetcode20有效的括号 {
    /**
     * 哈希映射和栈相结合的方法
     * 本题理解来说是比较简单的，难点就是如何判断两个符号是不是一组，例如“(())”，如何判断第二个左括号匹配的第一个右括号；
     * 我们可以用哈希表将所有符号先存储，左半边做key,右半边做value。
     * 遍历字符串的时候，遇到左半边符号就入栈，遇到右半边符号就与栈顶的符号比较，不匹配就返回false
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length() <= 1) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (!stack.isEmpty()) {
                    Character stackChar = stack.pop();
                    Character mapValueChar = map.get(stackChar);
                    if (s.charAt(i) != mapValueChar) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
