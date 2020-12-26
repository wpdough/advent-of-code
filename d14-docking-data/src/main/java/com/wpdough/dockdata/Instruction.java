package com.wpdough.dockdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Instruction {
    private InstructionType type;
    private String bitmask;
    private long address;
    private long value;

    public static Instruction parse(String input) {
        InstructionType type = InstructionType.getTypeFor(input);
        if (type.equals(InstructionType.UPDATE_BITMASK)) {
            String rest = input.replace("mask = ", "");
            return new Instruction(type, rest, -1, -1);
        } else if (type.equals(InstructionType.WRITE_VALUE)) {
            String addressStr = input.substring(input.indexOf("[") + 1, input.indexOf("]"));
            String valueStr = input.replace("mem[" + addressStr + "] = ", "");
            return new Instruction(type, null, Long.parseLong(addressStr), Long.parseLong(valueStr));
        }
        return null;
    }
}
