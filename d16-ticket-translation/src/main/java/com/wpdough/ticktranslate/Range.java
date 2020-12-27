package com.wpdough.ticktranslate;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Range {
    private int min;
    private int max;

    public boolean contains(int n) {
        return min <= n && n <= max;
    }

    public static Range parse(String range) {
        String[] parts = range.split("-");
        return new Range(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }
}
