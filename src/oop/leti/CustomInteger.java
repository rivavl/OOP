package oop.leti;

public class CustomInteger extends CustomNumber{

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
    public CustomNumber sqrt() {
        return new CustomInteger((int) Math.sqrt(this.value));
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + this.value;
    }
}
