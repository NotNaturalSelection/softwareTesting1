import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import powerSeries.impl.ArctgPowerSeries;

public class ArctgPowerSeriesTest {
    private static final Double TEST_PRECISION = 0.000000001;
    @ParameterizedTest
    @CsvFileSource(resources = "/testOk.csv")
    public void testOk(Double value, Double precision, Double result) {
        Assertions.assertEquals(result, ArctgPowerSeries.ofArgWithPrecision(value, precision).getResult(), precision);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testPrecisions.csv")
    public void testPrecisions(Double arg, Double okPrecision, Double wrongPrecision, Double result) {
        Assertions.assertEquals(result, ArctgPowerSeries.ofArgWithPrecision(arg, okPrecision).getResult(), okPrecision);
        Assertions.assertNotEquals(result, ArctgPowerSeries.ofArgWithPrecision(arg, wrongPrecision).getResult(), okPrecision);
    }

    @Test
    public void testMaxNumbers() {
        ArctgPowerSeries psWithDouble = ArctgPowerSeries.ofArgWithPrecision(Double.MAX_VALUE, TEST_PRECISION);
        ArctgPowerSeries psWithLong = ArctgPowerSeries.ofArgWithPrecision((double) Long.MAX_VALUE, TEST_PRECISION);
        Assertions.assertEquals(Math.atan(Double.MAX_VALUE), psWithDouble.getResult(), psWithDouble.getPrecision());
        Assertions.assertEquals(Math.atan(Long.MAX_VALUE), psWithLong.getResult(), psWithLong.getPrecision());
    }

    @Test
    public void testMinNumbers() {
        ArctgPowerSeries psWithDouble = ArctgPowerSeries.ofArgWithPrecision(Double.MIN_VALUE, TEST_PRECISION);
        ArctgPowerSeries psWithLong = ArctgPowerSeries.ofArgWithPrecision((double) Long.MIN_VALUE, TEST_PRECISION);
        Assertions.assertEquals(Math.atan(Double.MIN_VALUE), psWithDouble.getResult(), psWithDouble.getPrecision());
        Assertions.assertEquals(Math.atan(Long.MIN_VALUE), psWithLong.getResult(), psWithLong.getPrecision());
    }

    @Test
    public void testPrecisionExceptions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArctgPowerSeries.ofArgWithPrecision(0d, 2d));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArctgPowerSeries.ofArgWithPrecision(0d, -2d));
    }

    @Test
    public void testArgumentExceptions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArctgPowerSeries.of(Double.POSITIVE_INFINITY));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ArctgPowerSeries.of(Double.NEGATIVE_INFINITY));
    }
}
