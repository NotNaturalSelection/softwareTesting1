package powerSeries;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Arctg {

    private static final Double MAX_PRECISION = 1d;
    private static final Double MAX_ABS_ARG_VALUE = Double.MAX_VALUE;
    private static final Double MAX_VALUE_TO_CALC = Math.PI / 12;
    private static final Double PI_DIV_6 = Math.PI / 6;
    private static final Double defaultPrecision = 0.000001;
    private static final BigDecimal SQRT_OF_3 = BigDecimal.valueOf(Math.sqrt(3));
    private static final BigDecimal MINUS_ONE = BigDecimal.ONE.negate();

    private final Double precision;
    private final Double result;

    public Arctg(Double arg, Double precision) {
        if (Math.abs(arg) > MAX_ABS_ARG_VALUE) {
            throw new IllegalArgumentException("Absolute argument value must be less than or equal to " + MAX_ABS_ARG_VALUE);
        }
        if (Math.abs(precision) > MAX_PRECISION) {
            throw new IllegalArgumentException("Absolute precision must be less than " + MAX_PRECISION);
        }
        this.precision = precision;
        this.result = calc(arg, precision);
    }

    public Arctg(Double arg) {
        this(arg, defaultPrecision);
    }

    public Double getPrecision() {
        return precision;
    }

    public Double getResult() {
        return result;
    }

    private BigDecimal resultOfSeries(BigDecimal arg, BigDecimal bigDecimalPrecision){
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal rowMember = arg;
        int i = 1;
        while (rowMember.abs().doubleValue() >= bigDecimalPrecision.doubleValue()) {
            rowMember = arg.pow(2 * i - 1).divide(BigDecimal.valueOf(2L * i - 1), RoundingMode.HALF_DOWN).multiply(MINUS_ONE.pow(i - 1));
            result = result.add(rowMember);
            i++;
        }
        return result;
    }

    public Double calc(Double doubleArg, Double doublePrecision) {
        BigDecimal arg = BigDecimal.valueOf(doubleArg);
        BigDecimal bigDecimalPrecision = BigDecimal.valueOf(doublePrecision);

        if (arg.doubleValue() < 0) {
            arg = arg.negate();
        }
        if (arg.doubleValue() > 1) {
            arg = BigDecimal.valueOf(1 / arg.doubleValue());
        }

        int offsetTimes = 0;

        while (arg.doubleValue() > MAX_VALUE_TO_CALC) {
            arg = arg
                    .multiply(SQRT_OF_3)
                    .subtract(BigDecimal.ONE)
                    .divide(arg.add(SQRT_OF_3), RoundingMode.HALF_DOWN);
            offsetTimes++;
        }

        BigDecimal result = resultOfSeries(arg, bigDecimalPrecision);

        result = result.add(BigDecimal.valueOf(PI_DIV_6 * offsetTimes));

        if (Math.abs(doubleArg) > 1) {
            result = BigDecimal.valueOf(Math.PI / 2).subtract(result);
        }
        if (doubleArg < 0) {
            result = result.negate();
        }

        return result.doubleValue();
    }
}
