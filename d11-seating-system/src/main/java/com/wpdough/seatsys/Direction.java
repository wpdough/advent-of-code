package com.wpdough.seatsys;

import java.awt.*;

public enum Direction {
    NORTH       (0, -1),
    NORTH_EAST  (1, -1),
    EAST        (1, 0),
    SOUTH_EAST  (1, 1),
    SOUTH       (0, 1),
    SOUTH_WEST  (-1, 1),
    WEST        (-1, 0),
    NORTH_WEST  (-1, -1);

    private int translateX;
    private int translateY;

    Direction(int translateX, int translateY) {
        this.translateX = translateX;
        this.translateY = translateY;
    }

    public int getTranslateX() {
        return translateX;
    }

    public int getTranslateY() {
        return translateY;
    }
}
