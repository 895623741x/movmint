package Objects;

import Exceptions.ProductNotFoundException;
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

    @Override
    public void removeProduct(String productName) throws ProductNotFoundException {
        if (map.containsKey(productName)) {
            map.remove(productName);
        } else {
            throw new ProductNotFoundException("the product " + productName + " is not found");
        }
    }

    public String getShoppingCartName() {
        return this.shoppingCartName;
    }

}
