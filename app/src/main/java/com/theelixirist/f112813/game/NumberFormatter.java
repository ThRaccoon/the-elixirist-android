package com.theelixirist.f112813.game;

import java.util.Locale;

public class NumberFormatter {
    private static final String[] suffixes = new String[]{"K", "M", "B", "T", "Qa", "Qi", "Sx", "Sp", "Oc", "No",
            "Dc", "Ud", "Dd", "Td", "Qad", "Qid", "Sxd", "Spd", "Ocd", "Nod", "Vg", "Uvg"};

    public static String format(double number) {
        if (number < 1000) {
            if (number % 1 == 0) {
                return String.valueOf((long) number);
            } else {
                return String.format(Locale.US, "%.2f", number);
            }
        }

        int exp = (int) (Math.log10(number) / 3);
        if (exp > suffixes.length) {
            exp = suffixes.length;
        }

        double shortValue = number / Math.pow(1000, exp);

        return String.format(Locale.US, "%.2f%s", shortValue, suffixes[exp - 1]);
    }
}
