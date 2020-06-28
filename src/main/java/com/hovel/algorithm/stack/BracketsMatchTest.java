package com.hovel.algorithm.stack;

import java.util.Stack;

public class BracketsMatchTest {

    public static void main(String[] args) {
        String content = "(上海))";
        System.out.println(isMatchBrackets(content));

    }

    private static boolean isMatchBrackets(String content) {
        // 构造栈结构，存储括号内容
        Stack<String> stack = new Stack<>();

        // 遍历字符串
        for (int i = 0; i < content.length(); i++) {
            String s = String.valueOf(content.charAt(i));
            // 如果是左括号就压栈
            if ("(".equals(s)) {
                stack.push(s);
            } else if (")".equals(s)) {
                // 否则就出栈
                if (stack.size() > 0) {
                    stack.pop();
                } else {
                    return false;
                }
            }

        }

        if (stack.size() > 0) {
            return false;
        }

        return true;
    }


}
