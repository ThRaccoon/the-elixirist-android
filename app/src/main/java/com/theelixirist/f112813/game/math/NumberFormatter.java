package com.theelixirist.f112813.game.math;

import java.util.Locale;

public class NumberFormatter {
    private static final String[] suffixes = new String[]{"K", "M", "B", "T", "Qa", "Qi", "Sx", "Sp", "Oc", "No",
            "Dc", "Ud", "Dd", "Td", "Qad", "Qid", "Sxd", "Spd", "Ocd", "Nod", "Vg", "Uvg"};

    public static String format(BigDouble number) {
        if (number.getExponent() < 3) {
            double rawValue = number.getMantissa() * Math.pow(10, number.getExponent());

            if (Math.abs(Math.round(rawValue) - rawValue) < 1e-9) {
                return String.valueOf((long) rawValue);
            } else {
                return String.format(Locale.US, "%.2f", rawValue);
            }
        }

        int suffixIndex = (int) (number.getExponent() / 3);
        if (suffixIndex > suffixes.length) {
            suffixIndex = suffixes.length;
        }

        double displayMantissa = number.getMantissa() * Math.pow(10, number.getExponent() % 3);

        return String.format(Locale.US, "%.2f%s", displayMantissa, suffixes[suffixIndex - 1]);
    }
}
