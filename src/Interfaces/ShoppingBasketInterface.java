package Interfaces;

import Objects.Good;
import Objects.ShoppingBasket;
import Exceptions.GoodNotFoundException;

public interface ShoppingBasketInterface {

    boolean addGood(Good good);
    boolean removeGood(String idName) throws GoodNotFoundException;
    ShoppingBasket getShoppingBasketFromReceipt(String idName) throws GoodNotFoundException;
    int shoppingBasketCount();
}
