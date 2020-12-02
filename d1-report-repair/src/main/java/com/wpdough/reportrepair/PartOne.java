package com.wpdough.reportrepair;

import java.util.List;

public class PartOne {

    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        List<Integer> expenseReport = FileUtil.readIntegers(fileName);
        for (int i = 0; i < expenseReport.size(); i++) {
            for (int j = 0; j < expenseReport.size(); j++) {
                int a = expenseReport.get(i);
                int b = expenseReport.get(j);
                if ((a + b) == 2020) {
                    System.out.println(a);
                    System.out.println(b);
                    System.out.println(a * b);
                    return;
                }
            }
        }
    }
}
