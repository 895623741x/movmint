package Tests;
import static org.junit.Assert.*;

import Objects.*;
import org.junit.Test;

import Enums.ProductType;

public class ProductReceiptJUnit {
    private static final double DELTA = 1e-15;

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
    NonImportedProduct nonImportedproduct1 = new NonImportedProduct("cookies", 10.00f, 2, ProductType.FOOD);
    NonImportedProduct nonImportedproduct2 = new NonImportedProduct("shampoo", 20.00f, 1, ProductType.OTHER);
    ImportedProduct importedproduct1 = new ImportedProduct("imported book", 31.49f, 2, ProductType.BOOK);
    ImportedProduct importedproduct2 = new ImportedProduct("imported tool", 8.69f, 1, ProductType.OTHER);



    @Test
    public void testProducts() {
        nip1.calculateReceiptPrice();
        nip2.calculateReceiptPrice();
        nip3.calculateReceiptPrice();
        nip3.calculateSalesTax();
        assertEquals(12.49f, nip1.getReceiptPrice(), DELTA);
        assertEquals(16.49f, nip2.getReceiptPrice(), DELTA);
        assertEquals(0.85f, nip3.getReceiptPrice(), DELTA);
        assertEquals(0.00f, nip3.getSalesTax(), DELTA);
    }

    @Test
    public void testReceiptWithOnlyNonImportedProducts() {
//        Product ip1 = new ImportedProduct("imported box of chocolates", 10.00f, 1, ProductType.FOOD);
//        Product ip2 = new ImportedProduct("imported bottle of perfume", 47.50f, 1, ProductType.OTHER);

        ShoppingCart cart1 = new ShoppingCart("cart1");
        cart1.addProduct(nip1);
        cart1.addProduct(nip2);
        cart1.addProduct(nip3);
        ProductReceipt receipt1 = new ProductReceipt("receipt1", cart1);
        assertEquals(1.50f, receipt1.getSalesTax(), DELTA);
        assertEquals(29.83f, receipt1.getTotal(), DELTA);
        receipt1.generateReceiptTXT();
    }

    @Test
    public void testReceiptWithOnlyImportedProducts() {
//        Product nip1 = new NonImportedProduct("book", 12.49f, 1, ProductType.BOOK);
//        Product nip2 = new NonImportedProduct("music CD", 14.99f, 1, ProductType.OTHER);
//        Product nip3 = new NonImportedProduct("chocolate bar", 0.85f, 1, ProductType.FOOD);

        ShoppingCart cart2 = new ShoppingCart("cart2");
        cart2.addProduct(ip1);
        cart2.addProduct(ip2);
        ProductReceipt receipt2 = new ProductReceipt("receipt2", cart2);
        assertEquals(7.65f, receipt2.getSalesTax(), DELTA);
        assertEquals(65.15f, receipt2.getTotal(), DELTA);
        receipt2.generateReceiptTXT();
    }
    
    @Test
    public void testReceiptWithImportedProductsAndNonImportedProducts() {
//        Product ip3 = new ImportedProduct("imported bottle of perfume", 27.99f, 1, ProductType.OTHER);
//        Product nip4 = new NonImportedProduct("bottle of perfume", 18.99f, 1, ProductType.OTHER);
//        Product nip5 = new NonImportedProduct("headache pills", 9.75f, 1, ProductType.MEDICINE);
//        Product ip4 = new ImportedProduct("box of imported chocolates", 11.25f, 1, ProductType.FOOD);

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
//    @Test
//    public void testReceiptWithMultipleImportedProductsAndNonImportedProducts() {
////        Product nonImportedproduct1 = new NonImportedProduct("cookies", 10.00f, 2, ProductType.FOOD);
////        Product nonImportedproduct2 = new NonImportedProduct("shampoo", 20.00f, 1, ProductType.OTHER);
////        Product importedproduct1 = new NonImportedProduct("imported book", 31.38f, 2, ProductType.BOOK);
////        Product importedproduct2 = new NonImportedProduct("imported tool", 8.69f, 1, ProductType.OTHER);
//
//        ShoppingCart cart4 = new ShoppingCart("cart4");
//        cart4.addProduct(nonImportedproduct1);
//        cart4.addProduct(nonImportedproduct2);
//        cart4.addProduct(importedproduct1);
//        cart4.addProduct(importedproduct2);
//        ProductReceipt receipt4 = new ProductReceipt("receipt4", cart4);
//        assertEquals(0.00f, nonImportedproduct1.getSalesTax(), DELTA);
//        assertEquals(20.00f, nonImportedproduct1.getReceiptPrice(), DELTA);
//        assertEquals(2.00f, nonImportedproduct2.getSalesTax(), DELTA);
//        assertEquals(22.00f, nonImportedproduct2.getReceiptPrice(), DELTA);
//        assertEquals(3.15f, importedproduct1.getSalesTax(), DELTA);
//        assertEquals(66.13f, importedproduct1.getReceiptPrice(), DELTA);
//        assertEquals(1.30f, importedproduct2.getSalesTax(), DELTA);
//        assertEquals(9.99f, importedproduct2.getReceiptPrice(), DELTA);
//        assertEquals(6.45f, receipt4.getSalesTax(), DELTA);
//        assertEquals(118.119995f, receipt4.getTotal(), DELTA);
//        receipt4.generateReceiptTXT();
//    }



}
