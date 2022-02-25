package oop.leti;

public abstract class CustomNumber {

    public abstract CustomNumber plus(CustomNumber a);
    public abstract CustomNumber minus(CustomNumber a);
    public abstract CustomNumber mult(CustomNumber a);
    public abstract CustomNumber div(CustomNumber a);
    public abstract boolean biggerZero();
    public abstract boolean isZero();
    public abstract CustomNumber sqrt();
    public abstract Number getValue();
}
