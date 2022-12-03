import Products.Product;
import TaxCalculator.ImportTaxCalculator;
import TaxCalculator.SalesTaxCalculator;

public class Receipt {
    ShoppingCart shoppingCart;
    ImportTaxCalculator importTaxCalculator;
    SalesTaxCalculator salesTaxCalculator;
    float salesTax;
    float total;

    public Receipt(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
        for (Product product: this.shoppingCart.getCart()) {
            this.importTaxCalculator = new ImportTaxCalculator(product);
            this.salesTaxCalculator = new SalesTaxCalculator(product);

            this.salesTax += (this.importTaxCalculator.calculate() + this.salesTaxCalculator.calculate());
            this.total += product.getPrice();
        }
    }

    public float getSalesTax() {
        return this.salesTax;
    }

    public float getTotal() {
        return this.total;
    }


}
