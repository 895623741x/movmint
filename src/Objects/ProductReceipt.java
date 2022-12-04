package Objects;

import java.util.Map;

public class ProductReceipt {
    ShoppingCart cart;
    private float salesTax;
    private float total;

    public ProductReceipt(ShoppingCart cart) {
        this.cart = cart;
        this.salesTax = 0;
        this.total = 0;
    }

    public float getSalesTax() {
        Map<String, Product> map = this.cart.getShoppingCartMap();
        for (String productName: map.keySet()) {
            Product current = map.get(productName);
            salesTax += current.getSalesTax();

        }

        return salesTax;
    }

    public float getTotal() {
        Map<String, Product> map = this.cart.getShoppingCartMap();
        for (String productName: map.keySet()) {
            Product current = map.get(productName);
            total += current.getReceiptPrice();
        }
        return total;
    }
}
