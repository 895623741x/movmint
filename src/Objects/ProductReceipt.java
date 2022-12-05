package Objects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ProductReceipt {
    ShoppingCart cart;
    private float salesTax;
    private float total;
    private String receiptName;
    public ProductReceipt(String receiptName, ShoppingCart cart) {
        this.receiptName = receiptName;
        this.cart = cart;
        this.salesTax = 0;
        this.total = 0;

        Map<String, Product> map = this.cart.getShoppingCartMap();
        for (String productName: map.keySet()) {
            Product currentProduct = map.get(productName);

            if (currentProduct instanceof ImportedProduct) {
                salesTax += ((ImportedProduct) currentProduct).getSalesTax();
            } else {
                salesTax += ((NonImportedProduct) currentProduct).getSalesTax();
            }
        }
        for (String productName: map.keySet()) {
            Product currentProduct = map.get(productName);

            if (currentProduct instanceof ImportedProduct) {
                total += ((ImportedProduct) currentProduct).getReceiptPrice();
            } else {
                total += ((NonImportedProduct) currentProduct).getReceiptPrice();
            }

        }
    }

    public float getSalesTax() {
        return salesTax;
    }

    public float getTotal() {
        return total;
    }

    public ShoppingCart getShoppingCart() {
        return cart;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void generateReceiptTXT() {
        String receiptName = this.getReceiptName();
        ShoppingCart shoppingCart = this.getShoppingCart();
        Map<String, Product> shoppingCartMap = shoppingCart.getShoppingCartMap();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Results/" + receiptName + shoppingCart.getShoppingCartName() + ".txt"));
            writer.write(receiptName + " output:");

            for (String productKey: shoppingCartMap.keySet()) {

                Product currentProduct = shoppingCartMap.get(productKey);

                if (currentProduct instanceof ImportedProduct) {
                    writer.write("\n" + currentProduct.getQuantity() + " " + currentProduct.getName() + ": " + ((ImportedProduct) currentProduct).getReceiptPrice());
                } else {
                    writer.write("\n" + currentProduct.getQuantity() + " " + currentProduct.getName() + ": " + ((NonImportedProduct) currentProduct).getReceiptPrice());
                }

            }
            writer.write("\n" + "Sales Taxes: " + this.getSalesTax());
            writer.write("\n" + "Total: " + this.getTotal());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
