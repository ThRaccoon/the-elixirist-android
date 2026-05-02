package com.theelixirist.f112813.game.math;

public class MathUtils {
    private static final int MAX_PRECOMPUTED_POWER_OF_TEN = 92;
    private static final double[] PRECOMPUTED_POWERS_OF_TEN =
            new double[MAX_PRECOMPUTED_POWER_OF_TEN + 1];

    static {
        for (int i = 0; i <= MAX_PRECOMPUTED_POWER_OF_TEN; i++) {
            PRECOMPUTED_POWERS_OF_TEN[i] = Math.pow(10, i);
        }
    }

    public static double pow10(long exponent) {
        if (exponent < 0 || exponent > 92) {
            return Math.pow(10, exponent);
        }

        return PRECOMPUTED_POWERS_OF_TEN[(int) exponent];
    }
}
