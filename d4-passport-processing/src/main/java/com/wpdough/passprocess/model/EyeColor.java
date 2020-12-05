package com.wpdough.passprocess.model;

public enum EyeColor {
    AMBER   ("amb"),
    BLUE    ("blu"),
    BROWN   ("brn"),
    GRAY    ("gry"),
    GREEN   ("grn"),
    HAZEL   ("hzl"),
    OTHER   ("oth");

    private String abbreviation;

    EyeColor(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
