package com.wpdough.seatsys;

import java.util.Arrays;

public enum SpaceState {
    FLOOR       ('.'),
    EMPTY       ('L'),
    OCCUPIED    ('#');

    private char symbol;

    SpaceState(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static SpaceState getStateForChar(char c) {
        return Arrays.stream(SpaceState.values())
                .filter(ss -> ss.symbol == c)
                .findFirst()
                .orElse(null);
    }
}
