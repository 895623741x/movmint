package Tests;
import static org.junit.Assert.*;

import Objects.*;
import org.junit.Test;

import GoodTypes.ProductType;

public class ProductReceiptJUnit {
    private static final double DELTA = 1e-15;

    // Input 1
    Product nip1 = new NonImportedProduct("book", 12.49f, 1, ProductType.BOOK);
    Product nip2 = new NonImportedProduct("music CD", 14.99f, 1, ProductType.OTHER);
    Product nip3 = new NonImportedProduct("chocolate bar", 0.85f, 1, ProductType.FOOD);
    // Input 2
    Product ip1 = new ImportedProduct("imported box of chocolates", 10.00f, 1, ProductType.FOOD);
    Product ip2 = new ImportedProduct("imported bottle of perfume", 47.50f, 1, ProductType.OTHER);

    //Input 3
    Product ip3 = new ImportedProduct("imported bottle of perfume", 27.99f, 1, ProductType.OTHER);
    Product nip4 = new NonImportedProduct("bottle of perfume", 18.99f, 1, ProductType.OTHER);
    Product nip5 = new NonImportedProduct("headache pills", 9.75f, 1, ProductType.MEDICINE);
    Product ip4 = new ImportedProduct("box of imported chocolates", 11.25f, 1, ProductType.FOOD);



    @Test
    public void testProducts() {
        assertEquals(12.49f, nip1.getReceiptPrice(), DELTA);
        assertEquals(16.49f, nip2.getReceiptPrice(), DELTA);
        assertEquals(0.85f, nip3.getReceiptPrice(), DELTA);
        assertEquals(0.00f, nip3.getSalesTax(), DELTA);
    }

    @Test
    public void testReceiptWithOnlyNonImportedProducts() {
        ShoppingCart cart2 = new ShoppingCart();
        cart2.addProduct(ip1);
        cart2.addProduct(ip2);
        ProductReceipt receipt2 = new ProductReceipt(cart2);
        assertEquals(7.65f, receipt2.getSalesTax(), DELTA);
        assertEquals(65.15f, receipt2.getTotal(), DELTA);
    }
    @Test
    public void testReceiptWithOnlyImportedProducts() {
        ShoppingCart cart1 = new ShoppingCart();
        cart1.addProduct(nip1);
        cart1.addProduct(nip2);
        cart1.addProduct(nip3);
        ProductReceipt receipt1 = new ProductReceipt(cart1);
        assertEquals(1.50f, receipt1.getSalesTax(), DELTA);
        assertEquals(29.83f, receipt1.getTotal(), DELTA);
    }
    @Test
    public void testReceiptWithOnlyImportedProductsAndNonImportedProducts() {
        ShoppingCart cart3 = new ShoppingCart();
        cart3.addProduct(ip3);
        cart3.addProduct(nip4);
        cart3.addProduct(nip5);
        cart3.addProduct(ip4);
        ProductReceipt receipt3 = new ProductReceipt(cart3);
        assertEquals(6.70f, receipt3.getSalesTax(), DELTA);
        assertEquals(74.68f, receipt3.getTotal(), DELTA);
    }


}
