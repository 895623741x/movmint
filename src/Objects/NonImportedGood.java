package Objects;


import java.math.BigDecimal;
import GoodTypes.GoodType;
import Helper.Helper;

public class NonImportedGood extends Good {
    final String BASIC_SALE_TAX = "0.10"; // which corresponds to a discount of 10 %

    /**
     * Constructor for an Item, given also the type of the Item
     * @param description
     * @param quantity
     * @param price
     * @param goodtype
     */
    public NonImportedGood(String description, int quantity, BigDecimal price, GoodType goodtype) {
        super(description, quantity, price, goodtype);
    }

    public BigDecimal computePrice(){
        switch (this.getGoodtype()) {
            case BOOK:
            case FOOD:
            case MEDICINE:
                return this.getPrice();
            case OTHER:
            default:
                this.computeSaleTax();
                this.setPrice(this.getPrice().add(this.getSalesTax()));
                return this.getPrice();
        }
    }

    /**
     * Compute the sale tax of the Non Imported Good
     */
    public BigDecimal computeSaleTax(){
        switch (this.getGoodtype()) {
            case BOOK:
            case FOOD:
            case MEDICINE:
                return this.getSalesTax();
            case OTHER:
            default:
                this.setSalesTax(Helper.roundUpTo(this.getPrice().multiply(new BigDecimal(BASIC_SALE_TAX)).setScale(2, BigDecimal.ROUND_UP), new BigDecimal("0.05")));
                return this.getSalesTax();
        }
    }
}

