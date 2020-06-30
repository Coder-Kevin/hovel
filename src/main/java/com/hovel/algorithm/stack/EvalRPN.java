package com.hovel.algorithm.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author Kevin
 * <p>
 * 逆波兰表达式
 * <p>
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        Stack stack = new Stack();
        List<String> symbols = Arrays.asList("+", "*", "-", "/");

        for (String keyword : tokens) {
            if (!symbols.contains(keyword)) {
                stack.push(Integer.valueOf(keyword));
            } else {
                int b = (int) stack.pop();
                int a = (int) stack.pop();
                int result = deal(a, b, keyword);
                stack.push(result);
            }
        }

        return (int) stack.pop();
    }

    private int deal(int a, int b, String symbol) {

        int result = 0;
        switch (symbol) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                break;
        }

        return result;
    }

}
