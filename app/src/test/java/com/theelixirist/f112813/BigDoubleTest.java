package com.theelixirist.f112813;

import static org.junit.Assert.assertEquals;

import com.theelixirist.f112813.game.math.BigDouble;

import org.junit.Test;

public class BigDoubleTest {

    /// /////// new BigDouble(double value) //////////

    @Test
    public void create_bigPos_mantissa1Exponent2() {
        BigDouble a = new BigDouble(100, 0);

        assertEquals(1, a.getMantissa(), 1e-10);
        assertEquals(2, a.getExponent());
    }

    @Test
    public void create_smallPos_mantissa1ExponentNeg2() {
        BigDouble a = new BigDouble(0.01, 0);

        assertEquals(1, a.getMantissa(), 1e-10);
        assertEquals(-2, a.getExponent());
    }

    @Test
    public void create_one_mantissa1Exponent0() {
        BigDouble a = new BigDouble(1, 0);

        assertEquals(1, a.getMantissa(), 1e-10);
        assertEquals(0, a.getExponent());
    }

    @Test
    public void create_zero_mantissa0Exponent0() {
        BigDouble a = new BigDouble(0, 0);

        assertEquals(0, a.getMantissa(), 1e-10);
        assertEquals(0, a.getExponent());
    }

    @Test
    public void create_bigNeg_mantissaNeg1Exponent2() {
        BigDouble a = new BigDouble(-100, 0);

        assertEquals(-1, a.getMantissa(), 1e-10);
        assertEquals(2, a.getExponent());
    }

    @Test
    public void create_smallNeg_mantissaNeg1ExponentNeg2() {
        BigDouble a = new BigDouble(-0.01, 0);

        assertEquals(-1, a.getMantissa(), 1e-10);
        assertEquals(-2, a.getExponent());
    }

    @Test
    public void add() {
        BigDouble a = new BigDouble(11, 0);
        a.add(1, 0);

        System.out.println(a.getMantissa());

        assertEquals(1.2, a.getMantissa(), 1e-9);
    }
}
