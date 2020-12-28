package com.wpdough.ticktranslate;

import com.wpdough.aoc.AocProblem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public long partTwo(Document document) {
        List<List<Integer>> validNearbyTickets = findValidNearbyTickets(document);

        Map<Integer, List<TicketField>> valueIndexToValidTicketFields = new HashMap<>();
        for (int i = 0; i < document.getTicket().size(); i++) {
            valueIndexToValidTicketFields.put(i, new ArrayList<>(document.getTicketFields()));
        }

        for (List<Integer> nearbyTicket : validNearbyTickets) {
            for (int i = 0; i < nearbyTicket.size(); i++) {
                Integer value = nearbyTicket.get(i);
                List<TicketField> matchingFields = valueIndexToValidTicketFields.get(i).stream()
                        .filter(ticketField -> ticketField.rangesContain(value))
                        .collect(Collectors.toList());
                valueIndexToValidTicketFields.put(i, matchingFields);
            }
        }

        elliminate(valueIndexToValidTicketFields);

        List<TicketField> correctFieldOrder = new ArrayList<>();
        for (int i = 0; i < document.getTicket().size(); i++) {
            correctFieldOrder.add(valueIndexToValidTicketFields.get(i).get(0));
        }

        List<Integer> valuesWithFieldNameStartingWithDeparture = new ArrayList<>();
        for (int i = 0; i < correctFieldOrder.size(); i++) {
            if (correctFieldOrder.get(i).getName().startsWith("departure")) {
                valuesWithFieldNameStartingWithDeparture.add(document.getTicket().get(i));
            }
        }

        return valuesWithFieldNameStartingWithDeparture.stream()
                .mapToLong(x -> x)
                .reduce(1, (a, b) -> a * b);
    }

    private static void elliminate(Map<Integer, List<TicketField>> indexToValidFields) {
        if (indexToValidFields.values().stream().allMatch(fields -> fields.size() == 1))
            return;

        // find ticket field with 1 possibility and present in other fields
        // remove that field from all the rest

        int index = indexToValidFields.keySet().stream()
                .filter(i -> indexToValidFields.get(i).size() == 1 && otherFieldsContain(indexToValidFields, i))
                .findFirst()
                .get();
        TicketField value = indexToValidFields.get(index).get(0);
        indexToValidFields.values().stream()
                .filter(fields -> fields.size() > 1)
                .forEach(fields -> fields.remove(value));
        elliminate(indexToValidFields);
    }

    private static boolean otherFieldsContain(Map<Integer, List<TicketField>> indexToValidFields, int index) {
        TicketField value = indexToValidFields.get(index).get(0);
        long count = indexToValidFields.values().stream()
                .filter(fields -> fields.contains(value))
                .count();
        return count > 1;
    }

    private static List<List<Integer>> findValidNearbyTickets(Document document) {
        List<List<Integer>> validNearbyTickets = new ArrayList<>(document.getNearbyTickets());
        for (List<Integer> nearbyTicket : document.getNearbyTickets()) {
            for (Integer value : nearbyTicket) {
                if (document.getTicketFields().stream().noneMatch(field -> field.rangesContain(value))) {
                    validNearbyTickets.remove(nearbyTicket);
                    break;
                }
            }
        }
        return validNearbyTickets;
    }

    public static void main(String[] args) throws IOException {
        new TicketTranslation().solve(args[0]);
    }
}
