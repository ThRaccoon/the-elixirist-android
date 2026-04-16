/*
    All tests follow the format:
    [method]_[scenario]_[result]
*/

package com.theelixirist.f112813;

import static org.junit.Assert.assertEquals;

import com.theelixirist.f112813.game.math.BigDouble;
import com.theelixirist.f112813.game.math.BigDoubleFormatter;

import org.junit.Test;

public class BigDoubleFormatterTest {

    /// /////// format(BigDouble number) //////////

    @Test
    public void format_negExp_scientificNotation() {
        BigDouble a = new BigDouble(1.2, -3);

        assertEquals("1.20e-3", BigDoubleFormatter.format(a));
    }

    @Test
    public void format_expIsSmallerThan3_Integer() {
        BigDouble a = new BigDouble(1.23, 1);

        assertEquals("12", BigDoubleFormatter.format(a));
    }

    @Test
    public void format_expIs3_kSuffix() {
        BigDouble a = new BigDouble(1.2, 3);

        assertEquals("1.20K", BigDoubleFormatter.format(a));
    }

    @Test
    public void format_expIs45_qadSuffix() {
        BigDouble a = new BigDouble(1.2, 45);

        assertEquals("1.20Qad", BigDoubleFormatter.format(a));
    }

    @Test
    public void format_expIs90_nvSuffix() {
        BigDouble a = new BigDouble(1.2, 90);

        assertEquals("1.20Nv", BigDoubleFormatter.format(a));
    }

    @Test
    public void format_expIsBiggerThan92_scientificNotation() {
        BigDouble a = new BigDouble(1.2, 93);

        assertEquals("1.20e93", BigDoubleFormatter.format(a));
    }
}
