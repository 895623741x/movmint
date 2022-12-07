package Interfaces;

import Exceptions.ProductNotFoundException;
import Exceptions.ShoppingCartEmptyException;
import Objects.Product;

public interface ShoppingCartInterface {
    void addProduct(Product product);
    void removeProduct(String productName) throws ProductNotFoundException, ShoppingCartEmptyException;
}
