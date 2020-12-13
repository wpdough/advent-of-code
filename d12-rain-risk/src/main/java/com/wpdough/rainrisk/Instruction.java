package com.wpdough.rainrisk;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Instruction {
    private Action action;
    private int value;

    public static Instruction parse(String str) {
        Action action = Action.fromSymbol(str.charAt(0));
        int value = Integer.parseInt(str.substring(1));
        return new Instruction(action, value);
    }
}
