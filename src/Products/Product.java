package Products;

public abstract class Product {
    private String name;
    private float price;
    private int quantity;
    private boolean isSalesTaxApplied;
    private boolean isImported;


    public Product(String name, float price, int quantity, boolean isSalesTaxApplied, boolean isImported) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isSalesTaxApplied = isSalesTaxApplied;
        this.isImported = isImported;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSalesTaxApplied(boolean salesTaxApplied) {
        isSalesTaxApplied = salesTaxApplied;
    }

    public void setImported(boolean imported) {
        this.isImported = imported;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isSalesTaxApplied() {
        return isSalesTaxApplied;
    }

    public boolean isImported() {
        return isImported;
    }
}
