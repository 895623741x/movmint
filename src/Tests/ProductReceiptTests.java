package Tests;
import static org.junit.Assert.*;

import Exceptions.ProductNotFoundException;
import Exceptions.ShoppingCartEmptyException;
import Objects.*;
import org.junit.Test;
import Enums.ProductType;

public class ProductReceiptTests {
    private static final double DELTA = 1e-5;

    // Input 1
    NonImportedProduct nip1 = new NonImportedProduct("book", 12.49f, 1, ProductType.BOOK);
    NonImportedProduct nip2 = new NonImportedProduct("music CD", 14.99f, 1, ProductType.OTHER);
    NonImportedProduct nip3 = new NonImportedProduct("chocolate bar", 0.85f, 1, ProductType.FOOD);
    // Input 2
    ImportedProduct ip1 = new ImportedProduct("imported box of chocolates", 10.00f, 1, ProductType.FOOD);
    ImportedProduct ip2 = new ImportedProduct("imported bottle of perfume", 47.50f, 1, ProductType.OTHER);

    //Input 3
    ImportedProduct ip3 = new ImportedProduct("imported bottle of perfume", 27.99f, 1, ProductType.OTHER);
    NonImportedProduct nip4 = new NonImportedProduct("bottle of perfume", 18.99f, 1, ProductType.OTHER);
    NonImportedProduct nip5 = new NonImportedProduct("headache pills", 9.75f, 1, ProductType.MEDICINE);
    ImportedProduct ip4 = new ImportedProduct("box of imported chocolates", 11.25f, 1, ProductType.FOOD);

    // Self Defined Input 1
    NonImportedProduct nonImportedProduct1 = new NonImportedProduct("cookies", 10.00f, 2, ProductType.FOOD);
    NonImportedProduct nonImportedProduct2 = new NonImportedProduct("shampoo", 20.00f, 1, ProductType.OTHER);
    ImportedProduct importedProduct1 = new ImportedProduct("imported book", 31.99f, 2, ProductType.BOOK);
    ImportedProduct importedProduct2 = new ImportedProduct("imported tool", 8.99f, 1, ProductType.OTHER);


    // Testing each product's sales tax and final prices listed on the receipt are calculated correctly
    @Test
    public void testProducts() {
        nip1.calculateReceiptPrice();
        nip2.calculateReceiptPrice();
        nip3.calculateReceiptPrice();
        nip3.calculateSalesTax();

        assertEquals(12.49f, nip1.getOriginalPrice(), DELTA);
        assertEquals(12.49f, nip1.getReceiptPrice(), DELTA);
        assertEquals(0.00f, nip1.getSalesTax(), DELTA);

        assertEquals(14.99f, nip2.getOriginalPrice(), DELTA);
        assertEquals(16.49f, nip2.getReceiptPrice(), DELTA);
        assertEquals(1.50f, nip2.getSalesTax(), DELTA);

        assertEquals(0.85f, nip3.getOriginalPrice(), DELTA);
        assertEquals(0.85f, nip3.getReceiptPrice(), DELTA);
        assertEquals(0.00f, nip3.getSalesTax(), DELTA);
    }

    // Testing if exceptions are handled by the shopping cart class while removing the products from the cart
    @Test
    public void testShoppingCartThrowsException() {
        ShoppingCart testingCart = new ShoppingCart("testing");
        assertTrue(testingCart.isEmpty());

        // handle the ShoppingCartEmptyException when trying to find a product in an empty shopping cart
        assertThrows(ShoppingCartEmptyException.class, () -> testingCart.removeProduct(nip1.getName()));
        assertThrows(ShoppingCartEmptyException.class, () -> testingCart.getProduct(nip1.getName()));

        // Testing how many products are in the cart
        testingCart.addProduct(nip1);
        assertEquals(1, testingCart.size());

        // handle the ProductNotFoundException when trying to find a product that does not exist in the cart
        assertThrows(ProductNotFoundException.class, () -> testingCart.removeProduct(nip2.getName()));
        assertThrows(ProductNotFoundException.class, () -> testingCart.getProduct(nip2.getName()));
    }

