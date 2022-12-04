package Objects;
import java.math.BigDecimal;

import GoodTypes.GoodType;
public abstract class Good {

        private String description;
        private int quantity;
        private BigDecimal price;
        private BigDecimal salesTax;
        private GoodType goodtype;

        public Good(String description, int quantity, BigDecimal price, GoodType goodtype) {
            this.description = description;
            this.quantity = quantity;
            this.price = price;
            this.salesTax = BigDecimal.ZERO;
            this.goodtype = goodtype;
        }

        public abstract BigDecimal computePrice();
        public abstract BigDecimal computeSaleTax();


        public String getDescription() {
            return description;
        }

        public int getQuantity() {
            return quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public BigDecimal getSalesTax() {
            return salesTax;
        }

        public GoodType getGoodtype() {
            return goodtype;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public void setSalesTax(BigDecimal salesTax) {
            this.salesTax = salesTax;
        }

        public void setGoodtype(GoodType goodtype) {
            this.goodtype = goodtype;
        }

        @Override
        public String toString() {
            return this.quantity+ " "+this.description + ": "+ (this.getPrice().multiply(new BigDecimal(this.getQuantity())));
        }

    }

