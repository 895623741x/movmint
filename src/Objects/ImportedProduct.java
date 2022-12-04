package Objects;

import GoodTypes.ProductType;
import Helper.CalculationHelper;


public class ImportedProduct extends Product {

    private final float BASIC_SALES_TAX = 0.1f;
    private final float IMPORT_TAX = 0.05f;
    private float salesTax;
    public ImportedProduct(String name, float price, int quantity, ProductType productType) {
        super(name, price, quantity, productType);
    }

    @Override
    public float getReceiptPrice() {
        CalculationHelper calculationHelper = new CalculationHelper();
        float total = this.getPrice() * this.getQuantity();

        if (this.getProductType() == ProductType.BOOK || this.getProductType() == ProductType.FOOD || this.getProductType() == ProductType.MEDICINE) {
            salesTax = calculationHelper.salesTaxRoundUp(total, IMPORT_TAX);
        } else {
            salesTax = calculationHelper.salesTaxRoundUp(total, BASIC_SALES_TAX + IMPORT_TAX);
        }
        return total + salesTax;
    }

    @Override
    public float getSalesTax() {
        this.getReceiptPrice();
        return salesTax;
    }
}
