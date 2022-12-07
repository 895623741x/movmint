package Objects;

import Exceptions.ProductNotFoundException;
import Exceptions.ShoppingCartEmptyException;
import Helper.CalculationHelper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ProductReceipt {
    private ShoppingCart cart;
    private float salesTax;
    private float total;
    private String receiptName;
    public ProductReceipt(String receiptName, ShoppingCart cart) {

        this.receiptName = receiptName;
        this.cart = cart;
        this.salesTax = 0;
        this.total = 0;

        Map<String, Product> map = this.cart.getShoppingCartMap();

        // compute the total sales tax which will printed on the receipt
        for (String productName: map.keySet()) {
            Product currentProduct = map.get(productName);

            if (currentProduct instanceof ImportedProduct) {
                this.salesTax += ((ImportedProduct) currentProduct).getSalesTax();
            } else {
                this.salesTax += ((NonImportedProduct) currentProduct).getSalesTax();
            }
        }

        // compute the total sales tax which will printed on the receipt
        for (String productName: map.keySet()) {
            Product currentProduct = map.get(productName);

            if (currentProduct instanceof ImportedProduct) {
                this.total += ((ImportedProduct) currentProduct).getReceiptPrice();
            } else {
                this.total += ((NonImportedProduct) currentProduct).getReceiptPrice();
            }
        }

        // Round sales tax and total price to two decimal places float number
        this.salesTax = (float) Math.round(this.salesTax * 100.00f) / 100.00f;
        this.total = (float) Math.round(this.total * 100.00f) / 100.00f;
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

    public Product getProduct(String productName) throws ProductNotFoundException, ShoppingCartEmptyException {

        if (this.cart.isEmpty()) {
            throw new ShoppingCartEmptyException("Shopping cart can not be empty!");
        }

        Map<String, Product> cartMap = this.getShoppingCart().getShoppingCartMap();

        if (cartMap.containsKey(productName)) {
            return cartMap.get(productName);
        } else {
            throw new ProductNotFoundException("Product " + productName + " can not be found!");
        }
    }

    public void generateReceiptTXT() {
        String receiptName = this.getReceiptName();
        ShoppingCart shoppingCart = this.getShoppingCart();
        Map<String, Product> shoppingCartMap = shoppingCart.getShoppingCartMap();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Results/" + receiptName + shoppingCart.getShoppingCartName() + ".txt"));
            writer.write(receiptName + " output:" + "\n");

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
