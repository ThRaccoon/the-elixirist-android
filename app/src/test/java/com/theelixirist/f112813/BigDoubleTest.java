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

    // <editor-fold desc="add">

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
    public void add_expDiffIsMoreThanPos16_thisUnchanged() {
        BigDouble a = new BigDouble(1.2, 30);
        a.add(4.5, 6);

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(30, a.getExponent());
    }

    @Test
    public void add_expDiffIsEqualToPos16_thisUnchanged() {
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
    public void add_otherPosSig_otherSigScaledToThisExpAndAddedToThisSig() {
        BigDouble a = new BigDouble(1.2, 3);
        a.add(4.5, 6);

        assertEquals(4.5012, a.getSignificand(), EPSILON);
        assertEquals(6, a.getExponent());
    }

    @Test
    public void add_otherNegSig_otherSigScaledToThisExpAndAddedToThisSig() {
        BigDouble a = new BigDouble(1.2, 3);
        a.add(-4.5, 6);

        assertEquals(-4.4988, a.getSignificand(), EPSILON);
        assertEquals(6, a.getExponent());
    }

    // </editor-fold>

    // <editor-fold desc="multiply">

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
    public void multiply_otherPosSigPosExp_sigsMultipliedAndExpsAdded() {
        BigDouble a = new BigDouble(1.2, 3);
        a.multiply(4.5, 6);

        assertEquals(5.4, a.getSignificand(), EPSILON);
        assertEquals(9, a.getExponent());
    }

    @Test
    public void multiply_otherNegSigPosExp_sigsMultipliedAndExpsAdded() {
        BigDouble a = new BigDouble(1.2, 3);
        a.multiply(-4.5, 6);

        assertEquals(-5.4, a.getSignificand(), EPSILON);
        assertEquals(9, a.getExponent());
    }

    @Test
    public void multiply_otherPosSigNegExp_sigsMultipliedAndExpsAdded() {
        BigDouble a = new BigDouble(1.2, 3);
        a.multiply(4.5, -6);

        assertEquals(5.4, a.getSignificand(), EPSILON);
        assertEquals(-3, a.getExponent());
    }

    @Test
    public void multiply_otherNegSigNegExp_sigsMultipliedAndExpsAdded() {
        BigDouble a = new BigDouble(1.2, 3);
        a.multiply(-4.5, -6);

        assertEquals(-5.4, a.getSignificand(), EPSILON);
        assertEquals(-3, a.getExponent());
    }

    // </editor-fold>

    // <editor-fold desc="divide">

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
    public void divide_otherPosSigPosExp_sigsDividedAndExpsSubtracted() {
        BigDouble a = new BigDouble(1.2, 3);
        a.divide(4.5, 6);

        assertEquals(2.6666666666, a.getSignificand(), EPSILON);
        assertEquals(-4, a.getExponent());
    }

    @Test
    public void divide_otherNegSigPosExp_sigsDividedAndExpsSubtracted() {
        BigDouble a = new BigDouble(1.2, 3);
        a.divide(-4.5, 6);

        assertEquals(-2.6666666666, a.getSignificand(), EPSILON);
        assertEquals(-4, a.getExponent());
    }

    @Test
    public void divide_otherPosSigNegExp_sigsDividedAndExpsSubtracted() {
        BigDouble a = new BigDouble(1.2, 3);
        a.divide(4.5, -6);

        assertEquals(2.6666666666, a.getSignificand(), EPSILON);
        assertEquals(8, a.getExponent());
    }

    @Test
    public void divide_otherNegSigNegExp_sigsDividedAndExpsSubtracted() {
        BigDouble a = new BigDouble(1.2, 3);
        a.divide(-4.5, -6);

        assertEquals(-2.6666666666, a.getSignificand(), EPSILON);
        assertEquals(8, a.getExponent());
    }

    // </editor-fold>

    // <editor-fold desc="compareTo">

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
    public void compareTo_posSigsEqualExpsThisSigBigger_returns1() {
        BigDouble a = new BigDouble(4.5, 3);
        BigDouble b = new BigDouble(1.2, 3);

        assertEquals(1, a.compareTo(b));
    }

    @Test
    public void compareTo_posSigsEqualExpsEqualSigs_returns0() {
        BigDouble a = new BigDouble(1.2, 3);
        BigDouble b = new BigDouble(1.2, 3);

        assertEquals(0, a.compareTo(b));
    }

    @Test
    public void compareTo_posSigsEqualExpsThisSigSmaller_returnsNeg1() {
        BigDouble a = new BigDouble(1.2, 3);
        BigDouble b = new BigDouble(4.5, 3);

        assertEquals(-1, a.compareTo(b));
    }

    @Test
    public void compareTo_negSigsEqualExpsThisSigBigger_returns1() {
        BigDouble a = new BigDouble(-1.2, 3);
        BigDouble b = new BigDouble(-4.5, 3);

        assertEquals(1, a.compareTo(b));
    }

    @Test
    public void compareTo_negSigsEqualExpsEqualSigs_returns0() {
        BigDouble a = new BigDouble(-1.2, 3);
        BigDouble b = new BigDouble(-1.2, 3);

        assertEquals(0, a.compareTo(b));
    }

    @Test
    public void compareTo_negSigsEqualExpsThisSigSmaller_returnsNeg1() {
        BigDouble a = new BigDouble(-4.5, 3);
        BigDouble b = new BigDouble(-1.2, 3);

        assertEquals(-1, a.compareTo(b));
    }

    // </editor-fold>

    // <editor-fold desc="normalize">

    @Test
    public void normalize_sigIsZero_expSetToZero() {
        BigDouble a = new BigDouble(0, 3);

        assertEquals(0, a.getSignificand(), EPSILON);
        assertEquals(0, a.getExponent());
    }

    @Test
    public void normalize_posSigNormalized_unchanged() {
        BigDouble a = new BigDouble(1.2, 3);

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(3, a.getExponent());
    }

    @Test
    public void normalize_negSigNormalized_unchanged() {
        BigDouble a = new BigDouble(-1.2, 3);

        assertEquals(-1.2, a.getSignificand(), EPSILON);
        assertEquals(3, a.getExponent());
    }

    @Test
    public void normalize_posSigBiggerThan10_sigAndExpNormalized() {
        BigDouble a = new BigDouble(12.0, 0);

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(1, a.getExponent());
    }

    @Test
    public void normalize_posSigEqualTo10_sigAndExpNormalized() {
        BigDouble a = new BigDouble(10.0, 0);

        assertEquals(1, a.getSignificand(), EPSILON);
        assertEquals(1, a.getExponent());
    }

    @Test
    public void normalize_posSigSmallerThan1_sigAndExpNormalized() {
        BigDouble a = new BigDouble(0.12, 0);

        assertEquals(1.2, a.getSignificand(), EPSILON);
        assertEquals(-1, a.getExponent());
    }

    @Test
    public void normalize_negSigSmallerThanNeg10_sigAndExpNormalized() {
        BigDouble a = new BigDouble(-12.0, 0);

        assertEquals(-1.2, a.getSignificand(), EPSILON);
        assertEquals(1, a.getExponent());
    }

    @Test
    public void normalize_negSigEqualToNeg10_sigAndExpNormalized() {
        BigDouble a = new BigDouble(-10.0, 0);

        assertEquals(-1, a.getSignificand(), EPSILON);
        assertEquals(1, a.getExponent());
    }

    @Test
    public void normalize_negSigBiggerThanNeg1_sigAndExpNormalized() {
        BigDouble a = new BigDouble(-0.12, 0);

        assertEquals(-1.2, a.getSignificand(), EPSILON);
        assertEquals(-1, a.getExponent());
    }

    @Test
    public void normalize_posSigBelowPos1AfterShift_sigAndExpNormalized() {
        BigDouble a = new BigDouble(0.009999999999999998, 0);

        assertEquals(9.999999999999998, a.getSignificand(), EPSILON);
        assertEquals(-3, a.getExponent());
    }

    @Test
    public void normalize_negSigAboveNeg1AfterShift_sigAndExpNormalized() {
        BigDouble a = new BigDouble(-0.009999999999999998, 0);

        assertEquals(-9.999999999999998, a.getSignificand(), EPSILON);
        assertEquals(-3, a.getExponent());
    }

    // </editor-fold>
}
