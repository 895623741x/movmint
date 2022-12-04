package Objects;

import java.util.LinkedHashMap;
import java.util.Map;

import Exceptions.ShoppingBasketNotFoundException;
import Interfaces.ReceiptInterface;

public class Receipt implements ReceiptInterface {

    public String idName;
    private Map<String,ShoppingBasket> receipt;

    /**
     * Constructor method for Receipt without parameters
     */
    public Receipt(){
        receipt = new LinkedHashMap<String,ShoppingBasket>();
    }

    /**
     * Add a shopping basket to the receipt
     * @param sb
     * @return
     */
    public boolean addShoppingBasket(ShoppingBasket sb){
        if(this.receipt.containsKey(sb.getIdBasket())){
            return false;
        } else {
            this.receipt.put(sb.getIdBasket(), sb);
            return true;
        }
    }

    /**
     * Remove the shopping basket from the receipt
     * @param idName
     * @return
     * @throws ShoppingBasketNotFoundException
     */
    @Override
    public boolean removeShoppingBasket(String idName) throws ShoppingBasketNotFoundException{
        if(this.receipt.containsKey(idName)){
            this.receipt.remove(idName);
            return true;
        }else throw new ShoppingBasketNotFoundException("ShoppingBasket with ID "+idName+" is not Found.");
    }

    /**
     * Get the shopping basket from the receipt, given the id
     * @param idName
     * @return
     * @throws ShoppingBasketNotFoundException
     */
    public ShoppingBasket getShoppingBasketFromReceipt(String idName) throws ShoppingBasketNotFoundException {
        if(this.receipt.containsKey(idName)){
            return this.receipt.get(idName);
        }else {
            throw new ShoppingBasketNotFoundException("ShoppingBasket with ID "+idName+" is not Found.");
        }
    }

    /**
     * Return the number of shopping basket in the receipt
     * @return
     */
    public int receiptCount(){
        return this.receipt.size();
    }

    @Override
    public String toString() {
        String output = "";
        int i = 1;

        for (Map.Entry<String, ShoppingBasket> sb: this.receipt.entrySet()) {
            output += "Output "+i+":\n";
            if(this.receipt.size() == i)
                output += sb.getValue().toString();
            else
                output += sb.getValue().toString() + "\n\n";
            i++;
        }

        return output;
    }
}
