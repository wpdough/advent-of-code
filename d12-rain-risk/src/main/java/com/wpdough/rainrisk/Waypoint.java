package com.wpdough.rainrisk;

import java.awt.*;

public class Waypoint {
    private Point positionRelative = new Point(10, 1);

    public Point getActualPosition(Ship ship) {
        Point pos = new Point(ship.getPosition());
        pos.translate(positionRelative.x, positionRelative.y);
        return pos;
    }

    public Point getPositionRelative() {
        return positionRelative;
    }

    public void move(Direction direction, int units) {
        switch (direction) {
            case NORTH:
                positionRelative.translate(0, units);
                break;
            case SOUTH:
                positionRelative.translate(0, -units);
                break;
            case EAST:
                positionRelative.translate(units, 0);
                break;
            case WEST:
                positionRelative.translate(-units, 0);
                break;
        }
    }

    public void rotate(int degrees) {
        if (Math.abs(degrees) == 180) {
            positionRelative.setLocation(positionRelative.x * -1, positionRelative.y * -1);
        } else if (degrees == -90 || degrees == 270) {
            positionRelative.setLocation(positionRelative.y * -1, positionRelative.x);
        } else if (degrees == 90 || degrees == -270) {
            positionRelative.setLocation(positionRelative.y, positionRelative.x * -1);
        }
    }
}
