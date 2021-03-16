import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import powerSeries.Arctg;

public class ArctgTest {
    private static final Double TEST_PRECISION = 0.000000001;
    @ParameterizedTest
    @CsvFileSource(resources = "/testOk.csv")
    public void testOkValues(Double value, Double expected) {
        Arctg result = new Arctg(value);
        Assertions.assertEquals(expected, result.getResult(), result.getPrecision());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testPrecisions.csv")
    public void testPrecisions(Double arg, Double okPrecision, Double wrongPrecision, Double result) {
        Assertions.assertEquals(result, new Arctg(arg, okPrecision).getResult(), okPrecision);
        Assertions.assertNotEquals(result, new Arctg(arg, wrongPrecision).getResult(), okPrecision);
    }

    @Test
    public void testMaxNumbers() {
        Arctg resWithMaxDouble = new Arctg(Double.MAX_VALUE, TEST_PRECISION);
        Arctg resWithMaxLong = new Arctg((double) Long.MAX_VALUE, TEST_PRECISION);
        Assertions.assertEquals(Math.atan(Double.MAX_VALUE), resWithMaxDouble.getResult(), resWithMaxDouble.getPrecision());
        Assertions.assertEquals(Math.atan(Long.MAX_VALUE), resWithMaxLong.getResult(), resWithMaxLong.getPrecision());
    }

    @Test
    public void testMinNumbers() {
        Arctg restWithMinDouble = new Arctg(Double.MIN_VALUE, TEST_PRECISION);
        Arctg resWithMinLong = new Arctg((double) Long.MIN_VALUE, TEST_PRECISION);
        Assertions.assertEquals(Math.atan(Double.MIN_VALUE), restWithMinDouble.getResult(), restWithMinDouble.getPrecision());
        Assertions.assertEquals(Math.atan(Long.MIN_VALUE), resWithMinLong.getResult(), resWithMinLong.getPrecision());
    }

    @Test
    public void testPrecisionExceptions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Arctg(0d, 2d));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Arctg(0d, -2d));
    }

    @Test
    public void testArgumentExceptions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Arctg(Double.POSITIVE_INFINITY));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Arctg(Double.NEGATIVE_INFINITY));
    }
}
