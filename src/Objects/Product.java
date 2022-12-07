package Objects;

import Enums.ProductType;
import Interfaces.ProductInterface;

public abstract class Product implements ProductInterface {
    private String name;
    private float price;
    private int quantity;
    private ProductType productType;



    public Product(String name, float price, int quantity, ProductType productType) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.productType = productType;
    }

    public abstract void calculateReceiptPrice();

    public abstract void calculateSalesTax();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // get the product price before tax
    public float getOriginalPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
