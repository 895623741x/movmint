package TaxCalculator;

import Products.Product;

public class SalesTaxCalculator implements TaxCalculator{
    Product product;

    public SalesTaxCalculator(Product product) {
        this.product = product;
    }
    @Override
    public float calculate() {
        float importTax = 0;
        if (this.product.isSalesTaxApplied()) {
            importTax = (float) (Math.round(this.product.getPrice() * 0.1f * 20.0) / 20.0);
        }
        return importTax;
    }
}
