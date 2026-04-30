/*
    All tests follow the format:
    [method]_[scenario]_[result]
*/

package com.theelixirist.f112813;

import static org.junit.Assert.assertEquals;

import com.theelixirist.f112813.game.math.BigDouble;

import org.junit.Test;

public class BigDoubleTest {
    private static final double EPSILON = 1e-9;

    /// /////// BigDouble(String scientificNotation) //////////

    @Test
    public void BigDouble_control_inputConvertedToBigDouble() {
        BigDouble a = new BigDouble("1.2e3");

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(3, a.getExponent());
    }


    /// /////// add(BigDouble other), add(double significand, int exponent) //////////

    @Test
    public void add_otherSigIsZero_thisUnchanged() {
        BigDouble a = new BigDouble(1.2, 3);
        a.add(0, 0);

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(3, a.getExponent());
    }

    @Test
    public void add_thisSigIsZero_thisBecomesOther() {
        BigDouble a = new BigDouble(0, 0);
        a.add(4.5, 6);

        assertEquals(4.5, a.getSignificand(), EPSILON);
        assertEquals(6, a.getExponent());
    }

    @Test
    public void add_expDiffIsMoreThan16_thisUnchanged() {
        BigDouble a = new BigDouble(1.2, 30);
        a.add(4.5, 6);

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(30, a.getExponent());
    }

    @Test
    public void add_expDiffIsEqualTo16_thisUnchanged() {
        BigDouble a = new BigDouble(1.2, 18);
        a.add(4.5, 2);

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(18, a.getExponent());
    }

    @Test
    public void add_expDiffIsLessThanNeg16_thisBecomesOther() {
        BigDouble a = new BigDouble(1.2, 3);
        a.add(4.5, 60);

        assertEquals(4.5, a.getSignificand(), EPSILON);
        assertEquals(60, a.getExponent());
    }

    @Test
    public void add_expDiffIsEqualToNeg16_thisBecomesOther() {
        BigDouble a = new BigDouble(1.2, 2);
        a.add(4.5, 18);

        assertEquals(4.5, a.getSignificand(), EPSILON);
        assertEquals(18, a.getExponent());
    }

    @Test
    public void add_control_otherSigScaledToThisExpAndAddedToThisSig() {
        BigDouble a = new BigDouble(1.2, 3);
        a.add(4.5, 6);

        assertEquals(4.5012, a.getSignificand(), EPSILON);
        assertEquals(6, a.getExponent());
    }


    /// /////// multiply(BigDouble other), multiply(double significand, int exponent) //////////

    @Test
    public void multiply_otherSigIsZero_thisUnchanged() {
        BigDouble a = new BigDouble(1.2, 3);
        a.multiply(0, 0);

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(3, a.getExponent());
    }

    @Test
    public void multiply_thisSigIsZero_thisUnchanged() {
        BigDouble a = new BigDouble(0, 0);
        a.multiply(4.5, 6);

        assertEquals(0, a.getSignificand(), EPSILON);
        assertEquals(0, a.getExponent());
    }

    @Test
    public void multiply_control_sigsMultipliedAndExpsAdded() {
        BigDouble a = new BigDouble(1.2, 3);
        a.multiply(4.5, 6);

        assertEquals(5.4, a.getSignificand(), EPSILON);
        assertEquals(9, a.getExponent());
    }


    /// /////// divide(BigDouble other), divide(double significand, int exponent) //////////

    @Test
    public void divide_otherSigIsZero_thisUnchanged() {
        BigDouble a = new BigDouble(1.2, 3);
        a.divide(0, 0);

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(3, a.getExponent());
    }

    @Test
    public void divide_thisSigIsZero_thisUnchanged() {
        BigDouble a = new BigDouble(0, 0);
        a.divide(4.5, 6);

        assertEquals(0, a.getSignificand(), EPSILON);
        assertEquals(0, a.getExponent());
    }

    @Test
    public void divide_control_sigsDividedAndExpsSubtracted() {
        BigDouble a = new BigDouble(1.2, 3);
        a.divide(4.5, 6);

        assertEquals(2.6666666666, a.getSignificand(), EPSILON);
        assertEquals(-4, a.getExponent());
    }


    /// /////// compareTo(BigDouble other) //////////

    @Test
    public void compareTo_thisPosSigOtherNegSig_returns1() {
        BigDouble a = new BigDouble(1.2, 3);
        BigDouble b = new BigDouble(-4.5, 6);

        assertEquals(1, a.compareTo(b));
    }

    @Test
    public void compareTo_thisPosSigOtherSigIsZero_returns1() {
        BigDouble a = new BigDouble(1.2, 3);
        BigDouble b = new BigDouble(0, 0);

        assertEquals(1, a.compareTo(b));
    }

