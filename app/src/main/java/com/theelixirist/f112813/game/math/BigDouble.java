package com.theelixirist.f112813.game.math;

public class BigDouble {
    private double mantissa;
    private long exponent;

    public BigDouble(BigDouble other) {
        mantissa = other.mantissa;
        exponent = other.exponent;
    }

    public BigDouble(double mantissa, long exponent) {
        this.mantissa = mantissa;
        this.exponent = exponent;
        normalize();
    }

    public void add(BigDouble other) {
        add(other.mantissa, other.exponent);
    }

    public void add(double mantissa, long exponent) {
        if (mantissa == 0) { return; }

        if (this.mantissa == 0) {
            this.mantissa = mantissa;
            this.exponent = exponent;
            return;
        }

        long expDiff = this.exponent - exponent;

        if (expDiff >= 16) { return; }

        if (expDiff <= -16) {
            this.mantissa = mantissa;
            this.exponent = exponent;
            return;
        }

        this.mantissa += mantissa / Math.pow(10, expDiff);

        normalize();
    }

    public void subtract(BigDouble other) {
        add(-other.mantissa, other.exponent);
    }

    public void subtract(double mantissa, long exponent) {
        add(-mantissa, exponent);
    }

    public void multiply(BigDouble other) {
        multiply(other.mantissa, other.exponent);
    }

    public void multiply(double mantissa, long exponent) {
        if (mantissa == 0) { return; }
        if (this.mantissa == 0) { return; }

        this.mantissa *= mantissa;
        this.exponent += exponent;

        normalize();
    }

    public void divide(BigDouble other) {
        divide(other.mantissa, other.exponent);
    }

    public void divide(double mantissa, long exponent) {
        if (mantissa == 0) { return; }
        if (this.mantissa == 0) { return; }

        this.mantissa /= mantissa;
        this.exponent -= exponent;

        normalize();
    }

    public int compareTo(BigDouble other) {
        if (mantissa > 0 && other.mantissa <= 0) { return 1; }
        if (mantissa < 0 && other.mantissa >= 0) { return -1; }
        if (mantissa == 0) { return other.mantissa == 0 ? 0 : other.mantissa > 0 ? -1 : 1; }

        if (mantissa > 0) {
            if (exponent > other.exponent) { return 1; }
            if (exponent < other.exponent) { return -1; }
        } else {
            if (exponent > other.exponent) { return -1; }
            if (exponent < other.exponent) { return 1; }
        }
        return Double.compare(mantissa, other.mantissa);
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

    public double getMantissa() {
        return mantissa;
    }

    public void setMantissa(double mantissa) {
        this.mantissa = mantissa;
        normalize();
    }

    public long getExponent() {
        return exponent;
    }

    public void setExponent(long exponent) {
        this.exponent = exponent;
        normalize();
    }
}
