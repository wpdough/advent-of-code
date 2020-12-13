package com.wpdough.seatsys;

import java.util.List;

public class SeatingChart {
    private int rows, columns;
    private SpaceState[][] grid;

    public SeatingChart(List<String> input) {
        rows = input.size();
        columns = input.get(0).length();
        grid = new SpaceState[rows][columns];

        for (int row = 0; row < rows; row++) {
            char[] rowCells = input.get(row).toCharArray();
            for (int col = 0; col < columns; col++) {
                grid[row][col] = SpaceState.getStateForChar(rowCells[col]);
            }
        }
    }

    public SpaceState getState(int x, int y) {
        return grid[y][x];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void print() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                System.out.print(grid[y][x].getSymbol());
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setState(int x, int y, SpaceState state) {
        grid[y][x] = state;
    }

    public int countOccupiedAdj(int x, int y) {
        int occupied = 0;
        for (Direction dir : Direction.values()) {
            if (seatOccupied(x, y, dir)) {
                occupied++;
            }
        }
        return occupied;
    }

    public boolean seatOccupied(int x, int y, Direction dir) {
        int translatedX = dir.getTranslateX() + x;
        int translatedY = dir.getTranslateY() + y;
        if (valid(translatedX, translatedY) && getState(translatedX, translatedY).equals(SpaceState.OCCUPIED)) {
            return true;
        }
        return false;
    }

    private boolean valid(int x, int y) {
        return y >= 0 && y < rows && x >=0 && x < columns;
    }

    public int countOccupiedSeats() {
        int count = 0;
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                if (getState(x, y).equals(SpaceState.OCCUPIED)) {
                    count++;
                }
            }
        }
        return count;
    }
}
