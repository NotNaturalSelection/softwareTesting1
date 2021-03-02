package powerSeries.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import powerSeries.AbstractPowerSeries;

public class ArctgPowerSeries extends AbstractPowerSeries {
    private static final Double MAX_PRECISION = 1d;
    private static final Double MAX_ABS_ARG_VALUE = Double.MAX_VALUE;
    private static final Double MAX_VALUE_TO_CALC = Math.PI / 12;
    private static final Double SQRT_OF_3 = Math.sqrt(3);
    private static final Double PI_DIV_6 = Math.PI / 6;

    @Override
    public Double getMaxPrecision() {
        return MAX_PRECISION;
    }

    @Override
    public Double getMaxAbsArgValue() {
        return MAX_ABS_ARG_VALUE;
    }

    ArctgPowerSeries(Double arg, Double precision) {
        super(arg, precision);
    }

    ArctgPowerSeries(Double arg) {
        super(arg);
    }

    public static ArctgPowerSeries of(Double arg) {
        return new ArctgPowerSeries(arg);
    }

    public static ArctgPowerSeries ofArgWithPrecision(Double arg, Double precision) {
        return new ArctgPowerSeries(arg, precision);
    }

    @Override
    public Double calc(Double doubleArg, Double doublePrecision) {
        BigDecimal arg = BigDecimal.valueOf(doubleArg);
        BigDecimal precision = BigDecimal.valueOf(doublePrecision);

        if (arg.doubleValue() < 0) {
            arg = arg.negate();
        }

        if (arg.doubleValue() > 1) {
            arg = BigDecimal.valueOf(1 / arg.doubleValue());
        }

        int offset = 0;

        while (arg.doubleValue() > MAX_VALUE_TO_CALC) {
            arg = arg
                    .multiply(BigDecimal.valueOf(SQRT_OF_3))
                    .subtract(BigDecimal.ONE)
                    .divide(arg.add(BigDecimal.valueOf(SQRT_OF_3)), RoundingMode.HALF_DOWN);
            offset++;
        }

        BigDecimal result = BigDecimal.ZERO;
        BigDecimal rowMember = arg;
        int i = 1;
        while (rowMember.abs().doubleValue() >= precision.doubleValue()) {
            rowMember = arg.pow(2 * i - 1).divide(BigDecimal.valueOf(2L * i - 1), RoundingMode.HALF_DOWN).multiply(MINUS_ONE.pow(i - 1));
            result = result.add(rowMember);
            i++;
        }

        result = result.add(BigDecimal.valueOf(PI_DIV_6 * offset));
        if (Math.abs(doubleArg) > 1) {
            result = BigDecimal.valueOf(Math.PI / 2).subtract(result);
        }
        if (doubleArg < 0) {
            result = result.negate();
        }
        return result.doubleValue();
    }
}
