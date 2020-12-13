package com.wpdough.shuttlesearch;

import com.wpdough.aoc.AocProblem;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ShuttleSearch extends AocProblem<Notes> {

    @Override
    public Notes parseInput(List<String> input) {
        return Notes.parse(input);
    }

    @Override
    public long partOne(Notes input) {
        final AtomicInteger time = new AtomicInteger(input.getEarliestDepartureTime());
        int busIdBoarded = -1;
        int minutesWaited = -1;
        while (busIdBoarded == -1) {
            Optional<Integer> bus = input.getBusIds().stream()
                    .filter(i -> time.get()%i == 0)
                    .findFirst();
            if (bus.isPresent()) {
                busIdBoarded = bus.get();
                minutesWaited = time.get() - input.getEarliestDepartureTime();
                break;
            }
            time.getAndIncrement();
        }

        return busIdBoarded * minutesWaited;
    }

    @Override
    public long partTwo(Notes input) {
        return 0;
    }

    public static void main(String[] args) throws IOException {
        new ShuttleSearch().solve(args[0]);
    }
}
