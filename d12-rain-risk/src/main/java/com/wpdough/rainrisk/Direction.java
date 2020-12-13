package com.wpdough.rainrisk;

import java.util.Arrays;

public enum Direction {
    EAST    (0),
    SOUTH   (90),
    WEST    (180),
    NORTH   (270);

    private int degrees;

    Direction(int degrees) {
        this.degrees = degrees;
    }

    public int getDegrees() {
        return degrees;
    }

    public Direction rotate(int degrees) {
        if (degrees % 90 != 0)
            throw new IllegalArgumentException("direction can only be " +
                    "rotated by 90 degree increments");

        // normalize degrees
        degrees += getDegrees();
        degrees = degrees < 0 ? degrees + 360 : degrees;
        degrees = degrees >= 270 ? degrees % 360 : degrees;

        return fromDegrees(degrees);
    }

    public static Direction fromDegrees(int degrees) {
        return Arrays.stream(Direction.values())
                .filter(d -> d.getDegrees() == degrees)
                .findFirst()
                .get();
    }
}
