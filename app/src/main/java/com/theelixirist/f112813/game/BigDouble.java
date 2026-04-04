package com.theelixirist.f112813.game;

public class BigDouble {
    private double mantissa;
    private long exponent;

    public BigDouble(double mantissa, long exponent) {
        this.mantissa = mantissa;
        this.exponent = exponent;

        normalize();
    }

    public BigDouble(BigDouble other) {
        mantissa = other.mantissa;
        exponent = other.exponent;
    }

    public void normalize() {
        if (mantissa == 0) {
            exponent = 0;
            return;
        }

        long shift = (long) Math.floor(Math.log10(Math.abs(mantissa)));

        if (shift != 0) {
            exponent += shift;
            mantissa /= Math.pow(10, shift);
        }

        while (Math.abs(mantissa) < 1.0 && mantissa != 0) {
            mantissa *= 10.0;
            exponent--;
        }

        while (Math.abs(mantissa) >= 10.0) {
            mantissa /= 10.0;
            exponent++;
        }
    }
}
