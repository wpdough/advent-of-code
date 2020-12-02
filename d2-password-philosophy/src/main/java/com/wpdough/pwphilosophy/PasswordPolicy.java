package com.wpdough.pwphilosophy;

public class PasswordPolicy {
    private int a;
    private int b;
    private char letter;

    public PasswordPolicy(int a, int b, char letter) {
        this.a = a;
        this.b = b;
        this.letter = letter;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public char getLetter() {
        return letter;
    }

    public static PasswordPolicy parseString(String str) {
        String[] parts = str.split(" ");
        String[] minMaxParts = parts[0].split("-");
        int min = Integer.parseInt(minMaxParts[0]);
        int max = Integer.parseInt(minMaxParts[1]);
        char letter = parts[1].charAt(0);
        return new PasswordPolicy(min, max, letter);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PasswordPolicy{");
        sb.append("a=").append(a);
        sb.append(", b=").append(b);
        sb.append(", letter=").append(letter);
        sb.append('}');
        return sb.toString();
    }
}
