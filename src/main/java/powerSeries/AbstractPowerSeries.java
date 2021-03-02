package powerSeries;

import java.math.BigDecimal;

public abstract class AbstractPowerSeries implements PowerSeries {

    protected static final BigDecimal MINUS_ONE = BigDecimal.ONE.negate();
    protected static final Double defaultPrecision = 0.000001;

    protected final Double precision;
    protected final Double result;

    protected AbstractPowerSeries(Double arg, Double precision) {
        if (Math.abs(arg) > getMaxAbsArgValue()) {
            throw new IllegalArgumentException("Absolute argument value must be less than or equal to " + getMaxAbsArgValue().toString());
        }
        if (Math.abs(precision) > getMaxPrecision()) {
            throw new IllegalArgumentException("Absolute precision must be less than " + getMaxPrecision().toString());
        }
        this.precision = precision;
        this.result = calc(arg, precision);
    }

    protected AbstractPowerSeries(Double arg) {
        this(arg, defaultPrecision);
    }

    @Override
    public Double getResult() {
        return result;
    }

    @Override
    public Double getPrecision() {
        return precision;
    }

    public abstract Double getMaxPrecision();

    public abstract Double getMaxAbsArgValue();

    public abstract Double calc(Double arg, Double precision);
}
