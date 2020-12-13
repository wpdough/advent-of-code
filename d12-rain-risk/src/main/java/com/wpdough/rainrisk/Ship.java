package com.wpdough.rainrisk;

import lombok.Getter;

import java.awt.*;

@Getter
public class Ship {
    private Point position = new Point();
    private Direction direction = Direction.EAST;

    public void move(Instruction instruction) {
        Action action = instruction.getAction();
        int value = instruction.getValue();
        switch (action) {
            case NORTH:
            case SOUTH:
            case EAST:
            case WEST:
                move(Direction.valueOf(action.toString()), value);
                break;
            case LEFT:
                direction = direction.rotate(-value);
                break;
            case RIGHT:
                direction = direction.rotate(value);
                break;
            case FORWARD:
                move(direction, value);
        }
    }

    public void move(Direction direction, int units) {
        switch (direction) {
            case NORTH:
                position.translate(0, units);
                break;
            case SOUTH:
                position.translate(0, -units);
                break;
            case EAST:
                position.translate(units, 0);
                break;
            case WEST:
                position.translate(-units, 0);
                break;
        }
    }
}
