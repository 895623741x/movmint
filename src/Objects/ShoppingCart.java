package Objects;

import Exceptions.GoodNotFoundException;
import Interfaces.ShoppingCartInterface;

import java.util.*;

public class ShoppingCart implements ShoppingCartInterface {
    Map<String, Product> map;

    public ShoppingCart() {
        map = new HashMap<>();
    }

    public Map<String, Product> getShoppingCartMap() {
        return this.map;
    }

    @Override
    public void addProduct(Product product) {
        this.map.put(product.getName(), product);
    }

    @Override
    public void removeProduct(String productName) throws GoodNotFoundException {
        if (map.containsKey(productName)) {
            map.remove(productName);
        } else {
            throw new GoodNotFoundException("the product " + productName + " is not found");
        }
    }


}
