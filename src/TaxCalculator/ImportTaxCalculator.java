package TaxCalculator;

import Products.Product;

public class ImportTaxCalculator implements TaxCalculator {
    Product product;
    public ImportTaxCalculator(Product product) {
        this.product = product;
    }

    @Override
    public float calculate() {
        float salesTax = 0;
        if (this.product.isImported()) {
            salesTax = (float) (Math.round(this.product.getPrice() * 0.05f * 20.0) / 20.0);
        }
        return salesTax;
    }
}
