package com.theelixirist.f112813.game.math;

import org.jetbrains.annotations.NotNull;

public class BigDouble {
    private double significand;
    private int exponent;

    public BigDouble(BigDouble other) {
        significand = other.significand;
        exponent = other.exponent;
    }

    public BigDouble(double significand, int exponent) {
        this.significand = significand;
        this.exponent = exponent;
        normalize();
    }

    public BigDouble(double value) {
        this.significand = value;
        normalize();
    }

    public BigDouble(String scientificNotation) {
        String[] parts = scientificNotation.split("e");
        this.significand = Double.parseDouble(parts[0]);
        this.exponent = Integer.parseInt(parts[1]);
        normalize();
    }

    public double getSignificand() {
        return significand;
    }

    public void setSignificand(double significand) {
        this.significand = significand;
        normalize();
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
        normalize();
    }

    public void add(BigDouble other) {
        add(other.significand, other.exponent);
    }

    public void add(double significand, int exponent) {
        if (significand == 0) { return; }

        if (this.significand == 0) {
            this.significand = significand;
            this.exponent = exponent;
            return;
        }

        int expDiff = this.exponent - exponent;

        if (expDiff >= 16) { return; }

        if (expDiff <= -16) {
            this.significand = significand;
            this.exponent = exponent;
            return;
        }

        this.significand += significand / MathUtils.pow10(expDiff);

        normalize();
    }

    public void subtract(BigDouble other) {
        add(-other.significand, other.exponent);
    }

    public void subtract(double significand, int exponent) {
        add(-significand, exponent);
    }

    public void multiply(BigDouble other) {
        multiply(other.significand, other.exponent);
    }

    public void multiply(double significand, int exponent) {
        if (significand == 0) { return; }
        if (this.significand == 0) { return; }

        this.significand *= significand;
        this.exponent += exponent;

        normalize();
    }

    public void divide(BigDouble other) {
        divide(other.significand, other.exponent);
    }

    public void divide(double significand, int exponent) {
        if (significand == 0) { return; }
        if (this.significand == 0) { return; }

        this.significand /= significand;
        this.exponent -= exponent;

        normalize();
    }

    public int compareTo(BigDouble other) {
        if (significand > 0 && other.significand <= 0) { return 1; }
        if (significand < 0 && other.significand >= 0) { return -1; }
        if (significand == 0) {
            return other.significand == 0 ? 0 : other.significand > 0 ? -1 : 1;
        }

        if (significand > 0) {
            if (exponent > other.exponent) { return 1; }
            if (exponent < other.exponent) { return -1; }
        } else {
            if (exponent > other.exponent) { return -1; }
            if (exponent < other.exponent) { return 1; }
        }
        return Double.compare(significand, other.significand);
    }

    public void normalize() {
        if (significand == 0) {
            exponent = 0;
            return;
        }

        while (Math.abs(significand) >= 10.0) {
            significand /= 10.0;
            exponent++;
        }

        while (Math.abs(significand) < 1.0 && significand != 0) {
            significand *= 10.0;
            exponent--;
        }
    }

    @NotNull
    public String toString() {
        return significand + "e" + exponent;
    }
}
