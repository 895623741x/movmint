import Products.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> cart;

    public ShoppingCart() {
        this.cart = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.cart.add(product);
    }

    public List<Product> getCart() {
        return cart;
    }
}
