package com.wpdough.handyhavs;

import java.util.*;

public class Bag {
    private static Set<Bag> BAG_REGISTRY = new HashSet<>();

    private String color;
    private Map<String, Integer> contents;

    public Bag(String color, Map<String, Integer> contents) {
        this.color = color;
        this.contents = contents;

        BAG_REGISTRY.add(this);
    }

    public String getColor() {
        return color;
    }

    public Map<String, Integer> getContents() {
        return contents;
    }

    public int bagChildrenCount() {
        if (contents.isEmpty())
            return 0;
        int bagsInside = contents.values().stream().mapToInt(i -> i).sum();
        int childrensBags = contents.keySet().stream()
                .mapToInt(bagColor -> {
                    int countOfThisBagType = contents.get(bagColor);
                    Bag bag = findBagForColor(bagColor);
                    return countOfThisBagType * bag.bagChildrenCount();
                })
                .sum();
        return bagsInside + childrensBags;
    }

    public boolean canContain(String color) {
        if (contents.isEmpty())
            return false;
        if (contents.keySet().contains(color))
            return true;
        return contents.keySet().stream()
                .map(Bag::findBagForColor)
                .anyMatch(bag -> bag.canContain(color));
    }

    private static Bag findBagForColor(String color) {
        return BAG_REGISTRY.stream().filter(bag -> bag.getColor().equals(color)).findFirst().get();
    }

    public static Bag fromString(String line) {
        String color = line.substring(0, line.indexOf(" bags contain "));
        line = line.substring(line.indexOf(" bags contain ") + " bags contain ".length());
        Map<String, Integer> contents = parseContents(line);
        return new Bag(color, contents);
    }

    private static Map<String, Integer> parseContents(String line) {
        line = line.replace(" bags", "")
                .replace(" bag", "")
                .replace(".", "");

        Map<String, Integer> contentMap = new HashMap<>();
        if (line.equals("no other")) {
            return contentMap;
        }

        List<String> contents = Arrays.asList(line.split(", "));
        for (String bag : contents) {
            int numEndIndex = bag.indexOf(" ");
            int num = Integer.parseInt(bag.substring(0, numEndIndex));
            String bagColor = bag.substring(numEndIndex).trim();
            contentMap.put(bagColor, num);
        }

        return contentMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bag bag = (Bag) o;
        return Objects.equals(color, bag.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Bag{");
        sb.append("color='").append(color).append('\'');
        sb.append(", contents=").append(contents);
        sb.append('}');
        return sb.toString();
    }
}
