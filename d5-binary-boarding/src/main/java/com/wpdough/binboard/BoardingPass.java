package com.wpdough.binboard;

import java.util.Objects;

public class BoardingPass {
    private int row;
    private int column;
    private int seatId;

    public BoardingPass(int seatId) {
        this.row = (seatId - seatId % 8) / 8;
        this.column = seatId % 8;
        this.seatId = seatId;
    }

    public BoardingPass(int row, int column) {
        this.row = row;
        this.column = column;
        this.seatId = generateSeatId(row, column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSeatId() {
        return seatId;
    }

    public static BoardingPass parseString(String passStr) {
        String rowStr = passStr.substring(0, 7)
                .replace("F", "0")
                .replace("B", "1");
        String colStr = passStr.substring(7, 10)
                .replace("L", "0")
                .replace("R", "1");
        int row = parseBinary(rowStr);
        int col = parseBinary(colStr);
        return new BoardingPass(row, col);
    }

    private static int parseBinary(String binaryStr) {
        return Integer.parseInt(binaryStr,2);
    }

    private static int generateSeatId(int row, int column) {
        return row * 8 + column;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BoardingPass{");
        sb.append("row=").append(row);
        sb.append(", column=").append(column);
        sb.append(", seatId=").append(seatId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardingPass that = (BoardingPass) o;
        return seatId == that.seatId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, seatId);
    }
}
