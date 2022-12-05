package Interfaces;

import Exceptions.ProductNotFoundException;
import Objects.Product;

public interface ShoppingCartInterface {
    void addProduct(Product product);
    void removeProduct(String productName) throws ProductNotFoundException;
}
