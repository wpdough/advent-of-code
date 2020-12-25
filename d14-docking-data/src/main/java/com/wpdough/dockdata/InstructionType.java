package com.wpdough.dockdata;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum InstructionType {
    UPDATE_BITMASK  ("mask"),
    WRITE_VALUE     ("mem");

    private String prefix;

    public static InstructionType getTypeFor(String input) {
        return Arrays.stream(values())
                .filter(value -> input.startsWith(value.prefix))
                .findFirst()
                .orElse(null);
    }
}
