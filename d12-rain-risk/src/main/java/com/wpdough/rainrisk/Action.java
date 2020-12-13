package com.wpdough.rainrisk;

import java.util.Arrays;

public enum Action {
    NORTH   ('N'),
    SOUTH   ('S'),
    EAST    ('E'),
    WEST    ('W'),
    LEFT    ('L'),
    RIGHT   ('R'),
    FORWARD ('F');

    private char symbol;

    Action(char symbol) {
        this.symbol = symbol;
    }

    public static Action fromSymbol(char charAt) {
        return Arrays.stream(Action.values())
                .filter(a -> a.getSymbol() == charAt)
                .findFirst()
                .get();
    }

    public char getSymbol() {
        return symbol;
    }
}
