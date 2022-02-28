package oop.leti.numbers;

public class CustomRational extends CustomNumber {
    int numerator;
    int denominator;

    public CustomRational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public CustomNumber plus(CustomNumber a) {
        return null;
    }

    @Override
    public CustomNumber minus(CustomNumber a) {
        return null;
    }

    @Override
    public CustomNumber mult(CustomNumber a) {
        return null;
    }

    @Override
    public CustomNumber div(CustomNumber a) {
        return null;
    }

    @Override
    public boolean biggerZero() {
        return false;
    }

    @Override
    public boolean isZero() {
        return false;
    }

    @Override
    public CustomNumber sqrt() {
        return null;
    }

    @Override
    public Number getValue() {
        return null;
    }
}
