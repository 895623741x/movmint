package Objects;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import Exceptions.GoodNotFoundException;

public class ShoppingBasket {

    private Map<String,Good> shoppingBasket;
    private BigDecimal totalSalesTax;
    private BigDecimal totalAmount;
    private String idBasket;

    public ShoppingBasket(String idBasket) {
        this.shoppingBasket = new LinkedHashMap<String,Good>();
        this.totalSalesTax = BigDecimal.ZERO;
        this.totalAmount = BigDecimal.ZERO;
        this.idBasket = idBasket;
    }

    /**
     * Method to add a good to the basket
     * @param good
     * @return
     */
    public boolean addGood(Good good){

        Good g1 = good;

        if (good instanceof ImportedGood) {
            g1 = (ImportedGood) good;
        } else {
            g1 = (NonImportedGood) good;
        }

        if(this.shoppingBasket.containsKey(g1.getDescription())) {
            g1.setQuantity(g1.getQuantity() + good.getQuantity());
            g1.computePrice();

            this.setTotalSalesTax(this.totalSalesTax.add((g1.getSalesTax()).multiply(new BigDecimal(g1.getQuantity()))));
            this.setTotalAmount(this.totalAmount.add((g1.getPrice().multiply(new BigDecimal(g1.getQuantity())))));
            return true;
        } else {
            g1.computePrice();
            this.shoppingBasket.put(g1.getDescription(),g1);
            this.setTotalSalesTax(this.totalSalesTax.add((g1.getSalesTax()).multiply(new BigDecimal(g1.getQuantity()))));
            this.setTotalAmount(this.totalAmount.add((g1.getPrice().multiply(new BigDecimal(g1.getQuantity())))));
            return false;
        }
    }

    /**
     * Count the number of goods in the shopping basket
     * @return
     */
    public int goodCount(){
        return this.shoppingBasket.size();
    }

    /**
     * Return the good from the shopping basket, given the idName
     * @param idName
     * @return
     * @throws GoodNotFoundException
     */
    public Good getGoodFromShoppingBasket(String idName) throws GoodNotFoundException {
        if(this.shoppingBasket.containsKey(idName)){
            return this.shoppingBasket.get(idName);
        }else {
            throw new GoodNotFoundException("Good with ID "+idName+" is not Found.");
        }
    }

    /**
     * Remove a good from the shopping basket, given the idName
     * @param idName
     * @return
     * @throws GoodNotFoundException
     */
    public boolean removeGood(String idName) throws GoodNotFoundException {
        if(this.shoppingBasket.containsKey(idName)){
            Good g1 = this.shoppingBasket.get(idName);
            this.setTotalSalesTax(this.totalSalesTax.add((g1.getSalesTax()).multiply(new BigDecimal(g1.getQuantity()))));
            this.setTotalAmount(this.totalAmount.add((g1.getPrice().multiply(new BigDecimal(g1.getQuantity())))));
            this.shoppingBasket.remove(idName);
            return true;
        }else throw new GoodNotFoundException("Good with ID "+idName+" is not Found.");
    }

    /**
     * Clean the Basket
     */
    public void cleanBasket(){
        this.shoppingBasket.clear();
        this.totalSalesTax = BigDecimal.ZERO;
        this.totalAmount = BigDecimal.ZERO;
    }

    /**
     * Getter for the totalSalesTax
     * @return
     */
    public BigDecimal getTotalSalesTax() {
        return totalSalesTax;
    }

    /**
     * Getter for the totalAmount
     * @return
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Getter for the id of the basket
     * @return
     */
    public String getIdBasket() {
        return idBasket;
    }

    /**
     * Setter for the id of the basket
     * @param idBasket
     */
    public void setIdBasket(String idBasket) {
        this.idBasket = idBasket;
    }

    /**
     * Setter for the totalSalesTax
     * @param totalSalesTax
     */
    public void setTotalSalesTax(BigDecimal totalSalesTax) {
        this.totalSalesTax = totalSalesTax;
    }

    /**
     * Setter for the totalAmount
     * @param totalAmount
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    @Override
    public String toString() {
        String output = "";

        for (Map.Entry<String, Good> sb: this.shoppingBasket.entrySet()) {
            //System.out.print(sb.getKey());
            output += sb.getValue().toString() + "\n";
        }

        output += "Sales Taxes: "+this.getTotalSalesTax() + "\n" + "Total: "+this.getTotalAmount();

        return output;
    }
}
