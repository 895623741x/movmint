package Interfaces;

import Exceptions.GoodNotFoundException;
import Objects.Product;
import Objects.ShoppingBasket;

public interface ShoppingCartInterface {
    void addProduct(Product product);
    void removeProduct(String productName) throws GoodNotFoundException;
}
