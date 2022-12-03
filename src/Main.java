import Products.BookProduct;
import Products.OtherProduct;
import Products.Product;

public class Main {
    public static void main(String[] args) {

        ShoppingCart sc = new ShoppingCart();
        Product book = new BookProduct("book", 12.49f, 1, false, false);
        Product MusicCD = new OtherProduct("music CD", 14.99f, 1, true, false);
        Product chocolateBar = new OtherProduct("chocolate bar", 0.85f, 1, false, false);

        sc.addProduct(book);
        sc.addProduct(MusicCD);
        sc.addProduct(chocolateBar);

        Receipt receipt = new Receipt(sc);

        System.out.println("Sales Tax: " + receipt.getSalesTax());
        System.out.println("Total " + receipt.getTotal());
    }
}