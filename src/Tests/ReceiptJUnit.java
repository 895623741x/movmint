package Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

import Exceptions.GoodNotFoundException;
import Exceptions.ShoppingBasketNotFoundException;
import org.junit.Test;

import Objects.ImportedGood;
import Objects.NonImportedGood;
import Objects.Receipt;
import Objects.ShoppingBasket;
import GoodTypes.GoodType;

public class ReceiptJUnit {

    @Test
    public void testReceiptResult() {

        Receipt receipt = new Receipt();

        // Shopping Basket 1
        ShoppingBasket shoppingBasket1 = new ShoppingBasket("sb1");
        NonImportedGood nonig1 = new NonImportedGood("book", 1, new BigDecimal("12.49"), GoodType.BOOK);
        NonImportedGood nonig2 = new NonImportedGood("music CD", 1, new BigDecimal("14.99"), GoodType.OTHER);
        NonImportedGood nonig3 = new NonImportedGood("chocolate bar", 1, new BigDecimal("0.85"),GoodType.FOOD);

        shoppingBasket1.addGood(nonig1);
        shoppingBasket1.addGood(nonig2);
        shoppingBasket1.addGood(nonig3);
        receipt.addShoppingBasket(shoppingBasket1);

        // Shopping Basket 2
        ShoppingBasket shoppingBasket2 = new ShoppingBasket("sb2");
        ImportedGood imp1 = new ImportedGood("imported box of chocolates", 1, new BigDecimal("10.00"), GoodType.FOOD);
        ImportedGood imp2 = new ImportedGood("imported bottle of perfume", 1, new BigDecimal("47.50"), GoodType.OTHER);

        shoppingBasket2.addGood(imp1);
        shoppingBasket2.addGood(imp2);
        receipt.addShoppingBasket(shoppingBasket2);

        // Shopping Basket 3
        ShoppingBasket shoppingBasket3 = new ShoppingBasket("sb3");
        ImportedGood imp3 = new ImportedGood("imported bottle of perfume", 1, new BigDecimal("27.99"), GoodType.OTHER);
        NonImportedGood nonig4 = new NonImportedGood("bottle of perfume", 1, new BigDecimal("18.99"), GoodType.OTHER);
        NonImportedGood nonig5 = new NonImportedGood("packet of headache pills", 1, new BigDecimal("9.75"), GoodType.MEDICINE);
        ImportedGood imp4 = new ImportedGood("imported box of chocolates", 1, new BigDecimal("11.25"), GoodType.FOOD);

        shoppingBasket3.addGood(imp3);
        shoppingBasket3.addGood(nonig4);
        shoppingBasket3.addGood(nonig5);
        shoppingBasket3.addGood(imp4);
        receipt.addShoppingBasket(shoppingBasket3);

        try {
            assertEquals(new BigDecimal("29.83"), receipt.getShoppingBasketFromReceipt("sb1").getTotalAmount());
            assertEquals(new BigDecimal("65.15"), receipt.getShoppingBasketFromReceipt("sb2").getTotalAmount());
            assertEquals(new BigDecimal("74.68"), receipt.getShoppingBasketFromReceipt("sb3").getTotalAmount());
        } catch (ShoppingBasketNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
