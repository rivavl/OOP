package oop.leti.polinom;

public class NoRootsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "\nНет корней\n";
    }
}
