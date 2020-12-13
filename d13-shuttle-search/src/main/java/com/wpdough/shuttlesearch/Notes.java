package com.wpdough.shuttlesearch;

import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class Notes {
    private int earliestDepartureTime;
    private List<Integer> busIds;

    public static Notes parse(List<String> lines) {
        int earliestDepartureTime = Integer.parseInt(lines.get(0));
        List<Integer> busIds = Arrays.stream(lines.get(1).split(","))
                .filter(str -> !str.equals("x"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new Notes(earliestDepartureTime, busIds);
    }
}
