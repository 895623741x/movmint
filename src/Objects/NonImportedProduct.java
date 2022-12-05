package Objects;

import Enums.ProductType;
import Helper.CalculationHelper;

public class NonImportedProduct extends Product {

    private final float BASIC_SALES_TAX = 0.1f;
    private float salesTax;
    private float receiptPrice;
    public NonImportedProduct(String name, float price, int quantity, ProductType productType) {
        super(name, price, quantity, productType);
    }

    @Override
    public void calculateReceiptPrice() {
        float total = (float) Math.round(this.getPrice() * this.getQuantity() * 100.00f) / 100.00f;
        this.calculateSalesTax();
        this.receiptPrice = total + this.getSalesTax();
    }

    public float getReceiptPrice() {
        return receiptPrice;
    }

    @Override
    public void calculateSalesTax() {
        CalculationHelper calculationHelper = new CalculationHelper();
        float total = (float) (Math.round(this.getPrice() * this.getQuantity() * 100.00f) / 100.00f);

        if (this.getProductType() == ProductType.BOOK || this.getProductType() == ProductType.FOOD || this.getProductType() == ProductType.MEDICINE) {
            salesTax = calculationHelper.salesTaxRoundUp(total, 0);
        } else {
            salesTax = calculationHelper.salesTaxRoundUp(total, BASIC_SALES_TAX);
        }

    }
    public float getSalesTax() {
        return salesTax;
    }
}
