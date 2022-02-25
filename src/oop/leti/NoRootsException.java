package oop.leti;

public class NoRootsException extends RuntimeException{
    @Override
    public String getMessage() {
        return "\nНет корней\n";
    }
}
