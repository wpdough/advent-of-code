package com.wpdough.ticktranslate;

import com.wpdough.aoc.AocProblem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketTranslation extends AocProblem<Document> {

    @Override
    public Document parseInput(List<String> input) {
        return Document.parse(input);
    }

    @Override
    public long partOne(Document document) {
        List<Integer> invalidValues = new ArrayList<>();

        for (List<Integer> nearbyTicket : document.getNearbyTickets()) {
            for (Integer value : nearbyTicket) {
                if (document.getTicketFields().stream().noneMatch(field -> field.rangesContain(value))) {
                    invalidValues.add(value);
                }
            }
        }

        return invalidValues.stream().mapToInt(x -> x).sum();
    }

    @Override
    public long partTwo(Document input) {
        return 0;
    }

    public static void main(String[] args) throws IOException {
        new TicketTranslation().solve(args[0]);
    }
}
