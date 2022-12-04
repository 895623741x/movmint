package Objects;

import java.math.BigDecimal;

import GoodTypes.GoodType;
import Helper.Helper;
import Interfaces.GoodInterface;

public class ImportedGood extends Good implements GoodInterface {

    final String IMPORT_DUTY_TAX = "0.05"; // which corresponds to a discount of 5 %
    final String IMPORT_DUTY_BASIC_SALE_TAX = "0.15"; // which corresponds to a discount of 15 % (import duty tax + basic sale tax)
    public ImportedGood(String description, int quantity, BigDecimal price, GoodType goodtype) {
        super(description, quantity, price, goodtype);
    }

    public BigDecimal computePrice() {
        switch (this.getGoodtype()) {
            case BOOK:
            case FOOD:
            case MEDICINE:
                this.computeSaleTax();
                this.setPrice((this.getPrice()).add(this.getSalesTax()));
                return this.getPrice();
            case OTHER:
            default:
                this.computeSaleTax();
                this.setPrice(this.getPrice().add(this.getSalesTax()));
                return this.getPrice();
        }
    }


    /**
     * Compute the sale tax of the Imported Good
     * @return
     */
    public BigDecimal computeSaleTax(){
        switch (this.getGoodtype()) {
            case BOOK:
            case FOOD:
            case MEDICINE:
                this.setSalesTax(Helper.roundUpTo(this.getPrice().multiply(new BigDecimal(IMPORT_DUTY_TAX)).setScale(2, BigDecimal.ROUND_UP), new BigDecimal("0.05")));
                return this.getSalesTax();
            case OTHER:
            default:
                this.setSalesTax(Helper.roundUpTo(this.getPrice().multiply(new BigDecimal(IMPORT_DUTY_BASIC_SALE_TAX)).setScale(2, BigDecimal.ROUND_UP), new BigDecimal("0.05")));
                return this.getSalesTax();
        }
    }
}
