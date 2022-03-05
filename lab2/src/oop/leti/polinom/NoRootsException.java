package oop.leti.polinom;

public class NoRootsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "\nУравнение не имеет вещественных корней\n".toUpperCase();
    }
}
