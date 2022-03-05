package oop.leti.numbers;

import java.math.BigDecimal;

public class CustomInteger extends CustomNumber {

    private int value;

    public CustomInteger(int value) {
        this.value = value;
    }

    private CustomInteger convertToCustomInteger(CustomNumber a) {
        if (!(a instanceof CustomInteger)) {
            throw new RuntimeException();
        }
        CustomInteger ci = (CustomInteger) a;
        return ci;
    }

    @Override
    public CustomInteger plus(CustomNumber a) {
        return new CustomInteger(this.value + convertToCustomInteger(a).value);
    }

    @Override
    public CustomInteger minus(CustomNumber a) {
        return new CustomInteger(this.value - convertToCustomInteger(a).value);

    }

    @Override
    public CustomInteger mult(CustomNumber a) {
        return new CustomInteger(this.value * convertToCustomInteger(a).value);
    }

    @Override
    public CustomNumber mult(int a) {
        return new CustomInteger(this.value * a);
    }

    @Override
    public CustomInteger div(CustomNumber a) {
        return new CustomInteger(this.value / convertToCustomInteger(a).value);

    }

    @Override
    public boolean biggerZero() {
        return this.value > 0;
    }

    @Override
    public boolean isZero() {
        return this.value == 0;
    }

    @Override
    public CustomInteger sqrt() {
        return new CustomInteger((int) Math.sqrt(this.value));
    }

    @Override
    public Double getValue() {
        return (double) value;
    }

    @Override
    public boolean isTheSame(double exactX) {
        return (double) this.value == exactX;
    }

    @Override
    public String toString() {
        return "" + this.value;
    }
}
