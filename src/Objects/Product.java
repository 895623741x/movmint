package Objects;

import Enums.ProductType;

public abstract class Product {
    private String name;
    private float price;
    private int quantity;
    private float salesTax;
    private ProductType productType;
    private float receiptPrice;
    private final float BASIC_SALES_TAX = 0.1f;
    private final float IMPORT_TAX = 0.05f;


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

    public float getPrice() {
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
