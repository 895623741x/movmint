package Objects;

import Exceptions.ProductNotFoundException;
import Exceptions.ShoppingCartEmptyException;
import Interfaces.ShoppingCartInterface;

import java.util.*;

public class ShoppingCart implements ShoppingCartInterface {
    Map<String, Product> map;
    String shoppingCartName;

    public ShoppingCart(String shoppingCartName) {
        map = new HashMap<>();
        this.shoppingCartName = shoppingCartName;
    }

    public Map<String, Product> getShoppingCartMap() {
        return this.map;
    }

    @Override
    public void addProduct(Product product) {
        product.calculateSalesTax();
        product.calculateReceiptPrice();
        this.map.put(product.getName(), product);
    }

    public Product getProduct(String productName) throws ProductNotFoundException, ShoppingCartEmptyException {
        // throw ShoppingCartEmptyException when trying to find a product in an empty cart
        if (map.isEmpty()) {
            throw new ShoppingCartEmptyException("Shopping cart is empty!");
        }

        if (map.containsKey(productName)) {
            return map.get(productName);
        } else {
            throw new ProductNotFoundException("Product " + productName + " can not be found!");
        }
    }

    @Override
    public void removeProduct(String productName) throws ProductNotFoundException, ShoppingCartEmptyException {
        // throw ShoppingCartEmptyException when trying to find a product in an empty cart
        if (map.isEmpty()) {
            throw new ShoppingCartEmptyException("Shopping cart is empty!");
        }

        if (map.containsKey(productName)) {
            map.remove(productName);
        } else {
            // throw ProductNotFoundException when trying to find a product that does not exist in the cart
            throw new ProductNotFoundException("Product " + productName + " does not exist!");
        }
    }

    public String getShoppingCartName() {
        return this.shoppingCartName;
    }

    public boolean isEmpty() {
        return this.getShoppingCartMap().isEmpty();
    }
    public int size() {
        return map.size();
    }
}
