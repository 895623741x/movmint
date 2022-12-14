package Objects;

import Enums.ProductType;
import Helper.CalculationHelper;


public class ImportedProduct extends Product {
    private final float BASIC_SALES_TAX = 0.1f;
    private final float IMPORT_TAX = 0.05f;
    private float salesTax;
    private float receiptPrice;

    public ImportedProduct(String name, float price, int quantity, ProductType productType) {
        super(name, price, quantity, productType);
        this.salesTax = 0;
        this.receiptPrice = 0;
    }

    // compute the price that will be presented on the receipt (original price + sales tax)
    @Override
    public void calculateReceiptPrice() {
        float total = (float) (Math.round(this.getOriginalPrice() * this.getQuantity() * 100.00f) / 100.00f);
        this.calculateSalesTax();
        this.receiptPrice = total + getSalesTax();
    }

    public float getReceiptPrice() {
        return receiptPrice;
    }

    // compute the sales tax, since the product is imported, sales tax will include basic sales tax and import tax
    @Override
    public void calculateSalesTax() {
        CalculationHelper calculationHelper = new CalculationHelper();
        float total = (float) (Math.round(this.getOriginalPrice() * this.getQuantity() * 100.00f) / 100.00f);

        if (this.getProductType() == ProductType.BOOK || this.getProductType() == ProductType.FOOD || this.getProductType() == ProductType.MEDICINE) {
            salesTax = calculationHelper.salesTaxRoundUp(total, IMPORT_TAX);
        } else {
            salesTax = calculationHelper.salesTaxRoundUp(total, BASIC_SALES_TAX + IMPORT_TAX);
        }
    }

    public float getSalesTax() {
        return salesTax;
    }
}