    // Testing if exceptions are handled by the ProductReceipt cart class when getting the products from the cart
    @Test
    public void testReceiptException() {
        ShoppingCart testingCart = new ShoppingCart("testing cart");
        testingCart.addProduct(nip1);
        testingCart.addProduct(nip2);

//        ProductReceipt testingReceipt = null;

        ProductReceipt testingReceipt = new ProductReceipt("testing receipt", testingCart);
        try {
            assertEquals(nip1, testingReceipt.getProduct(nip1.getName()));
            assertEquals(nip2, testingReceipt.getProduct(nip2.getName()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(1.50f, testingReceipt.getSalesTax(), DELTA);
        assertEquals(28.98f, testingReceipt.getTotal(), DELTA);

        System.out.println("The input shopping cart can not be empty!");
    }



    // Testing example input 1
    @Test
    public void testReceiptWithOnlyNonImportedProducts() {

        ShoppingCart cart1 = new ShoppingCart("cart1");
        cart1.addProduct(nip1);
        cart1.addProduct(nip2);
        cart1.addProduct(nip3);
        ProductReceipt receipt1 = new ProductReceipt("receipt1", cart1);
        assertEquals(1.50f, receipt1.getSalesTax(), DELTA);
        assertEquals(29.83f, receipt1.getTotal(), DELTA);
        receipt1.generateReceiptTXT();
    }

    // Testing example input 2
    @Test
    public void testReceiptWithOnlyImportedProducts() {

        ShoppingCart cart2 = new ShoppingCart("cart2");
        cart2.addProduct(ip1);
        cart2.addProduct(ip2);
        ProductReceipt receipt2 = new ProductReceipt("receipt2", cart2);
        assertEquals(7.65f, receipt2.getSalesTax(), DELTA);
        assertEquals(65.15f, receipt2.getTotal(), DELTA);
        receipt2.generateReceiptTXT();
    }

    // Testing example input 3
    @Test
    public void testReceiptWithImportedProductsAndNonImportedProducts() {

        ShoppingCart cart3 = new ShoppingCart("cart3");
        cart3.addProduct(ip3);
        cart3.addProduct(nip4);
        cart3.addProduct(nip5);
        cart3.addProduct(ip4);
        ProductReceipt receipt3 = new ProductReceipt("receipt3", cart3);
        assertEquals(32.19f, ip3.getReceiptPrice(), DELTA); // imported bottle of perfume
        assertEquals(20.89f, nip4.getReceiptPrice(), DELTA);
        assertEquals(9.75f, nip5.getReceiptPrice(), DELTA);
        assertEquals(11.85f, ip4.getReceiptPrice(), DELTA);
        assertEquals(6.70f, receipt3.getSalesTax(), DELTA);
        assertEquals(74.68f, receipt3.getTotal(), DELTA);
        receipt3.generateReceiptTXT();
    }

    // Testing self defined inputs in which imported products and non-imported products can be multiple or single
    @Test
    public void testReceiptWithMultipleImportedProductsAndNonImportedProducts() {

        ShoppingCart cart4 = new ShoppingCart("cart4");
        cart4.addProduct(nonImportedProduct1);
        cart4.addProduct(nonImportedProduct2);
        cart4.addProduct(importedProduct1);
        cart4.addProduct(importedProduct2);
        ProductReceipt receipt4 = new ProductReceipt("receipt4", cart4);
        assertEquals(0.00f, nonImportedProduct1.getSalesTax(), DELTA);
        assertEquals(20.00f, nonImportedProduct1.getReceiptPrice(), DELTA);
        assertEquals(2.00f, nonImportedProduct2.getSalesTax(), DELTA);
        assertEquals(22.00f, nonImportedProduct2.getReceiptPrice(), DELTA);
        assertEquals(3.20f, importedProduct1.getSalesTax(), DELTA);
        assertEquals(67.18f, importedProduct1.getReceiptPrice(), DELTA);
        assertEquals(1.35f, importedProduct2.getSalesTax(), DELTA);
        assertEquals(10.34f, importedProduct2.getReceiptPrice(), DELTA);
        assertEquals(6.55f, receipt4.getSalesTax(), DELTA);
        assertEquals(119.52f, receipt4.getTotal(), DELTA);
        receipt4.generateReceiptTXT();
    }



}
