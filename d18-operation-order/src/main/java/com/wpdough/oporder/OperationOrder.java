package com.wpdough.oporder;

import com.wpdough.aoc.AocProblem;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OperationOrder extends AocProblem<List<String>> {

    @Override
    public List<String> parseInput(List<String> input) {
        return input;
    }

    @Override
    public long partOne(List<String> input) {
        return input.stream()
                .mapToLong(OperationOrder::evaluate)
                .sum();
    }

    @Override
    public long partTwo(List<String> input) {
        return 0;
    }

    public static long evaluate(String expression) {
        if (!expression.contains("(")) {
            return evaluateWithoutParenthesis(expression);
        }

        String parenthesisExpression = findExpressionWithParenthesis(expression);
        long evaluated;
        if (parenthesisExpression.startsWith("(") && parenthesisExpression.endsWith(")")) {
            evaluated = evaluate(parenthesisExpression.substring(1, parenthesisExpression.length() - 1));
        } else {
            evaluated = evaluate(parenthesisExpression);
        }

        return evaluate(expression.replace(parenthesisExpression, String.valueOf(evaluated)));
    }

    public static String findExpressionWithParenthesis(String expression) {
        int start = expression.indexOf("(");
        int nOpeningParenthesis = 0;
        int nClosingParenthesis = 0;
        for (int i = start; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(')
                nOpeningParenthesis++;
            if (c == ')')
                nClosingParenthesis++;

            if (nOpeningParenthesis == nClosingParenthesis)
                return expression.substring(start, i + 1);
        }

        return null;
    }

    public static long evaluateWithoutParenthesis(String expression) {
        String[] parts = expression.split(" ");
        if (parts.length == 1)
            return Long.parseLong(parts[0]);

        long a = Long.parseLong(parts[0]);
        boolean isAddition = parts[1].equals("+");
        long b = Long.parseLong(parts[2]);
        long result = isAddition ? a + b : a * b;

        if (parts.length == 3)
            return result;

        parts = Arrays.copyOfRange(parts, 3, parts.length);
        return evaluateWithoutParenthesis(result + " " + Arrays.stream(parts).collect(Collectors.joining(" ")));
    }

    public static void main(String[] args) throws IOException {
        new OperationOrder().solve(args[0]);
    }
}