    @Test
    public void compareTo_thisNegSigOtherPosSig_returnsNeg1() {
        BigDouble a = new BigDouble(-1.2, 3);
        BigDouble b = new BigDouble(4.5, 6);

        assertEquals(-1, a.compareTo(b));
    }

    @Test
    public void compareTo_thisNegSigOtherSigIsZero_returnsNeg1() {
        BigDouble a = new BigDouble(-1.2, 3);
        BigDouble b = new BigDouble(0, 0);

        assertEquals(-1, a.compareTo(b));
    }

    @Test
    public void compareTo_bothSigsAreZero_returns0() {
        BigDouble a = new BigDouble(0, 0);
        BigDouble b = new BigDouble(0, 0);

        assertEquals(0, a.compareTo(b));
    }

    @Test
    public void compareTo_thisSigIsZeroOtherPosSig_returnsNeg1() {
        BigDouble a = new BigDouble(0, 0);
        BigDouble b = new BigDouble(4.5, 6);

        assertEquals(-1, a.compareTo(b));
    }

    @Test
    public void compareTo_thisSigIsZeroOtherNegSig_returns1() {
        BigDouble a = new BigDouble(0, 0);
        BigDouble b = new BigDouble(-4.5, 6);

        assertEquals(1, a.compareTo(b));
    }

    @Test
    public void compareTo_posSigsThisExpBigger_returns1() {
        BigDouble a = new BigDouble(1.2, 30);
        BigDouble b = new BigDouble(4.5, 6);

        assertEquals(1, a.compareTo(b));
    }

    @Test
    public void compareTo_posSigsThisExpSmaller_returnsNeg1() {
        BigDouble a = new BigDouble(1.2, 3);
        BigDouble b = new BigDouble(4.5, 6);

        assertEquals(-1, a.compareTo(b));
    }

    @Test
    public void compareTo_negSigsThisExpBigger_returnsNeg1() {
        BigDouble a = new BigDouble(-1.2, 30);
        BigDouble b = new BigDouble(-4.5, 6);

        assertEquals(-1, a.compareTo(b));
    }

    @Test
    public void compareTo_negSigsThisExpSmaller_returns1() {
        BigDouble a = new BigDouble(-1.2, 3);
        BigDouble b = new BigDouble(-4.5, 6);

        assertEquals(1, a.compareTo(b));
    }

    @Test
    public void compareTo_sameSignSigsEqualExpsThisSigBigger_returns1() {
        BigDouble a = new BigDouble(4.5, 3);
        BigDouble b = new BigDouble(1.2, 3);

        assertEquals(1, a.compareTo(b));
    }

    @Test
    public void compareTo_equalSigsEqualExps_returns0() {
        BigDouble a = new BigDouble(1.2, 3);
        BigDouble b = new BigDouble(1.2, 3);

        assertEquals(0, a.compareTo(b));
    }

    @Test
    public void compareTo_sameSignSigsEqualExpsThisSigSmaller_returnsNeg1() {
        BigDouble a = new BigDouble(1.2, 3);
        BigDouble b = new BigDouble(4.5, 3);

        assertEquals(-1, a.compareTo(b));
    }


    /// /////// normalize() //////////

    @Test
    public void normalize_sigIsZero_expSetToZero() {
        BigDouble a = new BigDouble(0, 3);

        assertEquals(0, a.getSignificand(), EPSILON);
        assertEquals(0, a.getExponent());
    }

    @Test
    public void normalize_sigBiggerThan10_sigAndExpNormalized() {
        BigDouble a = new BigDouble(12.0, 0);

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(1, a.getExponent());
    }

    @Test
    public void normalize_sigEqualTo10_sigAndExpNormalized() {
        BigDouble a = new BigDouble(10.0, 0);

        assertEquals(1, a.getSignificand(), EPSILON);
        assertEquals(1, a.getExponent());
    }

    @Test
    public void normalize_sigSmallerThan1_sigAndExpNormalized() {
        BigDouble a = new BigDouble(0.12, 0);

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(-1, a.getExponent());
    }

    @Test
    public void normalize_posSigSmallerThan1AfterShift_sigAndExpNormalized() {
        BigDouble a = new BigDouble(0.009999999999999998, 0);

        assertEquals(9.999999999999998, a.getSignificand(), EPSILON);
        assertEquals(-3, a.getExponent());
    }

    @Test
    public void normalize_control_unchanged() {
        BigDouble a = new BigDouble(1.2, 3);

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(3, a.getExponent());
    }


    /// /////// toString() //////////

    @Test
    public void toString_control_thisConvertedToSciNotString() {
        BigDouble a = new BigDouble(1.2, 3);

        assertEquals("1.2e3", a.toString());
    }
}
