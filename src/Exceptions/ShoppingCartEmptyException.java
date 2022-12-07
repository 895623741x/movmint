package Exceptions;

public class ShoppingCartEmptyException extends Exception{
    public ShoppingCartEmptyException(String message) {
        super(message);
    }
}
