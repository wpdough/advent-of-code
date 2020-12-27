package com.wpdough.ticktranslate;

import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class Document {
    private List<TicketField> ticketFields;
    private List<Integer> ticket;
    private List<List<Integer>> nearbyTickets;

    public static Document parse(List<String> input) {
        List<List<String>> partitions = new ArrayList<>();
        List<String> partition = new ArrayList<>();
        for (String s : input) {
            if (s.isEmpty()) {
                partitions.add(partition);
                partition = new ArrayList<>();
            } else {
                partition.add(s);
            }
        }
        partitions.add(partition);

        List<TicketField> ticketFields = new ArrayList<>();
        for (String field : partitions.get(0)) {
            ticketFields.add(TicketField.parse(field));
        }

        List<Integer> ticket = Arrays.stream(partitions.get(1).get(1).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<String> nearbyTicketsPartition = partitions.get(2);
        List<List<Integer>> nearbyTickets = nearbyTicketsPartition.subList(1, nearbyTicketsPartition.size()).stream()
                .map(numList -> Arrays.stream(numList.split(",")).map(Integer::parseInt).collect(Collectors.toList()))
                .collect(Collectors.toList());

        return new Document(ticketFields, ticket, nearbyTickets);
    }
}