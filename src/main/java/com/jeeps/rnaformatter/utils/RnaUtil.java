package com.jeeps.rnaformatter.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RnaUtil {
    public static String reverseComplement(String seq) {
        String complement = Arrays.stream(seq.split(""))
                .map(String::toUpperCase)
                .map(c -> {
                    switch (c) {
                        case "G": return "C";
                        case "C": return "G";
                        case "A": return "T";
                        case "T": return "A";
                        default: return "X";
                    }
                })
                .collect(Collectors.joining());
        return new StringBuilder().append(complement).reverse().toString();
    }
}
