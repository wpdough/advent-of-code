package com.wpdough.ticktranslate;

import lombok.Value;

@Value
public class TicketField {
    private String name;
    private Range rangeA;
    private Range rangeB;

    public static TicketField parse(String field) {
        String[] parts = field.split(": ");
        String name = parts[0];
        String[] rangeParts = parts[1].split(" or ");
        Range rangeA = Range.parse(rangeParts[0]);
        Range rangeB = Range.parse(rangeParts[1]);
        return new TicketField(name, rangeA, rangeB);
    }

    public boolean rangesContain(int n) {
        return rangeA.contains(n) || rangeB.contains(n);
    }
}
