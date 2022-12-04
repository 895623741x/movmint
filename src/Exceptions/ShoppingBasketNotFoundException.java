package Exceptions;

public class ShoppingBasketNotFoundException extends Exception{

    public ShoppingBasketNotFoundException(){}
    public ShoppingBasketNotFoundException(String msg){
        super(msg);
    }
}