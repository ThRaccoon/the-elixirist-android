package com.theelixirist.f112813.game.math;

import java.util.Locale;

public class BigDoubleFormatter {
    private static final String[] SUFFIXES = new String[]{
            "K", "M", "B", "T", "Qa", "Qi", "Sx", "Sp", "Oc", "No",
            "Dc", "Ud", "Dd", "Td", "Qad", "Qid", "Sxd", "Spd", "Od", "Nd",
            "V", "Uv", "Dv", "Tv", "Qav", "Qiv", "Sxv", "Spv", "Ov", "Nv",
    };

    public static String format(BigDouble number) {
        if (number.getExponent() < 0) {
            return String.format(
                    Locale.US,
                    "%.2fe%d",
                    number.getSignificand(),
                    number.getExponent()
            );
        }

        if (number.getExponent() < 3) {
            double rawValue = number.getSignificand() * MathUtils.pow10(number.getExponent());
            return String.format(
                    Locale.US,
                    "%.0f",
                    Math.floor(rawValue)
            );
        }

        int suffixIndex = (int) number.getExponent() / 3;
        if (suffixIndex > SUFFIXES.length) {
            return String.format(
                    Locale.US,
                    "%.2fe%d",
                    number.getSignificand(),
                    number.getExponent()
            );
        }

        double scaledSignificand =
                number.getSignificand() * MathUtils.pow10(number.getExponent() % 3);

        return String.format(
                Locale.US,
                "%.2f%s",
                scaledSignificand,
                SUFFIXES[suffixIndex - 1]
        );
    }
}
