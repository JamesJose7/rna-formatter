package com.jeeps.rnaformatter.model;

public class TargetSite {
    public static final String FIRST_WRAP = "aattaatacgactcactata";
    public static final String SECOND_WRAP = "gttttagagctagaaatagc";
    private static final String FULL_SEGMENT = FIRST_WRAP + "%s" + SECOND_WRAP;
    public static final int TYPE_NGG = 0;
    public static final int TYPE_CCN = 1;

    private String name;
    private String rnaSegment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRnaSegment() {
        return rnaSegment;
    }

    public void setRnaSegment(String rnaSegment) {
        this.rnaSegment = rnaSegment;
    }

    public static String wrapSegment(String rnaSegment) {
        return String.format(FULL_SEGMENT, rnaSegment);
    }
}
