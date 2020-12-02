package com.wpdough.reportrepair;

import java.util.List;

public class PartTwo {

    public static void main(String[] args) throws Exception {
        String fileName = args[0];
        List<Integer> expenseReport = FileUtil.readIntegers(fileName);
        for (int i = 0; i < expenseReport.size(); i++) {
            for (int j = 0; j < expenseReport.size(); j++) {
                for (int k = 0; k < expenseReport.size(); k++) {
                    int a = expenseReport.get(i);
                    int b = expenseReport.get(j);
                    int c = expenseReport.get(k);
                    if ((a + b + c) == 2020) {
                        System.out.println(a);
                        System.out.println(b);
                        System.out.println(c);
                        System.out.println(a * b * c);
                        return;
                    }
                }
            }
        }
    }
}
