package com.jeeps.rnaformatter.model;

public class TargetSite {
    private static final String FULL_SEGMENT = "aattaatacgactcactata%sgttttagagctagaaatagc";

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
